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

import com.senai.backend.Hospital.models.Horario;
import com.senai.backend.Hospital.services.HorarioService;

@RestController
@RequestMapping("/horario")
public class HorarioController {

    @Autowired
    private HorarioService horarioService;

    @PostMapping("/salvar")
    public Horario salvar(@RequestBody Horario horario) {
        return horarioService.salvar(horario);
    }

    @GetMapping("/buscar por{id}")
    public Horario buscarPorId(@PathVariable Integer id) {
        return horarioService.buscarPorId(id);
    }

    @GetMapping("/listar")
    public List<Horario> listarTodos() {
        return horarioService.listarTodos();
    }

    @GetMapping("/contar")
    public Long contar() {
        return horarioService.contar();
    }

    @DeleteMapping("/remover por {id}")
    public void remover(@PathVariable Integer id) {
        horarioService.remover(id);
    }
}
