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

import com.senai.backend.Hospital.models.Medico;
import com.senai.backend.Hospital.services.MedicoService;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    private final MedicoService service;

    public MedicoController(MedicoService service) {
        this.service = service;
    }

    @PostMapping("/salvar")
    public ResponseEntity<?> salvarMedico(@RequestBody Medico m) {
        try { return ResponseEntity.ok(service.salvar(m)); }
        catch (Exception e) { return ResponseEntity.badRequest().body(e.getMessage()); }
    }

    @GetMapping("/buscar por {id}")
    public ResponseEntity<?> buscarMedicoPorId(@PathVariable Integer id) {
        Medico m = service.buscarPorId(id);
        return m == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(m);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Medico>> listarMedicos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/count")
    public ResponseEntity<Long> contarMedicos() {
        return ResponseEntity.ok(service.contar());
    }

    @DeleteMapping("/remover por {id}")
    public ResponseEntity<?> removerMedicoPorId(@PathVariable Integer id) {
        try { service.removerPorId(id); return ResponseEntity.noContent().build(); }
        catch (Exception e) { return ResponseEntity.status(500).body(e.getMessage()); }
    }

    @PutMapping("/atualizar por {id}")
    public ResponseEntity<?> atualizarMedicoPorId(@PathVariable Integer id, @RequestBody Medico m) {
        try { return ResponseEntity.ok(service.atualizar(id, m)); }
        catch (Exception e) { return ResponseEntity.badRequest().body(e.getMessage()); }
    }
}
