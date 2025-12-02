package com.senai.backend.Hospital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.senai.backend.Hospital.models.Agenda;
import com.senai.backend.Hospital.models.Medico;
import java.time.LocalDateTime;
import java.util.List;

public interface AgendaRepository extends JpaRepository<Agenda, Integer> {
    List<Agenda> findByMedicoAndDataHoraBetweenAndStatusTrue(Medico medico, LocalDateTime start, LocalDateTime end);
    List<Agenda> findByPacienteIdAndStatusTrue(Integer pacienteId);
    List<Agenda> findByStatusTrue();
}
