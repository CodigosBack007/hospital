package com.senai.backend.Hospital.repositories;

import com.senai.backend.Hospital.models.Horario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface HorarioRepository extends JpaRepository<Horario, Integer> {
    List<Horario> findByStatusTrue();
    List<Horario> findByMedicoId(Integer medicoId);
    List<Horario> findByDia(LocalDate dia);
    List<Horario> findByMedicoIdAndDia(Integer medicoId, LocalDate dia);
}
