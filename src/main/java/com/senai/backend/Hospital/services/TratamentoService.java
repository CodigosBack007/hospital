package com.senai.backend.Hospital.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.senai.backend.Hospital.models.Tratamento;
import com.senai.backend.Hospital.repositories.TratamentoRepository;

@Service
public class TratamentoService {

    private final TratamentoRepository repo;

    public TratamentoService(TratamentoRepository repo) {
        this.repo = repo;
    }

    public Tratamento salvar(Tratamento t) {
        LocalDateTime now = LocalDateTime.now();
        t.setDataCriacao(now);
        t.setDataAtualizacao(now);
        if (t.getStatus() == null) t.setStatus(true);
        return repo.save(t);
    }

    public Tratamento buscarPorId(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public List<Tratamento> listarTodos() {
        return repo.findAll();
    }

    public Long contar() {
        return repo.count();
    }

    public void removerPorId(Integer id) {
        repo.deleteById(id);
    }

    public Tratamento atualizar(Integer id, Tratamento novo) {
        Tratamento atual = repo.findById(id).orElseThrow(() -> new RuntimeException("Tratamento não encontrado"));
        atual.setDescricao(novo.getDescricao());
        atual.setCusto(novo.getCusto());
        atual.setCategoria(novo.getCategoria());
        actualizarStatusENovasDatas(atual, novo);
        return repo.save(atual);
    }

    private void actualizarStatusENovasDatas(Tratamento atual, Tratamento novo) {
        atual.setStatus(novo.getStatus());
        atual.setDataAtualizacao(LocalDateTime.now());
    }
}
