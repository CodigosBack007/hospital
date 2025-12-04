package com.senai.backend.Hospital.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.senai.backend.Hospital.models.Horario;
import com.senai.backend.Hospital.models.Medico;
import com.senai.backend.Hospital.repositories.HorarioRepository;
import com.senai.backend.Hospital.repositories.MedicoRepository;

@Service
public class HorarioService {

    private final HorarioRepository horarioRepo;
    private final MedicoRepository medicoRepo;

    public HorarioService(HorarioRepository horarioRepo, MedicoRepository medicoRepo) {
        this.horarioRepo = horarioRepo;
        this.medicoRepo = medicoRepo;
    }

    public Horario salvar(Horario h, Integer medicoId) {
        Medico medico = medicoRepo.findById(medicoId).orElseThrow(() -> new RuntimeException("Médico não encontrado"));
        if (!Boolean.TRUE.equals(medico.getStatus())) throw new IllegalArgumentException("Médico inativo");

        // validações básicas
        LocalDate hoje = LocalDate.now();
        if (h.getDia() == null || h.getDia().isBefore(hoje)) throw new IllegalArgumentException("Dia precisa ser hoje ou futuro");
        if (h.getHoraInicial() == null || h.getHoraFinal() == null) throw new IllegalArgumentException("Horas inválidas");
        if (!h.getHoraInicial().isBefore(h.getHoraFinal())) throw new IllegalArgumentException("Hora inicial deve ser anterior à hora final");

        // checar conflito no mesmo dia para o mesmo médico
        List<Horario> conflitos = horarioRepo.findByMedicoIdAndDia(medicoId, h.getDia());
        if (!conflitos.isEmpty()) throw new IllegalArgumentException("Já existe horário para esse médico nesse dia");

        h.setMedico(medico);
        h.setStatus(true);
        h.setCriadoEm(LocalDateTime.now());
        h.setAtualizadoEm(LocalDateTime.now());
        return horarioRepo.save(h);
    }

    public Horario buscarPorId(Integer id) {
        return horarioRepo.findById(id).orElse(null);
    }

    public List<Horario> listarTodos() {
        return horarioRepo.findAll();
    }

    public Long contar() {
        return horarioRepo.count();
    }

    public void removerPorId(Integer id) {
        horarioRepo.deleteById(id);
    }

    // atualização total
    public Horario atualizar(Integer id, Horario novo) {
        Horario atual = horarioRepo.findById(id).orElseThrow(() -> new RuntimeException("Horário não encontrado"));

        // validar novo
        LocalDate hoje = LocalDate.now();
        if (novo.getDia() == null || novo.getDia().isBefore(hoje)) throw new IllegalArgumentException("Dia precisa ser hoje ou futuro");
        if (novo.getHoraInicial() == null || novo.getHoraFinal() == null) throw new IllegalArgumentException("Horas inválidas");
        if (!novo.getHoraInicial().isBefore(novo.getHoraFinal())) throw new IllegalArgumentException("Hora inicial deve ser anterior à hora final");

        atual.setDia(novo.getDia());
        atual.setHoraInicial(novo.getHoraInicial());
        atual.setHoraFinal(novo.getHoraFinal());
        // médico é atualizado apenas se fornecido (full update exige medico presente)
        atual.setMedico(novo.getMedico());
        atual.setStatus(novo.getStatus());
        atual.setAtualizadoEm(LocalDateTime.now());
        return horarioRepo.save(atual);
    }
}
