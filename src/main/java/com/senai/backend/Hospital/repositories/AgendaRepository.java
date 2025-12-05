package com.senai.backend.Hospital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senai.backend.Hospital.models.Agenda;

public interface AgendaRepository extends JpaRepository<Agenda, Integer> {
    
}
