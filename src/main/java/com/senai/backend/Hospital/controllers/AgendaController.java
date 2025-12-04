package com.senai.backend.Hospital.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.backend.Hospital.models.Agenda;
import com.senai.backend.Hospital.services.AgendaService;

@RestController
@RequestMapping("/api/agendas")
public class AgendaController {

    private final AgendaService service;

    public AgendaController(AgendaService service) {
        this.service = service;
    }

    // DTO para criar agenda (usa horarioId)
    public static class AgendaCreateDTO {
        public Integer pacienteId;
        public Integer medicoId;
        public Integer horarioId;
        public Set<Integer> tratamentoIds;
        public String observacoes;
    }

    @PostMapping("salvar")
    public ResponseEntity<?> criarAgenda(@RequestBody AgendaCreateDTO dto) {
        try {
            Agenda a = new Agenda();
            a.setObservacoes(dto.observacoes);
            Agenda salvo = service.salvar(a, dto.pacienteId, dto.medicoId, dto.horarioId, dto.tratamentoIds);
            return ResponseEntity.ok(salvo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/buscar por {id}")
    public ResponseEntity<?> buscarAgendaPorId(@PathVariable Integer id) {
        Agenda a = service.buscarPorId(id);
        return a == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(a);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Agenda>> listarAgendas() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/count")
    public ResponseEntity<Long> contarAgendas() {
        return ResponseEntity.ok(service.contar());
    }

    @DeleteMapping("/remover por {id}")
    public ResponseEntity<?> removerAgendaPorId(@PathVariable Integer id) {
        try { service.removerPorId(id); return ResponseEntity.noContent().build(); }
        catch (Exception e) { return ResponseEntity.status(500).body(e.getMessage()); }
    }

    @PutMapping("/atualizar por {id}")
    public ResponseEntity<?> atualizarAgendaPorId(@PathVariable Integer id, @RequestBody Agenda a) {
        try { return ResponseEntity.ok(service.atualizar(id, a)); }
        catch (Exception e) { return ResponseEntity.badRequest().body(e.getMessage()); }
    }
}
