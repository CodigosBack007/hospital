package com.senai.backend.Hospital.services;

import com.senai.backend.Hospital.models.Agenda;
import com.senai.backend.Hospital.models.Paciente;
import com.senai.backend.Hospital.repositories.AgendaRepository;
import com.senai.backend.Hospital.repositories.PacienteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepo;
    private final AgendaRepository agendaRepo;

    public PacienteService(PacienteRepository pacienteRepo, AgendaRepository agendaRepo) {
        this.pacienteRepo = pacienteRepo;
        this.agendaRepo = agendaRepo;
    }

    public Paciente criar(Paciente p) {
        LocalDateTime now = LocalDateTime.now();
        p.setDataCriacao(now);
        p.setDataAtualizacao(now);
        if (p.getStatus() == null) p.setStatus(true);
        return pacienteRepo.save(p);
    }

    public Optional<Paciente> buscar(Integer id) {
        return pacienteRepo.findById(id);
    }

    public List<Paciente> listarAtivos() {
        return pacienteRepo.findByStatusTrue();
    }

    public Paciente atualizar(Integer id, Paciente novo) {
        Paciente atual = pacienteRepo.findById(id).orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
        atual.setNome(novo.getNome());
        atual.setEndereco(novo.getEndereco());
        atual.setContato(novo.getContato());
        atual.setDescricao(novo.getDescricao());
        atual.setStatus(novo.getStatus() == null ? atual.getStatus() : novo.getStatus());
        atual.setDataAtualizacao(LocalDateTime.now());
        return pacienteRepo.save(atual);
    }

    // Regra 10: ao desativar paciente, futuros agendamentos desse paciente são desativados
    public void desativar(Integer id) {
        Paciente p = pacienteRepo.findById(id).orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
        p.setStatus(false);
        p.setDataAtualizacao(LocalDateTime.now());
        pacienteRepo.save(p);

        // iterar agendas ativas do paciente e desativar as futuras
        List<Agenda> agendas = agendaRepo.findByPacienteIdAndStatusTrue(id);
        LocalDateTime agora = LocalDateTime.now();
        for (Agenda a : agendas) {
            if (a.getDataHora() != null && a.getDataHora().isAfter(agora)) {
                a.setStatus(false);
                a.setDataAtualizacao(LocalDateTime.now());
                agendaRepo.save(a);
            }
        }
    }
}
