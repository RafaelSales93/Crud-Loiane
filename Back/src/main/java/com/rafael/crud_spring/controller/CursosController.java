package com.rafael.crud_spring.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rafael.crud_spring.model.Curso;
import com.rafael.crud_spring.repository.CusroRepository;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/cursos")
@AllArgsConstructor
public class CursosController {

    private final CusroRepository cusroRepository;


    @GetMapping
    public @ResponseBody List<Curso> list(){
        return cusroRepository.findAll();
    }



}
