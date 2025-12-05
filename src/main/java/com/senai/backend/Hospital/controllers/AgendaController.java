package com.senai.backend.Hospital.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.backend.Hospital.models.Agenda;
import com.senai.backend.Hospital.services.AgendaService;

@RestController
@RequestMapping("/agendas")
public class AgendaController {

    @Autowired
    private AgendaService agendaService;

    @PostMapping("/salvar")
    public Agenda salvar(@RequestBody Agenda agenda) {
        return agendaService.salvar(agenda);
    }

    @GetMapping("/buscar por {id}")
    public Agenda buscarPorId(@PathVariable Integer id) {
        return agendaService.buscarPorId(id);
    }

    @GetMapping("/listar")
    public List<Agenda> listarTodos() {
        return agendaService.listarTodos();
    }

    @GetMapping("/contar")
    public Long contar() {
        return agendaService.contar();
    }

    @DeleteMapping("/remover por {id}")
    public void remover(@PathVariable Integer id) {
        agendaService.remover(id);
    }
}
