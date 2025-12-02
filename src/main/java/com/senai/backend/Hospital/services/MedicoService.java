package com.senai.backend.Hospital.services;

import com.senai.backend.Hospital.models.Medico;
import com.senai.backend.Hospital.repositories.MedicoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepo;

    public MedicoService(MedicoRepository medicoRepo) {
        this.medicoRepo = medicoRepo;
    }

    public Medico criar(Medico m) {
        LocalDateTime now = LocalDateTime.now();
        m.setDataCriacao(now);
        m.setDataAtualizacao(now);
        if (m.getStatus() == null) m.setStatus(true);
        return medicoRepo.save(m);
    }

    public Optional<Medico> buscar(Integer id) {
        return medicoRepo.findById(id);
    }

    public List<Medico> listarAtivos() {
        return medicoRepo.findByStatusTrue();
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
        atual.setStatus(novo.getStatus() == null ? atual.getStatus() : novo.getStatus());
        atual.setDataAtualizacao(LocalDateTime.now());
        return medicoRepo.save(atual);
    }
}
