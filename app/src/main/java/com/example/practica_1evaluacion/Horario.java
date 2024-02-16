package com.example.practica_1evaluacion;

import java.io.Serializable;

public class Horario implements Serializable {
    private String id;
    private String correoUsuario;

    private String cadenaLunes;
    private String cadenaMartes;
    private String cadenaMiercoles;
    private String cadenaJueves;
    private String cadenaViernes;

    public Horario() {
    }

    public Horario(String correoUsuario, String cadenaLunes, String cadenaMartes, String cadenaMiercoles, String cadenaJueves, String cadenaViernes) {
        this.correoUsuario = correoUsuario;
        this.cadenaLunes = cadenaLunes;
        this.cadenaMartes = cadenaMartes;
        this.cadenaMiercoles = cadenaMiercoles;
        this.cadenaJueves = cadenaJueves;
        this.cadenaViernes = cadenaViernes;
    }

    public Horario(String id, String correoUsuario, String cadenaLunes, String cadenaMartes, String cadenaMiercoles, String cadenaJueves, String cadenaViernes) {
        this.id = id;
        this.correoUsuario = correoUsuario;
        this.cadenaLunes = cadenaLunes;
        this.cadenaMartes = cadenaMartes;
        this.cadenaMiercoles = cadenaMiercoles;
        this.cadenaJueves = cadenaJueves;
        this.cadenaViernes = cadenaViernes;
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

    public String getCadenaLunes() {
        return cadenaLunes;
    }

    public void setCadenaLunes(String cadenaLunes) {
        this.cadenaLunes = cadenaLunes;
    }

    public String getCadenaMartes() {
        return cadenaMartes;
    }

    public void setCadenaMartes(String cadenaMartes) {
        this.cadenaMartes = cadenaMartes;
    }

    public String getCadenaMiercoles() {
        return cadenaMiercoles;
    }

    public void setCadenaMiercoles(String cadenaMiercoles) {
        this.cadenaMiercoles = cadenaMiercoles;
    }

    public String getCadenaJueves() {
        return cadenaJueves;
    }

    public void setCadenaJueves(String cadenaJueves) {
        this.cadenaJueves = cadenaJueves;
    }

    public String getCadenaViernes() {
        return cadenaViernes;
    }

    public void setCadenaViernes(String cadenaViernes) {
        this.cadenaViernes = cadenaViernes;
    }
}
