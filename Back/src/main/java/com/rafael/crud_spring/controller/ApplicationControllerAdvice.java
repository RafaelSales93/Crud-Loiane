package com.rafael.crud_spring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rafael.crud_spring.exception.RegistroNaoEncontrado;

@RestControllerAdvice
public class ApplicationControllerAdvice {
 
    @ExceptionHandler(RegistroNaoEncontrado.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    
    public String handleNotFoundException(RegistroNaoEncontrado ex) {
        return ex.getMessage();
    }
}
