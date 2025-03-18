package com.rafael.crud_spring.service;

import com.rafael.crud_spring.model.Curso;
import com.rafael.crud_spring.repository.CursoRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;


@Validated
@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public List<Curso> list() {
        return cursoRepository.findAll();
    }

    public Optional<Curso> findById(@PathVariable @NotNull @Positive Long id) {
        return cursoRepository.findById(id);
    }

    public Curso create(@Valid Curso curso) {

        return cursoRepository.save(curso);
    }

    public Optional<Curso> update(@NotNull @Positive Long id,
             @Valid Curso curso) {
        return cursoRepository.findById(id)
                .map(record -> {
                    record.setNome(curso.getNome());
                    record.setCategoria(curso.getCategoria());
                   return cursoRepository.save(record);
                   
                });
    }

    public boolean delete(@PathVariable @NotNull @Positive Long id) {
        return cursoRepository.findById(id)
                .map(record -> {
                    cursoRepository.deleteById(id);
                    return true;
                })
                .orElse(false);
    }
}
