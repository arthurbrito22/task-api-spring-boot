package com.taskapi.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@JsonPropertyOrder({ "id", "titulo", "descricao", "completa" })
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String titulo;
    private String descricao;
    private Boolean completa;

    public long getId() { return id; }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getCompleta() {
        return completa;
    }

    public void setCompleta(Boolean completa) {
        this.completa = completa;
    }

    public Task() {}

    public Task(String titulo, String descricao, Boolean completa) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.completa = completa;
    }
}
