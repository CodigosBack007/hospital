package com.senai.backend.Hospital.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senai.backend.Hospital.models.Tratamento;

public interface TratamentoRepository extends JpaRepository<Tratamento, Integer> {
    Optional<Tratamento> findByIdAndStatusTrue(Integer id);
}
