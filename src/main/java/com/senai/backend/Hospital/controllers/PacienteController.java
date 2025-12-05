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

import com.senai.backend.Hospital.models.Paciente;
import com.senai.backend.Hospital.services.PacienteService;

@RestController
@RequestMapping("/Pacientes")
public class PacienteController {

    @Autowired
    private PacienteService PacienteService;

    @PostMapping("salvar")
    public Paciente salvar(@RequestBody Paciente Paciente) {
        return PacienteService.salvar(Paciente);
    }

    @GetMapping("/buscar por {id}")
    public Paciente buscarPorId(@PathVariable Integer id) {
        return PacienteService.buscarPorId(id);
    }

    @GetMapping("listar")
    public List<Paciente> listarTodos() {
        return PacienteService.listarTodos();
    }

    @GetMapping("/contar")
    public Long contar() {
        return PacienteService.contar();
    }

    @DeleteMapping("/remover por {id}")
    public void remover(@PathVariable Integer id) {
        PacienteService.remover(id);
    }
}
