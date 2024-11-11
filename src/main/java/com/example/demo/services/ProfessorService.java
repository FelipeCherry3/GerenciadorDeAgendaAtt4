package com.example.demo.services;

import java.util.List;

import com.example.demo.dtos.CursoDTO;
import com.example.demo.dtos.ProfessorDTO;

public interface ProfessorService {
    void adicionar(ProfessorDTO professorDTO);
    List<ProfessorDTO> professoresPorEspecialidade(Long idCurso);
    void adicionarEspecializaao(Long idProfessor, Long idCurso);
    List<ProfessorDTO> buscarTodosProfessores();
}
