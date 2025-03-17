package com.rafael.crud_spring.controller;

import java.util.List;

import com.rafael.crud_spring.repository.CursoRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.rafael.crud_spring.CrudSpringApplication;
import com.rafael.crud_spring.model.Curso;

import lombok.AllArgsConstructor;

    @Validated
    @RestController
    @RequestMapping("/api/cursos")
    @AllArgsConstructor
public class CursosController {

    private final CrudSpringApplication crudSpringApplication;
    
    private final CursoRepository cursoRepository;

    @GetMapping
public @ResponseBody List<Curso> list() {
        return cursoRepository.findAll();
    }

    @GetMapping("/{id}")
public ResponseEntity<Curso> findById(@PathVariable @NotNull @Positive Long id) {
        return cursoRepository.findById(id)
            .map(record -> ResponseEntity.ok().body(record))
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
public Curso create(@RequestBody @Valid Curso curso) {

        return cursoRepository.save(curso);
    }

    @PutMapping("/{id}")
public ResponseEntity<Curso> update(@PathVariable @NotNull @Positive Long id,
    @RequestBody @Valid Curso curso) {
        return cursoRepository.findById(id)
            .map(record -> {
                record.setNome(curso.getNome());
                record.setCategoria(curso.getCategoria());
                Curso update = cursoRepository.save(record);
                return ResponseEntity.ok().body(update);
            })
            .orElse(ResponseEntity.notFound().build());
        }

    @DeleteMapping("/{id}")
public ResponseEntity<Void> delete(@PathVariable @NotNull @Positive Long id){
    return cursoRepository.findById(id)
            .map(record -> {
               cursoRepository.deleteById(id);
                return ResponseEntity.noContent().<Void>build();
            })
            .orElse(ResponseEntity.notFound().build());
    }
}
