package com.senai.backend.Hospital.controllers;

import com.senai.backend.Hospital.models.Tratamento;
import com.senai.backend.Hospital.services.TratamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tratamentos")
public class TratamentoController {

    private final TratamentoService service;

    public TratamentoController(TratamentoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Tratamento t) {
        try {
            Tratamento criado = service.criar(t);
            return ResponseEntity.ok(criado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Integer id) {
        try {
            return service.buscarAtivo(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Integer id, @RequestBody Tratamento t) {
        try {
            return ResponseEntity.ok(service.atualizar(id, t));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
