package com.senai.backend.Hospital.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senai.backend.Hospital.models.Medico;
import com.senai.backend.Hospital.repositories.MedicoRepository;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public Medico salvar(Medico medico) {
        return medicoRepository.save(medico);
    }

    public Medico buscarPorId(Integer id) {
        return medicoRepository.findById(id).orElse(null);
    }

    public List<Medico> listarTodos() {
        return medicoRepository.findAll();
    }

    public Long contar() {
        return medicoRepository.count();
    }

    public void remover(Integer id) {
        medicoRepository.deleteById(id);
    }
}
