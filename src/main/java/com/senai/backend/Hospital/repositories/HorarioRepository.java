package com.senai.backend.Hospital.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.senai.backend.Hospital.models.Horario;

public interface HorarioRepository extends JpaRepository<Horario, Integer> {
   
}
