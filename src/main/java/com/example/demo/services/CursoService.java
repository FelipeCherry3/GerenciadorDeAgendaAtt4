package com.example.demo.services;

import java.util.List;

import com.example.demo.dtos.CursoDTO;

public interface CursoService {
    void adicionar(CursoDTO curso);
    void adicionarProfessor(Long idCurso, Long idProfessor);
    List<CursoDTO> buscarCursos();
}
