package com.rafael.crud_spring.model;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rafael.crud_spring.enums.Categoria;
import com.rafael.crud_spring.enums.Status;
import com.rafael.crud_spring.enums.converters.CategoriaConverter;
import com.rafael.crud_spring.enums.converters.StatusConverter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
//@Table(name = "Cursos")
@SQLDelete(sql = "UPDATE Curso SET status = 'Inativo' WHERE id = ?")
@Where(clause = "status = 'Ativo'")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;

    @NotBlank
    @NotNull
    @Length(min = 5, max = 100)
    @Column(length = 100, nullable = false)
    private String nome;

    @NotNull
    @Column(nullable = false)
    @Convert(converter = CategoriaConverter.class)
    private Categoria categoria;

    @NotNull
    @Column(length = 10, nullable = false)
    @Convert(converter = StatusConverter.class)
    private Status status = Status.ATIVO;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "curso")
    // @JoinColumn(name = "curso_id")
    private List<Lesson> lessons = new ArrayList<>();

}
