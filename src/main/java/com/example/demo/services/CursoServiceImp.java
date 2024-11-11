package com.example.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.CursoDTO;
import com.example.demo.models.Curso;
import com.example.demo.models.Professor;
import com.example.demo.repository.CursoRepository;
import com.example.demo.repository.ProfessorRepository;

@Service
public class CursoServiceImp  implements CursoService{

    @Autowired
    private CursoRepository cursoRepository;
    
    @Autowired
    private ProfessorRepository professorRepository;

    @Override
    public void adicionar(CursoDTO curso) {
        Curso cursoEntity = new Curso().builder()
            .id(curso.getId())
            .descricao(curso.getDescricao())
            .cargaHoraria(curso.getCargaHoraria())
            .objetivos(curso.getObjetivos())
            .ementa(curso.getEmenta())
            .build();
        cursoRepository.save(cursoEntity);
    }

    @Override
    public void adicionarProfessor(Long idCurso, Long idProfessor) {
        Curso curso = cursoRepository.findById(idCurso)
            .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));
        Professor professor = professorRepository.findById(idProfessor)
        .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));

        curso.getProfessores().add(professor);
        professor.getEspecializacoes().add(curso);
        cursoRepository.save(curso);
        professorRepository.save(professor);
    }

    @Override
    public List<CursoDTO> buscarCursos() {
        List<Curso> cursos = cursoRepository.findAll();
        return cursos.stream()
            .map(curso -> new CursoDTO(
                curso.getId(),
                curso.getDescricao(),
                curso.getCargaHoraria(),
                curso.getObjetivos(),
                curso.getEmenta()))
            .collect(Collectors.toList());
    }
    
}
