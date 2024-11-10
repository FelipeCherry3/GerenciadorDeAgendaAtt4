package com.example.demo.services;

import java.util.List;

import com.example.demo.dtos.AgendaDTO;
import com.example.demo.dtos.ProfessorDTO;

public interface AgendaService {
    void cadastrarAgenda(AgendaDTO agendaDTO);
    List<AgendaDTO> visualizarAgendaDeProfessor(ProfessorDTO professorDTO);
    void adicionarResumo(AgendaDTO agenda,String resumo);
    AgendaDTO buscarAgendaPorId(Long id);
    String removerAgendaPorId(Long id);
    List<AgendaDTO> buscarTodasAgendas();
}
