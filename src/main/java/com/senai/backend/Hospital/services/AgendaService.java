package com.senai.backend.Hospital.services;

import com.senai.backend.Hospital.models.*;
import com.senai.backend.Hospital.repositories.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Service
public class AgendaService {

    private final AgendaRepository agendaRepo;
    private final PacienteRepository pacienteRepo;
    private final MedicoRepository medicoRepo;
    private final TratamentoRepository tratamentoRepo;

    public AgendaService(AgendaRepository agendaRepo, PacienteRepository pacienteRepo,
                         MedicoRepository medicoRepo, TratamentoRepository tratamentoRepo) {
        this.agendaRepo = agendaRepo;
        this.pacienteRepo = pacienteRepo;
        this.medicoRepo = medicoRepo;
        this.tratamentoRepo = tratamentoRepo;
    }

    // criar agenda aplicando regras
    public Agenda criarAgenda(Integer pacienteId, Integer medicoId, LocalDateTime dataHora, Set<Integer> tratamentoIds, String observacoes) {
        LocalDateTime agora = LocalDateTime.now();

        // Regra 3: não agendar no passado
        if (dataHora.isBefore(agora)) {
            throw new IllegalArgumentException("Não é permitido agendar para horário passado.");
        }

        Paciente paciente = pacienteRepo.findById(pacienteId).orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
        if (!Boolean.TRUE.equals(paciente.getStatus())) {
            throw new IllegalArgumentException("Paciente inativo não pode agendar.");
        }

        Medico medico = medicoRepo.findById(medicoId).orElseThrow(() -> new RuntimeException("Médico não encontrado"));
        if (!Boolean.TRUE.equals(medico.getStatus())) {
            throw new IllegalArgumentException("Médico inativo não pode ser atribuído.");
        }

        // Regra 4: médico não pode ter dois agendamentos na mesma data
        LocalDate dia = dataHora.toLocalDate();
        LocalDateTime start = dia.atStartOfDay();
        LocalDateTime end = dia.atTime(LocalTime.MAX);
        List<Agenda> agendasDoDia = agendaRepo.findByMedicoAndDataHoraBetweenAndStatusTrue(medico, start, end);
        if (!agendasDoDia.isEmpty()) {
            // regra estrita: não permitir outro agendamento na mesma data
            throw new IllegalArgumentException("Médico já possui agendamento nessa data.");
        }

        // Regra 9: máximo 10 tratamentos por agendamento
        if (tratamentoIds != null && tratamentoIds.size() > 10) {
            throw new IllegalArgumentException("Máximo de 10 tratamentos por agendamento.");
        }

        Set<Tratamento> tratamentos = new HashSet<>();
        if (tratamentoIds != null) {
            for (Integer tid : tratamentoIds) {
                Tratamento t = tratamentoRepo.findByIdAndStatusTrue(tid).orElseThrow(() -> new RuntimeException("Tratamento não encontrado ou inativo: " + tid));
                tratamentos.add(t);
            }
        }

        Agenda a = new Agenda();
        a.setPaciente(paciente);
        a.setMedico(medico);
        a.setDataHora(dataHora);
        a.setTratamentos(tratamentos);
        a.setObservacoes(observacoes);
        a.setStatus(true);
        a.setDataCriacao(LocalDateTime.now());
        a.setDataAtualizacao(LocalDateTime.now());

        return agendaRepo.save(a);
    }

    public Optional<Agenda> buscar(Integer id) {
        return agendaRepo.findById(id);
    }

    public List<Agenda> listarAtivos() {
        return agendaRepo.findByStatusTrue();
    }

    public Agenda desativarAgenda(Integer id) {
        Agenda a = agendaRepo.findById(id).orElseThrow(() -> new RuntimeException("Agenda não encontrada"));
        a.setStatus(false);
        a.setDataAtualizacao(LocalDateTime.now());
        return agendaRepo.save(a);
    }
}
