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

import com.senai.backend.Hospital.models.Paciente;
import com.senai.backend.Hospital.services.PacienteService;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    private final PacienteService service;

    public PacienteController(PacienteService service) {
        this.service = service;
    }

    @PostMapping("/salvar")
    public ResponseEntity<?> salvarPaciente(@RequestBody Paciente p) {
        try { return ResponseEntity.ok(service.salvar(p)); }
        catch (Exception e) { return ResponseEntity.badRequest().body(e.getMessage()); }
    }

    @GetMapping("/buscar por {id}")
    public ResponseEntity<?> buscarPacientePorId(@PathVariable Integer id) {
        Paciente p = service.buscarPorId(id);
        return p == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(p);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Paciente>> listarPacientes() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/count")
    public ResponseEntity<Long> contarPacientes() {
        return ResponseEntity.ok(service.contar());
    }

    @DeleteMapping("/Remover por {id}")
    public ResponseEntity<?> removerPacientePorId(@PathVariable Integer id) {
        try { service.removerPorId(id); return ResponseEntity.noContent().build(); }
        catch (Exception e) { return ResponseEntity.status(500).body(e.getMessage()); }
    }

    @PutMapping("/Atualizar por {id}")
    public ResponseEntity<?> atualizarPacientePorId(@PathVariable Integer id, @RequestBody Paciente p) {
        try { return ResponseEntity.ok(service.atualizar(id, p)); }
        catch (Exception e) { return ResponseEntity.badRequest().body(e.getMessage()); }
    }
}
