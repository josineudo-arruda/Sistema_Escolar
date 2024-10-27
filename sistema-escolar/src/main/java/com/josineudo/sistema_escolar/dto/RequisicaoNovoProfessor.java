package com.josineudo.sistema_escolar.dto;

import com.josineudo.sistema_escolar.models.Professor;
import com.josineudo.sistema_escolar.models.StatusProfessor;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

// DTO Data Transfer Object
// Garante que nao haja inserções malaciosas
public class RequisicaoNovoProfessor {
    @NotBlank
    @NotNull
    private String nome;
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal salario;
    private StatusProfessor statusProfessor;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public StatusProfessor getStatusProfessor() {
        return statusProfessor;
    }

    public void setStatusProfessor(StatusProfessor statusProfessor) {
        this.statusProfessor = statusProfessor;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public Professor toProfessor() {
        Professor professor = new Professor(this.nome,this.salario,this.statusProfessor);
        return professor;
    }
}
