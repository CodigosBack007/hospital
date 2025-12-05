package com.senai.backend.Hospital.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senai.backend.Hospital.models.Paciente;
import com.senai.backend.Hospital.repositories.PacienteRepository;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    // salvar - POST
    public Paciente salvar(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    // buscar pelo id - GET
    public Paciente buscarPorId(Integer id) {
        return pacienteRepository.findById(id).get();
    }

    // listar todos - GET
    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }

    // contar - GET
    public long contar() {
        return pacienteRepository.count();
    }

    // remover pelo id - DELETE
    public boolean removerPorId(Integer id) {
        Paciente pac = pacienteRepository.findById(id).get();
        if (pac != null) {
            pacienteRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // atualizar - PUT
    public Paciente atualizar(Integer id, Paciente paciente) {
        Paciente pac = pacienteRepository.findById(id).get();
        if (paciente != null) {
            paciente.setId(pac.getId());
            return pacienteRepository.save(paciente);
        }
        return null;
    }

}