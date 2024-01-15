package com.example.practica_1evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActividadPaginaRegistro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_pagina_registro);

        //DECLARACIÓN DE VARIABLES
        Button botonVolverRegistro = (Button) findViewById(R.id.botonVolverPantallaRegistro);
        Button botonHecho = (Button) findViewById(R.id.botonCompletarRegistro);



        botonVolverRegistro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent ActividadRegistro = new Intent(ActividadPaginaRegistro.this, ActividadPrincipal.class);
                startActivity(ActividadRegistro);
            }
        });

        botonHecho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent IrPantallaInicioSesion = new Intent(ActividadPaginaRegistro.this, ActividadPaginaInicioSesion.class);
                startActivity(IrPantallaInicioSesion);
            }
        });
    }
}