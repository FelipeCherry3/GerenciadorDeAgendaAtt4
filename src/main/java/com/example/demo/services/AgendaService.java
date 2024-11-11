package com.example.demo.services;

import java.util.List;

import com.example.demo.dtos.AgendaDTO;
import com.example.demo.dtos.CadastrarAgendaDTO;
import com.example.demo.dtos.ProfessorDTO;

public interface AgendaService {
    void cadastrarAgenda(CadastrarAgendaDTO agendaDTO);
    List<AgendaDTO> visualizarAgendaDeProfessor(Long id);
    void adicionarResumo(Long idAgenda,String resumo);
    AgendaDTO buscarAgendaPorId(Long id);
    String removerAgendaPorId(Long id);
    List<AgendaDTO> buscarTodasAgendas();
    boolean professorTemAgenda(AgendaDTO agendaDTO);
}
