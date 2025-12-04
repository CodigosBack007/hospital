package com.senai.backend.Hospital.services;

import com.senai.backend.Hospital.models.*;
import com.senai.backend.Hospital.repositories.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class AgendaService {

    private final AgendaRepository agendaRepo;
    private final PacienteRepository pacienteRepo;
    private final MedicoRepository medicoRepo;
    private final TratamentoRepository tratamentoRepo;
    private final HorarioRepository horarioRepo;

    public AgendaService(AgendaRepository agendaRepo,
                         PacienteRepository pacienteRepo,
                         MedicoRepository medicoRepo,
                         TratamentoRepository tratamentoRepo,
                         HorarioRepository horarioRepo) {
        this.agendaRepo = agendaRepo;
        this.pacienteRepo = pacienteRepo;
        this.medicoRepo = medicoRepo;
        this.tratamentoRepo = tratamentoRepo;
        this.horarioRepo = horarioRepo;
    }

    // salvar: recebe Agenda (apenas observacoes) + ids
    public Agenda salvar(Agenda agendaInput, Integer pacienteId, Integer medicoId, Integer horarioId, Set<Integer> tratamentoIds) {
        LocalDateTime agora = LocalDateTime.now();

        Paciente paciente = pacienteRepo.findById(pacienteId).orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
        if (!Boolean.TRUE.equals(paciente.getStatus())) throw new IllegalArgumentException("Paciente inativo.");

        Medico medico = medicoRepo.findById(medicoId).orElseThrow(() -> new RuntimeException("Médico não encontrado"));
        if (!Boolean.TRUE.equals(medico.getStatus())) throw new IllegalArgumentException("Médico inativo.");

        Horario horario = horarioRepo.findById(horarioId).orElseThrow(() -> new RuntimeException("Horário não encontrado"));
        if (!Boolean.TRUE.equals(horario.getStatus())) throw new IllegalArgumentException("Horário inativo.");
        if (!horario.getMedico().getId().equals(medicoId)) throw new IllegalArgumentException("Horário não pertence ao médico informado.");

        // não permitir agendar no passado (com base em dia+horaInicial)
        LocalDate horarioDia = horario.getDia();
        if (horarioDia.isBefore(agora.toLocalDate())) throw new IllegalArgumentException("Não é permitido agendar para dia passado.");
        if (horarioDia.isEqual(agora.toLocalDate()) && horario.getHoraInicial().isBefore(agora.toLocalTime())) throw new IllegalArgumentException("Não é permitido agendar para horário passado.");

        // não permitir usar horario já ocupado (verificar se já existe agenda com esse horario)
        List<Agenda> agendasComHorario = agendaRepo.findByHorarioIdAndStatusTrue(horarioId);
        if (!agendasComHorario.isEmpty()) throw new IllegalArgumentException("Horário já ocupado.");

        // máximo 10 tratamentos
        if (tratamentoIds != null && tratamentoIds.size() > 10) throw new IllegalArgumentException("Máximo 10 tratamentos por agendamento.");

        Set<Tratamento> tratamentos = new HashSet<>();
        if (tratamentoIds != null) {
            for (Integer tid : tratamentoIds) {
                Tratamento t = tratamentoRepo.findByIdAndStatusTrue(tid).orElseThrow(() -> new RuntimeException("Tratamento não encontrado ou inativo: " + tid));
                tratamentos.add(t);
            }
        }

        // regra: médico não pode ter dois agendamentos na mesma data
        LocalDate dia = horario.getDia();
        List<Agenda> agendasDoDia = agendaRepo.findByMedicoIdAndHorarioDiaAndStatusTrue(medicoId, dia);
        if (!agendasDoDia.isEmpty()) throw new IllegalArgumentException("Médico já possui agendamento nessa data.");

        // montar agenda
        Agenda a = new Agenda();
        a.setPaciente(paciente);
        a.setMedico(medico);
        a.setHorario(horario);
        a.setTratamentos(tratamentos);
        a.setObservacoes(agendaInput.getObservacoes());
        a.setStatus(true);
        a.setDataCriacao(LocalDateTime.now());
        a.setDataAtualizacao(LocalDateTime.now());

        Agenda salvo = agendaRepo.save(a);
        return salvo;
    }

    public Agenda buscarPorId(Integer id) {
        return agendaRepo.findById(id).orElse(null);
    }

    public List<Agenda> listarTodos() {
        return agendaRepo.findAll();
    }

    public Long contar() {
        return agendaRepo.count();
    }

    public void removerPorId(Integer id) {
        agendaRepo.deleteById(id);
    }

    // atualização total (PUT)
    public Agenda atualizar(Integer id, Agenda novo) {
        Agenda atual = agendaRepo.findById(id).orElseThrow(() -> new RuntimeException("Agenda não encontrada"));

        // Para atualização total aceitamos que o cliente forneça objetos Paciente, Medico e Horario com IDs válidos.
        // Validar paciente
        if (novo.getPaciente() == null || novo.getPaciente().getId() == null)
            throw new IllegalArgumentException("Paciente obrigatório com id");
        Paciente paciente = pacienteRepo.findById(novo.getPaciente().getId()).orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
        if (!Boolean.TRUE.equals(paciente.getStatus())) throw new IllegalArgumentException("Paciente inativo.");

        // Validar medico
        if (novo.getMedico() == null || novo.getMedico().getId() == null)
            throw new IllegalArgumentException("Médico obrigatório com id");
        Medico medico = medicoRepo.findById(novo.getMedico().getId()).orElseThrow(() -> new RuntimeException("Médico não encontrado"));
        if (!Boolean.TRUE.equals(medico.getStatus())) throw new IllegalArgumentException("Médico inativo.");

        // Validar horario
        if (novo.getHorario() == null || novo.getHorario().getId() == null)
            throw new IllegalArgumentException("Horário obrigatório com id");
        Horario novoHorario = horarioRepo.findById(novo.getHorario().getId()).orElseThrow(() -> new RuntimeException("Horário não encontrado"));
        if (!Boolean.TRUE.equals(novoHorario.getStatus())) throw new IllegalArgumentException("Horário inativo.");
        if (!novoHorario.getMedico().getId().equals(medico.getId())) throw new IllegalArgumentException("Horário não pertence ao médico.");

        // Verificar se novoHorario já está ocupado por outra agenda diferente
        List<Agenda> agendasComHorario = agendaRepo.findByHorarioIdAndStatusTrue(novoHorario.getId());
        boolean ocupadoPorOutro = agendasComHorario.stream().anyMatch(a -> !a.getId().equals(atual.getId()));
        if (ocupadoPorOutro) throw new IllegalArgumentException("Novo horário já ocupado.");

        // aplicar atualização total
        atual.setPaciente(paciente);
        atual.setMedico(medico);
        atual.setHorario(novoHorario);
        atual.setTratamentos(novo.getTratamentos());
        atual.setObservacoes(novo.getObservacoes());
        atual.setStatus(novo.getStatus());
        atual.setDataAtualizacao(LocalDateTime.now());

        return agendaRepo.save(atual);
    }
}
