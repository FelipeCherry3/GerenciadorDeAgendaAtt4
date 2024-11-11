package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.AgendaDTO;
import com.example.demo.dtos.CadastrarAgendaDTO;
import com.example.demo.services.AgendaService;

@RestController
@RequestMapping("/agenda")
public class AgendaController {

    @Autowired
    private AgendaService agendaService;

    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public String cadastrarAgenda(@RequestBody CadastrarAgendaDTO agendaDTO) {
        agendaService.cadastrarAgenda(agendaDTO);
        return "Agenda Cadastrada";
    }
    
    @GetMapping("visualizarProfessor")
    public List<AgendaDTO> visualizarAgendaDeProfessor(@RequestParam Long idProf) {
        return agendaService.visualizarAgendaDeProfessor(idProf);
    }

    @PostMapping("/adicionarResumo")
    public String adicionarResumo(@RequestParam Long idAgenda, @RequestParam String resumo) {
        agendaService.adicionarResumo(idAgenda, resumo);
        return "Resumo Adicionado";
    }

    @GetMapping("/visualizar")
    public List<AgendaDTO> visualizarAgenda() {
        return agendaService.buscarTodasAgendas();
    }
}
