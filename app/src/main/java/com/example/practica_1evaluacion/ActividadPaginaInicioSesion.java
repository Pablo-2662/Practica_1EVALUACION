package com.example.practica_1evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class ActividadPaginaInicioSesion extends AppCompatActivity {

    private String opcionSeleccionada="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_pagina_inicio_sesion);


        Button botonVolverInicioSesion = (Button) findViewById(R.id.botonVolverPantallaInicio);
        Button iniciarSesion = (Button) findViewById(R.id.botonCompletarInicio);
        EditText textoMail = (EditText) findViewById(R.id.textoCorreoInicioSesion);
        EditText textoPasswd = (EditText) findViewById(R.id.textoContrasenyaInicioSesion);
        TextView textoOculto = (TextView) findViewById(R.id.textoErrorInicio);
        RadioGroup opcionesPuestoInicio = findViewById(R.id.SelectorPuestoTrabajoInicio);
        RadioButton opcionInicioDocente = findViewById(R.id.opcionDocenteInicio);
        RadioButton opcionInicioCoordinador = findViewById(R.id.opcionCoordinadorInicio);
        RadioButton opcionInicioJefeEstudios = findViewById(R.id.opcionJefeEstudiosInicio);


        //Boton que me devuelve a la pagina principal de la aplicacion
        botonVolverInicioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent VolverActividadPrincipal = new Intent(ActividadPaginaInicioSesion.this, ActividadPrincipal.class);
                startActivity(VolverActividadPrincipal);
            }
        });

    }







