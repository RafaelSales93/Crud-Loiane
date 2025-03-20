package com.rafael.crud_spring.dto;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CursoDTO(
    @JsonProperty("id") Long id,
    @NotBlank @NotNull @Length(min = 5, max = 100) String nome,
    @NotNull @Length(max = 10) @Pattern(regexp = "Front-End|Back-End") String categoria,
    List<LessonDTO> lessons
) {
}
