package com.example.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.AgendaDTO;
import com.example.demo.dtos.CadastrarAgendaDTO;
import com.example.demo.dtos.ProfessorDTO;
import com.example.demo.models.Agenda;
import com.example.demo.models.Curso;
import com.example.demo.models.Professor;
import com.example.demo.repository.AgendaRepository;
import com.example.demo.repository.CursoRepository;
import com.example.demo.repository.ProfessorRepository;

@Service
public class AgendaServiceImp implements AgendaService{

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public void cadastrarAgenda(CadastrarAgendaDTO agendaDTO) {
        Professor professor = professorRepository.findById(agendaDTO.getIdProfessor())
            .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));

        Curso curso = cursoRepository.findById(agendaDTO.getIdCurso())
            .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));
        Agenda agenda = Agenda.builder()
            .dataInicio(agendaDTO.getDataInicio())
            .dataFim(agendaDTO.getDataFim())
            .horarioInicio(agendaDTO.getHorarioInicio())
            .horarioFim(agendaDTO.getHorarioFim())
            .cidade(agendaDTO.getCidade())
            .estado(agendaDTO.getEstado())
            .cep(agendaDTO.getCep())
            .professor(professor)
            .curso(curso)
            .build();
        if(!professorTemAgenda(new AgendaDTO(agenda.getId(), agenda.getDataInicio(), agenda.getDataFim(), agenda.getHorarioInicio(), agenda.getHorarioFim(), agenda.getCidade(), agenda.getEstado(), agenda.getCep(), agenda.getProfessor(), agenda.getCurso(), agenda.getResumo()))){
            throw new IllegalArgumentException("Professor já possui agenda nesse período");
        }
        agendaRepository.save(agenda);
    }

    @Override
    public List<AgendaDTO> visualizarAgendaDeProfessor(Long idProf) {
        Professor professor = professorRepository.findById(idProf)
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
                        agenda.getCurso(),
                        agenda.getResumo()))
                .collect(Collectors.toList());
    }

    @Override
    public void adicionarResumo(Long idAgenda, String resumo) {
        Agenda agendaModel = agendaRepository.findById(idAgenda)
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
            agenda.getCurso(),
            agenda.getResumo());
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
            agenda.getCurso(),
            agenda.getResumo())).collect(Collectors.toList());
    }

    @Override
    public boolean professorTemAgenda(AgendaDTO agendaDTO) {

        List<Agenda> agendas = agendaRepository.findByProfessorIdAndDataInicioLessThanEqualAndDataFimGreaterThanEqual(agendaDTO.getProfessor().getId(), agendaDTO.getDataInicio(),agendaDTO.getDataFim());
        return agendas.isEmpty();
    }
    
}
