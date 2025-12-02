package com.senai.backend.Hospital.models;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "competencia")
    private String competencia;

    @Column(name = "CIM")
    private String cim;

    @Column(name = "Endereço")
    private String endereco;

    @Column(name = "contato")
    private Integer contato;

     @Column(name = "nome")
    private String nome;

     @Column(name = "turno")
    private String turno; // Manhã / Tarde / Noite


    private Integer limiteDiario = 10;

    private Boolean status = true;

    @Column(name = "Data_Criação")
    private LocalDateTime dataCriacao;

    @Column(name = "Data_Atualizada")
    private LocalDateTime dataAtualizacao;

   @OneToMany
  @JoinColumn(name = "medico_id")
  private List<Horario> horarios;

    
    public Medico() {
    }


    public Medico(String cim, String competencia, Integer contato, LocalDateTime dataAtualizacao, LocalDateTime dataCriacao, String endereco, List<Horario> horarios, Integer id) {
        this.cim = cim;
        this.competencia = competencia;
        this.contato = contato;
        this.dataAtualizacao = dataAtualizacao;
        this.dataCriacao = dataCriacao;
        this.endereco = endereco;
        this.horarios = horarios;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompetencia() {
        return competencia;
    }

    public void setCompetencia(String competencia) {
        this.competencia = competencia;
    }

    public String getCim() {
        return cim;
    }

    public void setCim(String cim) {
        this.cim = cim;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Integer getContato() {
        return contato;
    }

    public void setContato(Integer contato) {
        this.contato = contato;
    }

    public Integer getLimiteDiario() {
        return limiteDiario;
    }

    public void setLimiteDiario(Integer limiteDiario) {
        this.limiteDiario = limiteDiario;
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

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    
}
