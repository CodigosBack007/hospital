package com.senai.backend.Hospital.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senai.backend.Hospital.models.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Integer> {
    List<Medico> findByStatusTrue();
}
