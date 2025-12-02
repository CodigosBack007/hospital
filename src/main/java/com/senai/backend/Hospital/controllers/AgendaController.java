package com.senai.backend.Hospital.controllers;

import com.senai.backend.Hospital.models.Agenda;
import com.senai.backend.Hospital.services.AgendaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/agendas")
public class AgendaController {

    private final AgendaService service;

    public AgendaController(AgendaService service) {
        this.service = service;
    }

    // DTO simples
    public static class AgendaCreateDTO {
        public Integer pacienteId;
        public Integer medicoId;
        public LocalDateTime dataHora;
        public Set<Integer> tratamentoIds;
        public String observacoes;
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody AgendaCreateDTO dto) {
        try {
            Agenda a = service.criarAgenda(dto.pacienteId, dto.medicoId, dto.dataHora, dto.tratamentoIds, dto.observacoes);
            return ResponseEntity.ok(a);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Integer id) {
        return service.buscar(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Agenda>> listarAtivos() {
        return ResponseEntity.ok(service.listarAtivos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> desativar(@PathVariable Integer id) {
        try {
            service.desativarAgenda(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
