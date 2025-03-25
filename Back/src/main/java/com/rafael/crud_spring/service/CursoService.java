package com.rafael.crud_spring.service;


import com.rafael.crud_spring.dto.CursoDTO;
import com.rafael.crud_spring.dto.CursoPageDTO;
import com.rafael.crud_spring.dto.mapper.CursoMapper;
import com.rafael.crud_spring.exception.RegistroNaoEncontrado;
import com.rafael.crud_spring.model.Curso;
import com.rafael.crud_spring.repository.CursoRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

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

    public CursoPageDTO list( @PositiveOrZero int page, @Positive @Max(100) int size) {
        Page<Curso> cursosPage = cursoRepository.findAll(PageRequest.of(page, size));    
        List<CursoDTO> cursos = cursosPage.get().map(cursoMapper::toDTO).collect(Collectors.toList());    
        return new CursoPageDTO(cursos, cursosPage.getTotalElements(), cursosPage.getTotalPages());
    }
    
    

    // public List<CursoDTO> list() {
    //     return cursoRepository.findAll()
    //             .stream()
    //             .map(cursoMapper::toDTO)
    //             .collect(Collectors.toList());
    // }

    public CursoDTO findById(@NotNull @Positive Long id) {
        return cursoRepository.findById(id).map(cursoMapper::toDTO)
                .orElseThrow(() -> new RegistroNaoEncontrado(id));
    }

    public CursoDTO create(@Valid @NotNull CursoDTO curso) {
        return cursoMapper.toDTO(cursoRepository.save(cursoMapper.toEntity(curso)));
    }

    public CursoDTO update(@NotNull @Positive Long id, @Valid @NotNull CursoDTO cursoDTO) {
        return cursoRepository.findById(id)
                .map(record -> {
                    Curso cursoEntity = cursoMapper.toEntity(cursoDTO);
                    record.setNome(cursoDTO.nome());
                    record.setcategory(cursoMapper.convertcategoryValue(cursoDTO.category()));
                
                    record.getLessons().clear();
                    cursoEntity.getLessons().forEach(record.getLessons()::add);
                    
                    return cursoMapper.toDTO(cursoRepository.save(record));
                })
                .orElseThrow(() -> new RegistroNaoEncontrado(id));
    }
    
    

    public void delete(@NotNull @Positive Long id) {
        cursoRepository.delete(cursoRepository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontrado(id)));
    }
}
