package com.example.practica_1evaluacion;

import java.io.Serializable;

public class Mensaje implements Serializable {

    private String id;
    private String correoOrigen;
    private String correoDestino;
    private String fecha;
    private String hora;
    private String mensaje;

    public Mensaje(String id, String correoOrigen, String correoDestino, String fecha, String hora, String mensaje) {
        this.id = id;
        this.correoOrigen = correoOrigen;
        this.correoDestino = correoDestino;
        this.fecha = fecha;
        this.hora = hora;
        this.mensaje = mensaje;
    }

    public Mensaje() {
    }

    public Mensaje(String correoOrigen, String correoDestino, String fecha, String hora, String mensaje) {
        this.correoOrigen = correoOrigen;
        this.correoDestino = correoDestino;
        this.fecha = fecha;
        this.hora = hora;
        this.mensaje = mensaje;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCorreoOrigen() {
        return correoOrigen;
    }

    public void setCorreoOrigen(String correoOrigen) {
        this.correoOrigen = correoOrigen;
    }

    public String getCorreoDestino() {
        return correoDestino;
    }

    public void setCorreoDestino(String correoDestino) {
        this.correoDestino = correoDestino;
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

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
