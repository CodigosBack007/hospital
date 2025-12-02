package com.senai.backend.Hospital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.senai.backend.Hospital.models.Medico;
import java.util.List;

public interface MedicoRepository extends JpaRepository<Medico, Integer> {
    List<Medico> findByStatusTrue();
}
