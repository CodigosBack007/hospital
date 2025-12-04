package com.senai.backend.Hospital.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.senai.backend.Hospital.models.Medico;
import com.senai.backend.Hospital.repositories.MedicoRepository;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepo;

    public MedicoService(MedicoRepository medicoRepo) {
        this.medicoRepo = medicoRepo;
    }

    public Medico salvar(Medico m) {
        LocalDateTime now = LocalDateTime.now();
        m.setDataCriacao(now);
        m.setDataAtualizacao(now);
        if (m.getStatus() == null) m.setStatus(true);
        return medicoRepo.save(m);
    }

    public Medico buscarPorId(Integer id) {
        return medicoRepo.findById(id).orElse(null);
    }

    public List<Medico> listarTodos() {
        return medicoRepo.findAll();
    }

    public Long contar() {
        return medicoRepo.count();
    }

    public void removerPorId(Integer id) {
        medicoRepo.deleteById(id);
    }

    public Medico atualizar(Integer id, Medico novo) {
        Medico atual = medicoRepo.findById(id).orElseThrow(() -> new RuntimeException("Médico não encontrado"));
        atual.setNome(novo.getNome());
        atual.setCompetencia(novo.getCompetencia());
        atual.setCim(novo.getCim());
        atual.setEndereco(novo.getEndereco());
        atual.setContato(novo.getContato());
        atual.setTurno(novo.getTurno());
        atual.setLimiteDiario(novo.getLimiteDiario());
        atual.setStatus(novo.getStatus());
        atual.setDataAtualizacao(LocalDateTime.now());
        return medicoRepo.save(atual);
    }
}
