package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.CursoDTO;
import com.example.demo.services.CursoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping("/all")
    public List<CursoDTO> getCursos() {
        return cursoService.buscarCursos();
    }

    @PostMapping("/cadastrar")
    public String cadastrarCurso(@RequestBody CursoDTO cursoDTO) {
        cursoService.adicionar(cursoDTO);
        return "Curso Cadastrado";
    }
    
    @PostMapping("/adicionarProfessor")
    public String adicionarProfessor(@RequestParam Long idCurso, @RequestParam Long idProfessor) {
        cursoService.adicionarProfessor(idCurso, idProfessor);
        
        return "Professor adicionado a curso";
    }
    
}
