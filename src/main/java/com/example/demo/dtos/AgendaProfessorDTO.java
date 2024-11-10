package com.example.demo.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

import com.example.demo.models.Curso;
import com.example.demo.models.Professor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AgendaProfessorDTO {
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private LocalTime horarioInicio;
    private LocalTime horarioFim;
    private String cidade;
    private String estado;
    private String cep;
    private Curso curso;
}
