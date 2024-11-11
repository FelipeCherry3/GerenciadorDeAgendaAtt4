package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Agenda;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {
    List<Agenda> findByProfessorId(Long professorId);
    List<Agenda> findByProfessorIdAndDataInicioLessThanEqualAndDataFimGreaterThanEqual(Long professorId, LocalDate dataInicio, LocalDate dataFim);
}
