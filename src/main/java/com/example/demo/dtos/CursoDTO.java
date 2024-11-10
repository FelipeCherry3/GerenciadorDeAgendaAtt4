package com.example.demo.dtos;

import java.util.List;

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
@ToString
@Getter
@Setter
public class CursoDTO {
    private Long id;
    private String descricao;
    private int cargaHoraria;
    private String objetivos;
    private String ementa;
    private List<ProfessorDTO> professores;
    public CursoDTO(Long id, String descricao, int cargaHoraria, String objetivos, String ementa) {
        this.id = id;
        this.descricao = descricao;
        this.cargaHoraria = cargaHoraria;
        this.objetivos = objetivos;
        this.ementa = ementa;
    }
}
