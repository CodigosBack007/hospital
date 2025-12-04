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

import com.senai.backend.Hospital.models.Tratamento;
import com.senai.backend.Hospital.services.TratamentoService;

@RestController
@RequestMapping("/api/tratamentos")
public class TratamentoController {

    private final TratamentoService service;

    public TratamentoController(TratamentoService service) {
        this.service = service;
    }

    @PostMapping("/salvar")
    public ResponseEntity<?> salvarTratamento(@RequestBody Tratamento t) {
        try { return ResponseEntity.ok(service.salvar(t)); }
        catch (Exception e) { return ResponseEntity.badRequest().body(e.getMessage()); }
    }

    @GetMapping("/buscar por {id}")
    public ResponseEntity<?> buscarTratamentoPorId(@PathVariable Integer id) {
        Tratamento t = service.buscarPorId(id);
        return t == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(t);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Tratamento>> listarTratamentos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/count")
    public ResponseEntity<Long> contarTratamentos() {
        return ResponseEntity.ok(service.contar());
    }

    @DeleteMapping("/remover por {id}")
    public ResponseEntity<?> removerTratamentoPorId(@PathVariable Integer id) {
        try { service.removerPorId(id); return ResponseEntity.noContent().build(); }
        catch (Exception e) { return ResponseEntity.status(500).body(e.getMessage()); }
    }

    @PutMapping("/atualizar por {id}")
    public ResponseEntity<?> atualizarTratamentoPorId(@PathVariable Integer id, @RequestBody Tratamento t) {
        try { return ResponseEntity.ok(service.atualizar(id, t)); }
        catch (Exception e) { return ResponseEntity.badRequest().body(e.getMessage()); }
    }
}
