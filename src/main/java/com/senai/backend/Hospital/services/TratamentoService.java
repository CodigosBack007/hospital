package com.senai.backend.Hospital.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senai.backend.Hospital.models.Tratamento;
import com.senai.backend.Hospital.repositories.TratamentoRepository;

@Service
public class TratamentoService {

    @Autowired
    private TratamentoRepository tratamentoRepository;

    public Tratamento salvar(Tratamento tratamento) {
        return tratamentoRepository.save(tratamento);
    }

    public Tratamento buscarPorId(Integer id) {
        return tratamentoRepository.findById(id).orElse(null);
    }

    public List<Tratamento> listarTodos() {
        return tratamentoRepository.findAll();
    }

    public Long contar() {
        return tratamentoRepository.count();
    }

    public void remover(Integer id) {
        tratamentoRepository.deleteById(id);
    }
}
