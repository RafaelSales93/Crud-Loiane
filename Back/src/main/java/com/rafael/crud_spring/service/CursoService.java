package com.rafael.crud_spring.service;


import com.rafael.crud_spring.dto.CursoDTO;
import com.rafael.crud_spring.dto.mapper.CursoMapper;
import com.rafael.crud_spring.enums.Categoria;
import com.rafael.crud_spring.exception.RegistroNaoEncontrado;
import com.rafael.crud_spring.repository.CursoRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@Service
public class CursoService {

    private final CursoRepository cursoRepository;
    private final CursoMapper cursoMapper;

    public CursoService(CursoRepository cursoRepository, CursoMapper cursoMapper) {
        this.cursoRepository = cursoRepository;
        this.cursoMapper = cursoMapper;
    }

    public List<CursoDTO> list() {
        return cursoRepository.findAll()
                .stream()
                .map(cursoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CursoDTO findById(@PathVariable @NotNull @Positive Long id) {
        return cursoRepository.findById(id).map(cursoMapper::toDTO)
                .orElseThrow(() -> new RegistroNaoEncontrado(id));
    }

    public CursoDTO create(@Valid @NotNull CursoDTO curso) {
        return cursoMapper.toDTO(cursoRepository.save(cursoMapper.toEntity(curso)));
    }

    public CursoDTO update(@NotNull @Positive Long id, @Valid @NotNull CursoDTO curso) {
        return cursoRepository.findById(id)
    .map(record -> {
        record.setNome(curso.getNome());
        record.setCategoria(Categoria.FRONT_END);
        return cursoMapper.toDTO(cursoRepository.save(record));
    }).orElseThrow(() -> new RegistroNaoEncontrado(id));

    }

    public void delete(@PathVariable @NotNull @Positive Long id) {
        cursoRepository.delete(cursoRepository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontrado(id)));
    }
}
