package com.example.practica_1evaluacion;

import java.io.Serializable;

public class Guardia implements Serializable {

    private String idGuardia;
    private String correoGuardia;
    private String fechaGuardia;
    private String horaGuardia;
    private String motivoGuardia;

    public Guardia() {
    }

    public Guardia(String idGuardia, String correoGuardia, String fechaGuardia, String horaGuardia, String motivoGuardia) {
        this.idGuardia = idGuardia;
        this.correoGuardia = correoGuardia;
        this.fechaGuardia = fechaGuardia;
        this.horaGuardia = horaGuardia;
        this.motivoGuardia = motivoGuardia;
    }

    public Guardia(String correoGuardia, String fechaGuardia, String horaGuardia, String motivoGuardia) {
        this.correoGuardia = correoGuardia;
        this.fechaGuardia = fechaGuardia;
        this.horaGuardia = horaGuardia;
        this.motivoGuardia = motivoGuardia;
    }

    public String getIdGuardia() {
        return idGuardia;
    }

    public void setIdGuardia(String idGuardia) {
        this.idGuardia = idGuardia;
    }

    public String getCorreoGuardia() {
        return correoGuardia;
    }

    public void setCorreoGuardia(String correoGuardia) {
        this.correoGuardia = correoGuardia;
    }

    public String getFechaGuardia() {
        return fechaGuardia;
    }

    public void setFechaGuardia(String fechaGuardia) {
        this.fechaGuardia = fechaGuardia;
    }

    public String getHoraGuardia() {
        return horaGuardia;
    }

    public void setHoraGuardia(String horaGuardia) {
        this.horaGuardia = horaGuardia;
    }

    public String getMotivoGuardia() {
        return motivoGuardia;
    }

    public void setMotivoGuardia(String motivoGuardia) {
        this.motivoGuardia = motivoGuardia;
    }
}
