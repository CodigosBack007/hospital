package com.senai.backend.Hospital.models;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "agendas")
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;


    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "horario_id")
    private Horario horario;

    @ManyToMany
    @JoinTable(
        name = "agenda_tratamentos",
        joinColumns = @JoinColumn(name = "agenda_id"),
        inverseJoinColumns = @JoinColumn(name = "tratamento_id")
    )
    private Set<Tratamento> tratamentos = new HashSet<>();

  
    @Column(name = "observacoes")
    private String observacoes;

    private Boolean status = true;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao = LocalDateTime.now();

    
    public Agenda() {
    }


    public Agenda(Integer id, Paciente paciente, Medico medico, Horario horario, Set<Tratamento> tratamentos,
            String observacoes, Boolean status, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao) {
        this.id = id;
        this.paciente = paciente;
        this.medico = medico;
        this.horario = horario;
        this.tratamentos = tratamentos;
        this.observacoes = observacoes;
        this.status = true;
        this.dataCriacao = LocalDateTime.now();
        this.dataAtualizacao = LocalDateTime.now();
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public Set<Tratamento> getTratamentos() {
        return tratamentos;
    }

    public void setTratamentos(Set<Tratamento> tratamentos) {
        this.tratamentos = tratamentos;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
}
