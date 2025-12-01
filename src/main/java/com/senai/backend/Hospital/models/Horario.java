package com.senai.backend.Hospital.models;

import java.time.LocalTime;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table
public class Horario {
    private Integer id;
    private LocalTime horaInicial;
    private LocalTime horaFinal;
    private Date dia;
}
