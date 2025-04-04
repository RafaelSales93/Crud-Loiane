package com.rafael.crud_spring.dto.mapper;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.rafael.crud_spring.dto.CursoDTO;
import com.rafael.crud_spring.dto.LessonDTO;
import com.rafael.crud_spring.enums.Categoria;
import com.rafael.crud_spring.model.Curso;
import com.rafael.crud_spring.model.Lesson;


@Component
public class CursoMapper {

    public CursoDTO toDTO(Curso curso) {
        
        if (curso == null) {
            return null;            
        }

        List<LessonDTO> lessons = curso.getLessons()
        .stream()
        .map(lesson -> new LessonDTO(lesson.getId(), lesson.getNome(),
         lesson.getYoutubeUrl())).toList();
        return new CursoDTO(curso.getId(),curso.getNome(), curso.getCategoria().getValue(), lessons);
        
        
    }

    public Curso toEntity(CursoDTO cursoDTO) {

        if (cursoDTO == null) {
            return null;            
        }

        Curso curso = new Curso();
        if (cursoDTO.id() != null) {
            curso.setId(cursoDTO.id());            
        }
        curso.setNome(cursoDTO.nome());
        curso.setCategoria(convertCategoriaValue(cursoDTO.categoria()));

        List<Lesson> lessons = cursoDTO.lessons().stream().map(lessonDTO -> {
            var lesson = new Lesson();
            lesson.setId(lessonDTO.id());
            lesson.setNome(lessonDTO.nome());
            lesson.setYoutubeUrl(lessonDTO.youtubeUrl());
            lesson.setCurso(curso);
            return lesson;
        }).collect(Collectors.toList());
        curso.setLessons(lessons);
        return curso;
    }

    public Categoria convertCategoriaValue(String value) {
        if (value == null) {
            return null;
        }
        return switch (value) {
            case "Front-End" -> Categoria.FRONT_END;
            case "Back-End" -> Categoria.BACK_END;
            default -> throw new IllegalArgumentException("categoria inválida" + value);
        };
    }
}
