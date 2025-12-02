package com.senai.backend.Hospital.models;

import java.time.LocalDateTime;
import java.util.Set;

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
@Table
public class Agenda {
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
   private Integer id;

   @Column(name = "tratamento")
   private String tratamento;

   @Column(name = "consulta")
   private String consulta;

   @Column(name = "custo")
   private Double custo;
   
   @Column(name = "categoria")
   private String categoria;

   @Column(name = "descrição")
   private String descricao;

   @Column(name = "data_hora")
  private LocalDateTime dataHora;

  @Column(name = "observacoes")
  private String observacoes;


   @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @ManyToMany
    @JoinTable(
        name = "agenda_tratamentos",
        joinColumns = @JoinColumn(name = "agenda_id"),
        inverseJoinColumns = @JoinColumn(name = "tratamento_id")
    )
    private Set<Tratamento> tratamentos;

    private Boolean status = true;

    @Column(name = "Data_Criação")
    private LocalDateTime dataCriacao;

    @Column(name = "Data_Atualizada")
    private LocalDateTime dataAtualizacao;

    public Agenda() {
    }

    public Agenda(String categoria, String consulta, Double custo, LocalDateTime dataAtualizacao, LocalDateTime dataCriacao, String descricao, Integer id, Medico medico, Paciente paciente, String tratamento, Set<Tratamento> tratamentos) {
        this.categoria = categoria;
        this.consulta = consulta;
        this.custo = custo;
        this.dataAtualizacao = dataAtualizacao;
        this.dataCriacao = dataCriacao;
        this.descricao = descricao;
        this.id = id;
        this.medico = medico;
        this.paciente = paciente;
        this.tratamento = tratamento;
        this.tratamentos = tratamentos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTratamento() {
        return tratamento;
    }

    public void setTratamento(String tratamento) {
        this.tratamento = tratamento;
    }

    public String getConsulta() {
        return consulta;
    }

    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }

    public Double getCusto() {
        return custo;
    }

    public void setCusto(Double custo) {
        this.custo = custo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public Set<Tratamento> getTratamentos() {
        return tratamentos;
    }

    public void setTratamentos(Set<Tratamento> tratamentos) {
        this.tratamentos = tratamentos;
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

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }


}
