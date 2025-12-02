package com.senai.backend.Hospital.services;

import com.senai.backend.Hospital.models.Tratamento;
import com.senai.backend.Hospital.repositories.TratamentoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TratamentoService {

    private final TratamentoRepository repo;

    public TratamentoService(TratamentoRepository repo) {
        this.repo = repo;
    }

    public Tratamento criar(Tratamento t) {
        LocalDateTime now = LocalDateTime.now();
        t.setDataCriacao(now);
        t.setDataAtualizacao(now);
        if (t.getStatus() == null) t.setStatus(true);
        return repo.save(t);
    }

    public Optional<Tratamento> buscarAtivo(Integer id) {
        return repo.findByIdAndStatusTrue(id);
    }

    public Tratamento atualizar(Integer id, Tratamento novo) {
        Tratamento atual = repo.findById(id).orElseThrow(() -> new RuntimeException("Tratamento não encontrado"));
        atual.setDescricao(novo.getDescricao());
        atual.setCusto(novo.getCusto());
        atual.setCategoria(novo.getCategoria());
        atual.setStatus(novo.getStatus() == null ? atual.getStatus() : novo.getStatus());
        atual.setDataAtualizacao(LocalDateTime.now());
        return repo.save(atual);
    }
}
