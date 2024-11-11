package com.example.demo.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.CursoDTO;
import com.example.demo.dtos.ProfessorDTO;
import com.example.demo.models.Curso;
import com.example.demo.models.Professor;
import com.example.demo.repository.CursoRepository;
import com.example.demo.repository.ProfessorRepository;

@Service
public class ProfessorServiceImp implements ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Override
    public void adicionar(ProfessorDTO professorDTO) {
        Professor professor = Professor.builder()
            .nome(professorDTO.getNome())
            .cpf(professorDTO.getCpf())
            .rg(professorDTO.getRg())
            .endereco(professorDTO.getEndereco())
            .celular(professorDTO.getCelular())
            .especializacoes(new ArrayList<>())
            .build();
        professorRepository.save(professor);
    }

    @Override
    public List<ProfessorDTO> professoresPorEspecialidade(Long idCurso) {
        Curso curso2 = cursoRepository.findById(idCurso) 
            .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));
        List<Professor> professores = professorRepository.findByEspecializacoes(curso2);
        return professores.stream()
            .map(professor -> new ProfessorDTO(
                professor.getId(),
                professor.getNome(),
                professor.getCpf(),
                professor.getRg(),
                professor.getEndereco(),
                professor.getCelular(),
                professor.getEspecializacoes().stream()
                    .map(cursoDTO -> new CursoDTO(
                        curso2.getId(),
                        curso2.getDescricao(),
                        curso2.getCargaHoraria(),
                        curso2.getObjetivos(),
                        curso2.getEmenta()))
                    .collect(Collectors.toList())))
            .collect(Collectors.toList());
    }

    @Override
    public void adicionarEspecializaao(Long idprofessor, Long idCurso) {
        Curso curso = cursoRepository.findById(idCurso)
            .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));
        Professor professor = professorRepository.findById(idprofessor)
            .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));
        professor.getEspecializacoes().add(curso);
        professorRepository.save(professor);
    }

    @Override
    public List<ProfessorDTO> buscarTodosProfessores() {
        List<Professor> professores = professorRepository.findAll();
        return professores.stream()
            .map(professor -> new ProfessorDTO(
                professor.getId(),
                professor.getNome(),
                professor.getCpf(),
                professor.getRg(),
                professor.getEndereco(),
                professor.getCelular(),
                professor.getEspecializacoes().stream()
                    .map(curso -> new CursoDTO(
                        curso.getId(),
                        curso.getDescricao(),
                        curso.getCargaHoraria(),
                        curso.getObjetivos(),
                        curso.getEmenta()))
                    .collect(Collectors.toList()))
            ).collect(Collectors.toList());
    }
    
}
