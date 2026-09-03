package com.taskapi.entity;

public class Task {

    private long id;
    private String titulo;
    private String descricao;
    private boolean completa = false;

    public long getId() {
        return id;
    }

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

    public boolean isCompleta() {
        return completa;
    }

    public void setCompleta(boolean completa) {
        this.completa = completa;
    }

    public Task() {

    }

    public Task(long id, String titulo, String descricao, boolean completa) {

        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.completa = completa;
    }
}
