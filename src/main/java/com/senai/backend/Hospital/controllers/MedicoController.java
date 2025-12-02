package com.senai.backend.Hospital.controllers;

import com.senai.backend.Hospital.models.Medico;
import com.senai.backend.Hospital.services.MedicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    private final MedicoService service;

    public MedicoController(MedicoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Medico m) {
        try {
            Medico criado = service.criar(m);
            return ResponseEntity.ok(criado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Integer id) {
        return service.buscar(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Medico>> listarAtivos() {
        return ResponseEntity.ok(service.listarAtivos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Integer id, @RequestBody Medico m) {
        try {
            Medico upd = service.atualizar(id, m);
            return ResponseEntity.ok(upd);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
