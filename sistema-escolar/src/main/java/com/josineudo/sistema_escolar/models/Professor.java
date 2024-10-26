package com.josineudo.sistema_escolar.models;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;

@Entity
public class Professor {
    @Id // Marca como uma chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // O valor gerado como id vai um valor incremental e único
    private Long id;

    @Column(nullable = false) // Obriga a presença do nome
    private String nome;

    private BigDecimal salario;

    @Enumerated(EnumType.STRING) // Fala que o tipo do Enum vai ser salvo no BDD como string
    private StatusProfessor statusProfessor;

    public Professor() {}

    public Professor(String nome, BigDecimal salario, StatusProfessor statusProfessor) {
        this.nome = nome;
        this.salario = salario;
        this.statusProfessor = statusProfessor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public StatusProfessor getStatusProfessor() {
        return statusProfessor;
    }

    public void setStatusProfessor(StatusProfessor statusProfessor) {
        this.statusProfessor = statusProfessor;
    }
}
