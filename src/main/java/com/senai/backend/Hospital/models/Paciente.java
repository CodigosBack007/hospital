package com.senai.backend.Hospital.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pacientes")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String endereco;
    private String contato;
    private String descricao;

    // ativo/inativo (regra de negócio usa isso)
    private Boolean status = true;

    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

    // getters e setters
    public Paciente() {
    }
    

    public Paciente(Integer id, String nome, String endereco, String contato, String descricao, Boolean status,
            LocalDateTime dataCriacao, LocalDateTime dataAtualizacao) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.contato = contato;
        this.descricao = descricao;
        this.status = status;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
    }


    public Integer getId() { return id; }
    
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getContato() { return contato; }
    public void setContato(String contato) { this.contato = contato; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Boolean getStatus() { return status; }
    public void setStatus(Boolean status) { this.status = status; }

    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }

    public LocalDateTime getDataAtualizacao() { return dataAtualizacao; }
    public void setDataAtualizacao(LocalDateTime dataAtualizacao) { this.dataAtualizacao = dataAtualizacao; }
}
