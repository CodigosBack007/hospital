package com.senai.backend.Hospital.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "tratamentos")
public class Tratamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // código único

    private String descricao;
    private Double custo;
    private String categoria;

    private Boolean status = true;

    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

    @ManyToMany(mappedBy = "tratamentos")
    private Set<Agenda> agendas;

    // getters e setters

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
