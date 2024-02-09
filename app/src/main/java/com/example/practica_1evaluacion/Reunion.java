package com.example.practica_1evaluacion;
import java.io.Serializable;

public class Reunion implements Serializable {
    private int id;
    private String fecha;
    private String hora;
    private String asunto;
    private boolean asistencia;

    private String correoUsuario;

    public Reunion(String idReunion, String fecha, String hora, String asunto, boolean asistencia, String correoUsuario){
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.asunto = asunto;
        this.asistencia = asistencia;
        this.correoUsuario = correoUsuario;

    }
    public Reunion(){

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public boolean isAsistencia() {
        return asistencia;
    }

    public void setAsistencia(boolean asistencia) {
        this.asistencia = asistencia;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }
}
