package com.senai.backend.Hospital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.senai.backend.Hospital.models.Tratamento;
import java.util.Optional;

public interface TratamentoRepository extends JpaRepository<Tratamento, Integer> {
    Optional<Tratamento> findByIdAndStatusTrue(Integer id);
}
