package com.senai.backend.Hospital.models;

import java.time.LocalDateTime;
import java.util.List;

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
    private Integer id;

    private String nome;
    private String competencia;
    private String cim;
    private String endereco;
    private String contato;
    private String turno;
    private Integer limiteDiario = 10;

    private Boolean status = true;

    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

    @OneToMany
    @JoinColumn(name = "medico_id")
    private List<Horario> horarios;

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
