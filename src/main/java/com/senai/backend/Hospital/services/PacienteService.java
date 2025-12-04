package com.senai.backend.Hospital.services;

import com.senai.backend.Hospital.models.Agenda;
import com.senai.backend.Hospital.models.Paciente;
import com.senai.backend.Hospital.repositories.AgendaRepository;
import com.senai.backend.Hospital.repositories.PacienteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepo;
    private final AgendaRepository agendaRepo;

    public PacienteService(PacienteRepository pacienteRepo, AgendaRepository agendaRepo) {
        this.pacienteRepo = pacienteRepo;
        this.agendaRepo = agendaRepo;
    }

    public Paciente salvar(Paciente p) {
        LocalDateTime now = LocalDateTime.now();
        p.setDataCriacao(now);
        p.setDataAtualizacao(now);
        if (p.getStatus() == null) p.setStatus(true);
        return pacienteRepo.save(p);
    }

    public Paciente buscarPorId(Integer id) {
        return pacienteRepo.findById(id).orElse(null);
    }

    public List<Paciente> listarTodos() {
        return pacienteRepo.findAll();
    }

    public Long contar() {
        return pacienteRepo.count();
    }

    public void removerPorId(Integer id) {
        pacienteRepo.deleteById(id);
    }

    // atualização total; se status mudar pra false, desativa futuras agendas (regra de negócio)
    public Paciente atualizar(Integer id, Paciente novo) {
        Paciente atual = pacienteRepo.findById(id).orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
        Boolean antigoStatus = atual.getStatus();

        atual.setNome(novo.getNome());
        atual.setEndereco(novo.getEndereco());
        atual.setContato(novo.getContato());
        atual.setDescricao(novo.getDescricao());
        atual.setStatus(novo.getStatus());
        atual.setDataAtualizacao(LocalDateTime.now());

        Paciente salvo = pacienteRepo.save(atual);

        if (Boolean.TRUE.equals(antigoStatus) && Boolean.FALSE.equals(novo.getStatus())) {
            // desativar futuras agendas
            List<Agenda> agendas = agendaRepo.findByPacienteIdAndStatusTrue(id);
            LocalDateTime agora = LocalDateTime.now();
            for (Agenda a : agendas) {
                if (a.getHorario() != null && a.getHorario().getDia() != null) {
                    // comparar data do horario com hoje
                    if (a.getHorario().getDia().isAfter(agora.toLocalDate()) ||
                        (a.getHorario().getDia().isEqual(agora.toLocalDate()) && a.getHorario().getHoraInicial().isAfter(agora.toLocalTime()))) {
                        a.setStatus(false);
                        a.setDataAtualizacao(LocalDateTime.now());
                        agendaRepo.save(a);
                    }
                }
            }
        }

        return salvo;
    }
}
