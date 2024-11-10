package com.example.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.AgendaDTO;
import com.example.demo.dtos.ProfessorDTO;
import com.example.demo.models.Agenda;
import com.example.demo.models.Professor;
import com.example.demo.repository.AgendaRepository;
import com.example.demo.repository.ProfessorRepository;

@Service
public class AgendaServiceImp implements AgendaService{

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private AgendaRepository agendaRepository;
    @Override
    public void cadastrarAgenda(AgendaDTO agendaDTO) {
        Agenda agenda = Agenda.builder()
            .dataInicio(agendaDTO.getDataInicio())
            .dataFim(agendaDTO.getDataFim())
            .horarioInicio(agendaDTO.getHorarioInicio())
            .horarioFim(agendaDTO.getHorarioFim())
            .cidade(agendaDTO.getCidade())
            .estado(agendaDTO.getEstado())
            .cep(agendaDTO.getCep())
            .professor(Professor.builder().id(agendaDTO.getProfessor().getId()).build())
            .curso(agendaDTO.getCurso())
            .build();
        agendaRepository.save(agenda);
    }

    @Override
    public List<AgendaDTO> visualizarAgendaDeProfessor(ProfessorDTO professorDTO) {
        Professor professor = professorRepository.findById(professorDTO.getId())
            .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));

        List<Agenda> agendas = agendaRepository.findByProfessorId(professor.getId());
        return agendas.stream()
                .map(agenda -> new AgendaDTO(
                        agenda.getId(),
                        agenda.getDataInicio(),
                        agenda.getDataFim(),
                        agenda.getHorarioInicio(),
                        agenda.getHorarioFim(),
                        agenda.getCidade(),
                        agenda.getEstado(),
                        agenda.getCep(),
                        agenda.getProfessor(),
                        agenda.getCurso()))
                .collect(Collectors.toList());
    }

    @Override
    public void adicionarResumo(AgendaDTO agenda, String resumo) {
        Agenda agendaModel = agendaRepository.findById(agenda.getId())
            .orElseThrow(() -> new IllegalArgumentException("Agenda não encontrada"));
        agendaModel.setResumo(resumo);
        agendaRepository.save(agendaModel);
    }

    @Override
    public AgendaDTO buscarAgendaPorId(Long id) {
        Agenda agenda = agendaRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Agenda não encontrada"));
        return new AgendaDTO(
            agenda.getId(),
            agenda.getDataInicio(),
            agenda.getDataFim(),
            agenda.getHorarioInicio(),
            agenda.getHorarioFim(),
            agenda.getCidade(),
            agenda.getEstado(),
            agenda.getCep(),
            agenda.getProfessor(),
            agenda.getCurso());
    }

    @Override
    public String removerAgendaPorId(Long id) {
        agendaRepository.deleteById(id);
        return "Agenda removida com sucesso";  
    }

    @Override
    public List<AgendaDTO> buscarTodasAgendas() {
        List<Agenda> agendas = agendaRepository.findAll();

        return agendas.stream()
        .map(agenda -> new AgendaDTO(
            agenda.getId(),
            agenda.getDataInicio(),
            agenda.getDataFim(),
            agenda.getHorarioInicio(),
            agenda.getHorarioFim(),
            agenda.getCidade(),
            agenda.getEstado(),
            agenda.getCep(),
            agenda.getProfessor(),
            agenda.getCurso())).collect(Collectors.toList());
    }
    
}
