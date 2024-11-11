package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.ProfessorDTO;
import com.example.demo.services.ProfessorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/professor")
public class ProfessorController {
    
    @Autowired
    private ProfessorService professorService;

    @GetMapping("/all")
    public List<ProfessorDTO> getProfessores() {
        return professorService.buscarTodosProfessores();
    }
    
    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public String cadastrarProfessor(@RequestBody ProfessorDTO professorDTO) {
        professorService.adicionar(professorDTO);
        
        return "Professor Cadastrado";
    }
    
    @GetMapping("/especialidade")
    public List<ProfessorDTO> getProfessoresPorEspecialidade(@RequestParam Long idCurso) {
        return professorService.professoresPorEspecialidade(idCurso);
    }

    @PostMapping("/adicionarEspecializacao")
    @ResponseStatus(HttpStatus.CREATED)
    public String adicionarEspecializacao(@RequestParam Long idProfessor, @RequestParam Long idCurso) {
        professorService.adicionarEspecializaao(idProfessor, idCurso);
        
        return "Especialização Adicionada";
    }
}
