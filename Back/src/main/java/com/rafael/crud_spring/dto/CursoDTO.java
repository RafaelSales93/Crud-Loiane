package com.rafael.crud_spring.dto;

public class CursoDTO {

    private Long id;
    private String nome;
    private String categoria;

    public CursoDTO() {
    }

    public CursoDTO(Long id, String nome, String categoria) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "CursoDTO [id=" + id + ", nome=" + nome + ", categoria=" + categoria + "]";
    }
    
}
