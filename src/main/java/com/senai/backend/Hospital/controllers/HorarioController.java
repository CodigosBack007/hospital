package com.senai.backend.Hospital.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.backend.Hospital.models.Horario;
import com.senai.backend.Hospital.services.HorarioService;

@RestController
@RequestMapping("/api/horarios")
public class HorarioController {

    private final HorarioService service;

    public HorarioController(HorarioService service) {
        this.service = service;
    }

    @PostMapping("/salvar")
    public ResponseEntity<?> salvarHorarioParaMedico(@PathVariable Integer medicoId, @RequestBody Horario h) {
        try { return ResponseEntity.ok(service.salvar(h, medicoId)); }
        catch (Exception e) { return ResponseEntity.badRequest().body(e.getMessage()); }
    }

    @GetMapping("/buscar por {id}")
    public ResponseEntity<?> buscarHorarioPorId(@PathVariable Integer id) {
        Horario h = service.buscarPorId(id);
        return h == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(h);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Horario>> listarHorarios() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/count")
    public ResponseEntity<Long> contarHorarios() {
        return ResponseEntity.ok(service.contar());
    }

    @DeleteMapping("/remover por {id}")
    public ResponseEntity<?> removerHorarioPorId(@PathVariable Integer id) {
        try { service.removerPorId(id); return ResponseEntity.noContent().build(); }
        catch (Exception e) { return ResponseEntity.status(500).body(e.getMessage()); }
    }

    @PutMapping("/atualizar por {id}")
    public ResponseEntity<?> atualizarHorarioPorId(@PathVariable Integer id, @RequestBody Horario h) {
        try { return ResponseEntity.ok(service.atualizar(id, h)); }
        catch (Exception e) { return ResponseEntity.badRequest().body(e.getMessage()); }
    }
}
