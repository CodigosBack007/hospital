package com.senai.backend.Hospital.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senai.backend.Hospital.models.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
    List<Paciente> findByStatusTrue();
}
