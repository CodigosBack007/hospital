package com.senai.backend.Hospital.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table
public class Agenda {
   private Integer id;
   private String tratamento;
   private String consulta;
   private Double custo;
   private String categoria;
   private String descricao;
}
