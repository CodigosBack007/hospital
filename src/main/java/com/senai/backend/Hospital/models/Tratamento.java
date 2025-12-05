package com.senai.backend.Hospital.models;

import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tratamentos")
public class Tratamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="descrição")
    private String descricao;

    @Column(name="custo")
    private Double custo;

    @Column(name="categoria")
    private String categoria;

    private Boolean status = true;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name="data_criação")
    private LocalDateTime dataCriacao;

     @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
       @Column(name="data_atualização")
    private LocalDateTime dataAtualizacao;

    @ManyToMany(mappedBy = "tratamentos")
    private Set<Agenda> agendas;


    public Tratamento() {
    }


    
    public Tratamento(Integer id, String descricao, Double custo, String categoria, Boolean status,
            LocalDateTime dataCriacao, LocalDateTime dataAtualizacao, Set<Agenda> agendas) {
        this.id = id;
        this.descricao = descricao;
        this.custo = custo;
        this.categoria = categoria;
        this.status = true;
        this.dataCriacao = LocalDateTime.now();
        this.dataAtualizacao = LocalDateTime.now();
        this.agendas = agendas;
    }



    // getters/setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Double getCusto() { return custo; }
    public void setCusto(Double custo) { this.custo = custo; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public Boolean getStatus() { return status; }
    public void setStatus(Boolean status) { this.status = status; }

    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }

    public LocalDateTime getDataAtualizacao() { return dataAtualizacao; }
    public void setDataAtualizacao(LocalDateTime dataAtualizacao) { this.dataAtualizacao = dataAtualizacao; }

    public Set<Agenda> getAgendas() { return agendas; }
    public void setAgendas(Set<Agenda> agendas) { this.agendas = agendas; }
}
