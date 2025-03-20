package com.rafael.crud_spring.dto.mapper;

import java.util.Locale.Category;

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

        return new CursoDTO(curso.getId(), curso.getNome(), "Front-end");
    }

    public Curso toEntity(CursoDTO cursoDTO) {

        if (cursoDTO == null) {
            return null;            
        }

        Curso curso = new Curso();
        if (cursoDTO.getId() != null) {
            curso.setId(cursoDTO.getId());            
        }
        curso.setNome(cursoDTO.getNome());
        curso.setCategoria(Categoria.FRONT_END);
        curso.setStatus("Ativo");

        return curso;
    }
}
