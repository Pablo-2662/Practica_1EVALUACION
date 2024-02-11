package com.example.practica_1evaluacion;

import java.io.Serializable;

public class Tarea implements Serializable {
    private String id;
    private String descripcion;
    private String tipo;
    private String categoria;
    private String correoEmisor;
    private String correoReceptor;
    private String fechaLimite;

    public Tarea(String id, String descripcion, String tipo, String categoria, String correoEmisor, String correoReceptor, String fechaLimite) {
        this.id = id;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.categoria = categoria;
        this.correoEmisor = correoEmisor;
        this.correoReceptor = correoReceptor;
        this.fechaLimite = fechaLimite;
    }

    public Tarea() {
    }

    public Tarea(String descripcion, String tipo, String categoria, String correoEmisor, String correoReceptor, String fechaLimite) {
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.categoria = categoria;
        this.correoEmisor = correoEmisor;
        this.correoReceptor = correoReceptor;
        this.fechaLimite = fechaLimite;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCorreoEmisor() {
        return correoEmisor;
    }

    public void setCorreoEmisor(String correoEmisor) {
        this.correoEmisor = correoEmisor;
    }

    public String getCorreoReceptor() {
        return correoReceptor;
    }

    public void setCorreoReceptor(String correoReceptor) {
        this.correoReceptor = correoReceptor;
    }

    public String getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(String fechaLimite) {
        this.fechaLimite = fechaLimite;
    }
}
