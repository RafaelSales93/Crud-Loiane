package com.rafael.crud_spring.dto;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rafael.crud_spring.enums.category;
import com.rafael.crud_spring.enums.validation.ValueOfEnum;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CursoDTO(
    @JsonProperty("_id") Long id,
    @NotBlank @NotNull @Length(min = 5, max = 100) String nome,
    @NotNull @Length(max = 10) @ValueOfEnum(enumClass = category.class ) String category,
    @NotNull @NotEmpty @Valid List<LessonDTO> lessons ) {
}
