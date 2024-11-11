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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class CadastrarAgendaDTO {
    private Long id;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private LocalTime horarioInicio;
    private LocalTime horarioFim;
    private String cidade;
    private String estado;
    private String cep;
    private Long idProfessor;
    private Long idCurso;
}
