package com.example.demo.services;

import com.example.demo.dtos.AgendaDTO;
import com.example.demo.dtos.ProfessorDTO;

public interface AgendaService {
    void cadastrarAgenda(AgendaDTO agendaDTO);
    AgendaDTO visualizarAgendaDeProfessor(ProfessorDTO professorDTO);
    void adicionarResumo(String resumo);
}
