package com.senai.backend.Hospital.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.backend.Hospital.models.Paciente;
import com.senai.backend.Hospital.services.PacienteService;

@RestController
@RequestMapping("/Paciente")
public class PacienteController {
    
    @Autowired
    private PacienteService PacienteService;

    
    // salvar - POST
    @PostMapping("/salvar")
    public Paciente salvar(@RequestBody Paciente Paciente) {
        return PacienteService.salvar(Paciente);
    }

    // buscar pelo id - GET
    @GetMapping("/buscarPorId/{id}")
    public Paciente buscarPorId(@PathVariable Integer id) {
        return PacienteService.buscarPorId(id);
    }

    // listar todos - GET
    @GetMapping("/listar")
    public List<Paciente> listarTodos() {
        return PacienteService.listarTodos();
    }

    // contar - GET
    @GetMapping("/contar")
    public long contar() {
        return PacienteService.contar();
    }

    // remover pelo id - DELETE
    @DeleteMapping("/deletar/{id}")
    public String deletar(@PathVariable Integer id) {
        if (PacienteService.removerPorId(id)) {
            return "Paciente removido com sucesso!";
        }
        return "Falha ao remover Paciente!";
    }

    // atualizar - PUT
    @PutMapping("/atualizar/{id}")
    public Paciente atualizar(@PathVariable Integer id, @RequestBody Paciente Paciente) {
        return PacienteService.atualizar(id, Paciente);
    }

}