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
public class ProfessorDTO {
    private Long id;
    private String nome;
    private String cpf;
    private String rg;
    private String endereco;
    private String celular;
    private List<CursoDTO> especializacoes;
}
