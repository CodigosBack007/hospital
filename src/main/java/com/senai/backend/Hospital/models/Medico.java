package com.senai.backend.Hospital.models;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "medicos")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name="id")
    private Integer id;

     @Column(name="nome")
    private String nome;

     @Column(name="competencia")
    private String competencia;

     @Column(name="CIM")
    private String cim;

     @Column(name="endereço")
    private String endereco;

     @Column(name="contato")
    private String contato;

     @Column(name="turno")
    private String turno;

    private Integer limiteDiario = 10;

    private Boolean status = true;

     @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
     @Column(name="data_criação")
    private LocalDateTime dataCriacao;

     @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
     @Column(name="data_atualização")
    private LocalDateTime dataAtualizacao;

    @OneToMany
    @JoinColumn(name = "medico_id")
    private List<Horario> horarios;

    public Medico() {
    }

    
    public Medico(Integer id, String nome, String competencia, String cim, String endereco, String contato,
            String turno, Integer limiteDiario, Boolean status, LocalDateTime dataCriacao,
            LocalDateTime dataAtualizacao, List<Horario> horarios) {
        this.id = id;
        this.nome = nome;
        this.competencia = competencia;
        this.cim = cim;
        this.endereco = endereco;
        this.contato = contato;
        this.turno = turno;
        this.limiteDiario = limiteDiario;
        this.status = true;
        this.dataCriacao = LocalDateTime.now();
        this.dataAtualizacao = LocalDateTime.now();
        this.horarios = horarios;
    }


    // getters / setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCompetencia() { return competencia; }
    public void setCompetencia(String competencia) { this.competencia = competencia; }

    public String getCim() { return cim; }
    public void setCim(String cim) { this.cim = cim; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getContato() { return contato; }
    public void setContato(String contato) { this.contato = contato; }

    public String getTurno() { return turno; }
    public void setTurno(String turno) { this.turno = turno; }

    public Integer getLimiteDiario() { return limiteDiario; }
    public void setLimiteDiario(Integer limiteDiario) { this.limiteDiario = limiteDiario; }

    public Boolean getStatus() { return status; }
    public void setStatus(Boolean status) { this.status = status; }

    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }

    public LocalDateTime getDataAtualizacao() { return dataAtualizacao; }
    public void setDataAtualizacao(LocalDateTime dataAtualizacao) { this.dataAtualizacao = dataAtualizacao; }

    public List<Horario> getHorarios() { return horarios; }
    public void setHorarios(List<Horario> horarios) { this.horarios = horarios; }
}
