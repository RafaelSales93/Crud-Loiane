package com.rafael.crud_spring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.rafael.crud_spring.exception.RegistroNaoEncontrado;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ApplicationControllerAdvice {
 
    @ExceptionHandler(RegistroNaoEncontrado.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    
    public String handleNotFoundException(RegistroNaoEncontrado ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
            .map(error -> error.getField() + " " + error.getDefaultMessage())
            .reduce("", (acc, error) -> acc + error + "\n");
    }
    
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleConstraintViolationException(ConstraintViolationException ex) {
        return ex.getConstraintViolations().stream()
            .map(violation -> violation.getPropertyPath() + " " + violation.getMessage())
            .reduce("", (acc, violation) -> acc + violation + "\n");
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        if (ex != null && ex.getRequiredType() != null) {
            String type = ex.getRequiredType().getTypeName();
            String[] typeParts = type.split("\\.");
            String typeName = typeParts[typeParts.length - 1];
            return ex.getName() + " deve ser do tipo " + typeName;
        }
        return "Argumento inválido";
    }
    

}
