package com.senai.backend.Hospital.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.backend.Hospital.models.Tratamento;
import com.senai.backend.Hospital.services.TratamentoService;

@RestController
@RequestMapping("/tratamentos")
public class TratamentoController {

    @Autowired
    private TratamentoService tratamentoService;

    @PostMapping("/salvar")
    public Tratamento salvar(@RequestBody Tratamento tratamento) {
        return tratamentoService.salvar(tratamento);
    }

    @GetMapping("/buscar por {id}")
    public Tratamento buscarPorId(@PathVariable Integer id) {
        return tratamentoService.buscarPorId(id);
    }

    @GetMapping("/listar")
    public List<Tratamento> listarTodos() {
        return tratamentoService.listarTodos();
    }

    @GetMapping("/contar")
    public Long contar() {
        return tratamentoService.contar();
    }

    @DeleteMapping("/remover por {id}")
    public void remover(@PathVariable Integer id) {
        tratamentoService.remover(id);
    }
}
