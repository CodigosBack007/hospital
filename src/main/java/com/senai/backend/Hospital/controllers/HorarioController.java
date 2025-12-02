package com.senai.backend.Hospital.controllers;

import com.senai.backend.Hospital.models.Horario;
import com.senai.backend.Hospital.services.HorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/horarios")
public class HorarioController {

    @Autowired
    private HorarioService horarioService;

    @PostMapping("/criar/{medicoId}")
    public Horario criar(@RequestBody Horario horario, @PathVariable Integer medicoId) {
        return horarioService.salvar(horario, medicoId);
    }

    @GetMapping
    public List<Horario> listar() {
        return horarioService.listar();
    }

    @GetMapping("/{id}")
    public Horario buscar(@PathVariable Integer id) {
        return horarioService.buscar(id);
    }

    @DeleteMapping("/{id}")
    public String desativar(@PathVariable Integer id) {
        boolean ok = horarioService.desativar(id);
        return ok ? "Horário desativado" : "Horário não encontrado";
    }
}
