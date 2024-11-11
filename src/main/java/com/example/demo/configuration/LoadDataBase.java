package com.example.demo.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.models.Agenda;
import com.example.demo.models.Curso;
import com.example.demo.models.Professor;
import com.example.demo.repository.AgendaRepository;
import com.example.demo.repository.CursoRepository;
import com.example.demo.repository.ProfessorRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class LoadDataBase {

    @Bean
    CommandLineRunner initDatabase(CursoRepository cursoRepository, 
                                   ProfessorRepository professorRepository, 
                                   AgendaRepository agendaRepository) {
        return args -> {
            // Criando Cursos
            Curso curso1 = new Curso(1L, "Curso de Java", 40, "Programação em Java", "Ementa do curso 1");
            Curso curso2 = new Curso(2L, "Curso de Python", 35, "Programação em Python", "Ementa do curso 2");
            Curso curso3 = new Curso(3L, "Fisioterapia Desportiva", 30, "Técnicas para atletas", "Ementa do curso 3");

            cursoRepository.save(curso1);
            cursoRepository.save(curso2);
            cursoRepository.save(curso3);

            // Criando Professores
            Professor professor1 = new Professor("Arnaldo César", "12345678900", "MG1234567", "Rua A, 123", "31987654321");
            Professor professor2 = new Professor("Maria Cavalcante", "09876543211", "SP7654321", "Rua B, 456", "31987654322");

            // Associando cursos aos professores
            professor1.getEspecializacoes().add(curso1);
            professor1.getEspecializacoes().add(curso3);
            professor2.getEspecializacoes().add(curso2);

            professorRepository.save(professor1);
            professorRepository.save(professor2);

            // Criando Agendas
            Agenda agenda1 = new Agenda(1L, curso1, professor1, LocalDate.of(2024, 3, 17), LocalDate.of(2024, 3, 20), LocalTime.of(8, 0), LocalTime.of(17, 0), "Resumo 1", "Marília", "SP", "123321");
            Agenda agenda2 = new Agenda(2L, curso2, professor2, LocalDate.of(2024, 3, 19), LocalDate.of(2024, 3, 21), LocalTime.of(8, 0), LocalTime.of(12, 0), "Resumo 2", "Jundiaí", "SP", "1700000");

            agendaRepository.save(agenda1);
            agendaRepository.save(agenda2);
        };
    }
}