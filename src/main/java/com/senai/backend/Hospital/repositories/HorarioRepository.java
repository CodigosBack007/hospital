package com.senai.backend.Hospital.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senai.backend.Hospital.models.Horario;

public interface HorarioRepository extends JpaRepository<Horario, Integer> {
    List<Horario> findByStatusTrue();
    List<Horario> findByMedicoId(Integer medicoId);
    List<Horario> findByMedicoIdAndDia(Integer medicoId, LocalDate dia);
}
