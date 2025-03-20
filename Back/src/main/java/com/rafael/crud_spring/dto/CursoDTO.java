package com.rafael.crud_spring.dto;

public class CursoDTO {

    private Long id;
    private String nome;
    private String categoria;
    private String status;

    public CursoDTO() {
    }

    public CursoDTO(Long id, String nome, String categoria, String status) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.status = status;
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

    public String getStatus() {
        return status;
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

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CursoDTO [id=" + id + ", nome=" + nome + ", categoria=" + categoria + ", status=" + status + "]";
    }
    
}
