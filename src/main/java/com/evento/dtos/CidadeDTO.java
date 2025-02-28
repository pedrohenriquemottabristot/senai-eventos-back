package com.evento.dtos;

public class CidadeDTO {
    private Long id;
    private String nome;
    private String estado;

    public CidadeDTO(){}

    public CidadeDTO(Long id, String estado, String nome) {
        this.id = id;
        this.estado = estado;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
