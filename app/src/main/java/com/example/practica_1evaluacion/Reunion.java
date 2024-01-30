package com.example.practica_1evaluacion;
import java.io.Serializable;

public class Reunion implements Serializable {
    private int id;
    private String fecha;
    private String hora;
    private String asunto;
    private boolean asistencia;

    public Reunion(){

    }

    public Reunion(int id, String fecha, String hora, String asunto, boolean asistencia) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.asunto = asunto;
        this.asistencia = asistencia;
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
}
