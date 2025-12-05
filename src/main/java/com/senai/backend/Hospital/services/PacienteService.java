package com.senai.backend.Hospital.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senai.backend.Hospital.models.Paciente;
import com.senai.backend.Hospital.repositories.PacienteRepository;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository cliRepository;

     public Paciente salvar(Paciente Paciente){
        return cliRepository.save(Paciente);
    }

    public Paciente buscarPorId(Integer id) {
        return cliRepository.findById(id).orElse(null);
    }

    public List<Paciente> listarTodos() {
        return cliRepository.findAll();
    }

    public long contar() {
        return cliRepository.count();
    }

    public boolean remover(Integer id) {
        if (!cliRepository.existsById(id)) return false;
        cliRepository.deleteById(id);
        return true;
    }
}
