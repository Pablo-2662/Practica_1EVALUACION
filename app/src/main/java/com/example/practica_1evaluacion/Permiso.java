package com.example.practica_1evaluacion;

import java.io.Serializable;

public class Permiso implements Serializable {

    private String idPermiso;
    private String correoUsuario;
    private String correoJefe;
    private String tipo;
    private String fechaMin;
    private String fechaMax;
    private boolean aceptado;

    public Permiso() {
    }

    public Permiso(String correoUsuario, String correoJefe, String tipo, String fechaMin, String fechaMax, boolean aceptado) {
        this.correoUsuario = correoUsuario;
        this.correoJefe = correoJefe;
        this.tipo = tipo;
        this.fechaMin = fechaMin;
        this.fechaMax = fechaMax;
        this.aceptado = aceptado;
    }

    public Permiso(String idPermiso, String correoUsuario, String correoJefe, String tipo, String fechaMin, String fechaMax, boolean aceptado) {
        this.idPermiso = idPermiso;
        this.correoUsuario = correoUsuario;
        this.correoJefe = correoJefe;
        this.tipo = tipo;
        this.fechaMin = fechaMin;
        this.fechaMax = fechaMax;
        this.aceptado = aceptado;
    }

    public String getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(String idPermiso) {
        this.idPermiso = idPermiso;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public String getCorreoJefe() {
        return correoJefe;
    }

    public void setCorreoJefe(String correoJefe) {
        this.correoJefe = correoJefe;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFechaMin() {
        return fechaMin;
    }

    public void setFechaMin(String fechaMin) {
        this.fechaMin = fechaMin;
    }

    public String getFechaMax() {
        return fechaMax;
    }

    public void setFechaMax(String fechaMax) {
        this.fechaMax = fechaMax;
    }

    public boolean getAceptado() {
        return aceptado;
    }

    public void setAceptado(boolean aceptado) {
        this.aceptado = aceptado;
    }
}
