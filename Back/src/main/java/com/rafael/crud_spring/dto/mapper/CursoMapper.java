package com.rafael.crud_spring.dto.mapper;


import org.springframework.stereotype.Component;

import com.rafael.crud_spring.dto.CursoDTO;
import com.rafael.crud_spring.enums.Categoria;
import com.rafael.crud_spring.model.Curso;

@Component
public class CursoMapper {

    public CursoDTO toDTO(Curso curso) {
        
        if (curso == null) {
            return null;            
        }

        return new CursoDTO(curso.getId(), curso.getNome(), curso.getCategoria().getValue(), curso.getLessons());
        
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

        return curso;
    }

    public Categoria convertCategoriaValue(String value) {
        if (value == null) {
            return null;
        }
        return switch (value) {
            case "Front-End" -> Categoria.FRONT_END;
            case "Back-End" -> Categoria.BACK_END;
            default -> throw new IllegalArgumentException("Categoria inválida" + value);
        };
    }
}
