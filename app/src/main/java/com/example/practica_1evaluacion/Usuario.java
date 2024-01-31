package com.example.practica_1evaluacion;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String email;
    private String nombre;
    private String contrasena;
    private String tlf;
    private String puesto;

    public Usuario(){

    }

    public Usuario(String email, String nombre, String contrasena, String tlf, String puesto) {
        this.email = email;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.tlf = tlf;
        this.puesto = puesto;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }
}
