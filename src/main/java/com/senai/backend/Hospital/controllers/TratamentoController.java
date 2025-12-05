package com.senai.backend.Hospital.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/Tratamento")
public class TratamentoController {
    
    @Autowired
    private TratamentoService TratamentoService;

    
    // salvar - POST
    @PostMapping("/salvar")
    public Tratamento salvar(@RequestBody Tratamento Tratamento) {
        return TratamentoService.salvar(Tratamento);
    }

    // buscar pelo id - GET
    @GetMapping("/buscarPorId/{id}")
    public Tratamento buscarPorId(@PathVariable Integer id) {
        return TratamentoService.buscarPorId(id);
    }

    // listar todos - GET
    @GetMapping("/listar")
    public List<Tratamento> listarTodos() {
        return TratamentoService.listarTodos();
    }

    // contar - GET
    @GetMapping("/contar")
    public long contar() {
        return TratamentoService.contar();
    }

    // remover pelo id - DELETE
    @DeleteMapping("/deletar/{id}")
    public String deletar(@PathVariable Integer id) {
        if (TratamentoService.removerPorId(id)) {
            return "Tratamento removido com sucesso!";
        }
        return "Falha ao remover Tratamento!";
    }

    // atualizar - PUT
    @PutMapping("/atualizar/{id}")
    public Tratamento atualizar(@PathVariable Integer id, @RequestBody Tratamento Tratamento) {
        return TratamentoService.atualizar(id, Tratamento);
    }

}