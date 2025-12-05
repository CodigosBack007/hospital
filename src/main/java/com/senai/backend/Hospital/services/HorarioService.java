package com.senai.backend.Hospital.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senai.backend.Hospital.models.Horario;
import com.senai.backend.Hospital.repositories.HorarioRepository;


@Service
public class HorarioService {

    @Autowired
    private HorarioRepository horarioRepository;

    public Horario salvar(Horario horario) {
        return horarioRepository.save(horario);
    }

    public Horario buscarPorId(Integer id) {
        return horarioRepository.findById(id).orElse(null);
    }

    public List<Horario> listarTodos() {
        return horarioRepository.findAll();
    }

    public Long contar() {
        return horarioRepository.count();
    }

    public void remover(Integer id) {
        horarioRepository.deleteById(id);
    }
}
