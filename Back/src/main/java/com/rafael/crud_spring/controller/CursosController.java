package com.rafael.crud_spring.controller;

import java.util.List;

import com.rafael.crud_spring.repository.CursoRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rafael.crud_spring.model.Curso;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/cursos")
@AllArgsConstructor
public class CursosController {

    private final CursoRepository cursoRepository;


    @GetMapping
    public List<Curso> list(){
        return cursoRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Curso>findById(@PathVariable Long id){
        return cursoRepository.findById(id)
        .map(record -> ResponseEntity.ok().body(record))
        .orElse(ResponseEntity.notFound().build());
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
public Curso create(@RequestBody Curso curso){

   return cursoRepository.save(curso);
//    return ResponseEntity.status(HttpStatus.CREATED)
//    .body(cursoRepository.save(curso));

}
}
