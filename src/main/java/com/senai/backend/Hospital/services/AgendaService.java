package com.senai.backend.Hospital.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senai.backend.Hospital.models.Agenda;
import com.senai.backend.Hospital.repositories.AgendaRepository;

@Service
public class AgendaService {

    @Autowired
    private AgendaRepository agendaRepository;

    public Agenda salvar(Agenda agenda) {
        return agendaRepository.save(agenda);
    }

    public Agenda buscarPorId(Integer id) {
        return agendaRepository.findById(id).orElse(null);
    }

    public List<Agenda> listarTodos() {
        return agendaRepository.findAll();
    }

    public Long contar() {
        return agendaRepository.count();
    }

    public void remover(Integer id) {
        agendaRepository.deleteById(id);
    }
}
