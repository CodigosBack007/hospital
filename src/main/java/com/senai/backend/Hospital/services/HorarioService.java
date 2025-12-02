package com.senai.backend.Hospital.services;

import com.senai.backend.Hospital.models.Horario;
import com.senai.backend.Hospital.models.Medico;
import com.senai.backend.Hospital.repositories.HorarioRepository;
import com.senai.backend.Hospital.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class HorarioService {

    @Autowired
    private HorarioRepository horarioRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    // Criar horário
    public Horario salvar(Horario horario, Integer medicoId) {

        Medico medico = medicoRepository.findById(medicoId).orElse(null);
        if (medico == null || Boolean.FALSE.equals(medico.getStatus())) {
            return null; // médico inativo ou inexistente
        }

        // Regras básicas:

        // 1) Não permitir horário no passado
        if (horario.getDia().isBefore(LocalDate.now())) {
            return null;
        }

        // 2) Não permitir hora inicial >= hora final
        if (horario.getHoraInicial().isAfter(horario.getHoraFinal()) ||
            horario.getHoraInicial().equals(horario.getHoraFinal())) {
            return null;
        }

        // 3) Não permitir dois horários no mesmo dia para o mesmo médico
        List<Horario> conflitos =
                horarioRepository.findByMedicoIdAndDia(medicoId, horario.getDia());

        if (!conflitos.isEmpty()) {
            return null;
        }

        horario.setMedico(medico);

        return horarioRepository.save(horario);
    }

    public List<Horario> listar() {
        return horarioRepository.findByStatusTrue();
    }

    public Horario buscar(Integer id) {
        return horarioRepository.findById(id).orElse(null);
    }

    // delete lógico
    public boolean desativar(Integer id) {
        Horario h = horarioRepository.findById(id).orElse(null);
        if (h == null) return false;

        h.setStatus(false);
        horarioRepository.save(h);
        return true;
    }
}
