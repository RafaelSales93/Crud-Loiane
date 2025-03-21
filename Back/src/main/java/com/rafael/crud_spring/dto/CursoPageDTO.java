package com.rafael.crud_spring.dto;

import java.util.List;

public class CursoPageDTO {
    private List<CursoDTO> cursos;
    private long totalElements;
    private int totalPages;

    public CursoPageDTO(List<CursoDTO> cursos, long totalElements, int totalPages) {
        this.cursos = cursos;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }

    // Getters e Setters
    public List<CursoDTO> getCursos() {
        return cursos;
    }

    public void setCursos(List<CursoDTO> cursos) {
        this.cursos = cursos;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
