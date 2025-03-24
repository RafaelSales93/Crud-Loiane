package com.rafael.crud_spring.controller;



import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rafael.crud_spring.dto.CursoDTO;
import com.rafael.crud_spring.dto.CursoPageDTO;
import com.rafael.crud_spring.service.CursoService;

@Validated
@RestController
@RequestMapping("/api/cursos")
public class CursosController {

    private final CursoService cursoService;

    public CursosController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    public CursoPageDTO list( @RequestParam(defaultValue = "0") @PositiveOrZero int page, @RequestParam(defaultValue = "10") @Positive @Max(100) int pageSize) {
        return cursoService.list( page, pageSize);
    }

    // @GetMapping
    // public List<CursoDTO> list() {
    //     return cursoService.list();
    // }

    @GetMapping("/{id}")
    public CursoDTO findById(@PathVariable @NotNull @Positive Long id) {
        return cursoService.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public CursoDTO create(@RequestBody @Valid CursoDTO curso) {

        return cursoService.create(curso);
    }

    @PutMapping("/{id}")
    public CursoDTO update(@PathVariable @NotNull @Positive Long id,
            @RequestBody @Valid @NotNull CursoDTO curso) {
        return cursoService.update(id, curso);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id) {
        cursoService.delete(id);            
    }

}
