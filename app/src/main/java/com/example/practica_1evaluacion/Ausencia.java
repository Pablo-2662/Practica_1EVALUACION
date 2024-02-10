package com.example.practica_1evaluacion;

import java.io.Serializable;

public class Ausencia implements Serializable {
    private String id;
    private String correoUsuario;
    private String correoJefe;
    private String fecha;
    private String hora;
    private String motivo;

    public Ausencia(String id, String correoUsuario, String correoJefe, String fecha, String hora, String motivo) {
        this.id = id;
        this.correoUsuario = correoUsuario;
        this.correoJefe = correoJefe;
        this.fecha = fecha;
        this.hora = hora;
        this.motivo = motivo;
    }

    public Ausencia(String correoUsuario, String correoJefe, String fecha, String hora, String motivo) {
        this.correoUsuario = correoUsuario;
        this.correoJefe = correoJefe;
        this.fecha = fecha;
        this.hora = hora;
        this.motivo = motivo;
    }

    public Ausencia() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
