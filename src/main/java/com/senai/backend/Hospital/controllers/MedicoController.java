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

import com.senai.backend.Hospital.models.Medico;
import com.senai.backend.Hospital.services.MedicoService;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @PostMapping("salvar")
    public Medico salvar(@RequestBody Medico medico) {
        return medicoService.salvar(medico);
    }

    @GetMapping("/buscar por {id}")
    public Medico buscarPorId(@PathVariable Integer id) {
        return medicoService.buscarPorId(id);
    }

    @GetMapping("/listar")
    public List<Medico> listarTodos() {
        return medicoService.listarTodos();
    }

    @GetMapping("/contar")
    public Long contar() {
        return medicoService.contar();
    }

    @DeleteMapping("/remover por {id}")
    public void remover(@PathVariable Integer id) {
        medicoService.remover(id);
    }
}
