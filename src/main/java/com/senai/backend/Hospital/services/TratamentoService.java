package com.senai.backend.Hospital.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senai.backend.Hospital.models.Tratamento;
import com.senai.backend.Hospital.repositories.TratamentoRepository;

@Service
public class TratamentoService {

    @Autowired
    private TratamentoRepository TratamentoRepository;

    // salvar - POST
    public Tratamento salvar(Tratamento Tratamento) {
        return TratamentoRepository.save(Tratamento);
    }

    // buscar pelo id - GET
    public Tratamento buscarPorId(Integer id) {
        return TratamentoRepository.findById(id).get();
    }

    // listar todos - GET
    public List<Tratamento> listarTodos() {
        return TratamentoRepository.findAll();
    }

    // contar - GET
    public long contar() {
        return TratamentoRepository.count();
    }

    // remover pelo id - DELETE
    public boolean removerPorId(Integer id) {
        Tratamento pac = TratamentoRepository.findById(id).get();
        if (pac != null) {
            TratamentoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // atualizar - PUT
    public Tratamento atualizar(Integer id, Tratamento Tratamento) {
        Tratamento pac = TratamentoRepository.findById(id).get();
        if (Tratamento != null) {
            Tratamento.setId(pac.getId());
            return TratamentoRepository.save(Tratamento);
        }
        return null;
    }

}
