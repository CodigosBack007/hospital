package com.senai.backend.Hospital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.senai.backend.Hospital.models.Paciente;
import java.util.List;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
    List<Paciente> findByStatusTrue();
}
