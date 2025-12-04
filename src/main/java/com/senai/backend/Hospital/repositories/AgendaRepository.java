package com.senai.backend.Hospital.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senai.backend.Hospital.models.Agenda;
import com.senai.backend.Hospital.models.Medico;

public interface AgendaRepository extends JpaRepository<Agenda, Integer> {
    List<Agenda> findByStatusTrue();
    List<Agenda> findByPacienteIdAndStatusTrue(Integer pacienteId);
    List<Agenda> findByHorarioIdAndStatusTrue(Integer horarioId);
    List<Agenda> findByMedicoIdAndHorarioDiaAndStatusTrue(Integer medicoId, LocalDate dia);
    List<Agenda> findByMedicoAndHorarioDiaAndStatusTrue(Medico medico, LocalDate dia);
}
