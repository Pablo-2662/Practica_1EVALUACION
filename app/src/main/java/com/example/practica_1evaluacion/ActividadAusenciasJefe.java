package com.example.practica_1evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActividadAusenciasJefe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_ausencias_jefe);

        Button botonVolverAusenciasJefe = (Button) findViewById(R.id.botonVolverPantallaAusenciasJefe);
        Button botonAñadirInforme = (Button) findViewById(R.id.botonAñadirInforme);

        Bundle datosAusencias = getIntent().getExtras();

        botonVolverAusenciasJefe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent volverAusenciasJefe = new Intent(ActividadAusenciasJefe.this, ActividadMenuUsuario.class);
                volverAusenciasJefe.putExtras(datosAusencias);
                startActivity(volverAusenciasJefe);
            }
        });

        botonAñadirInforme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent crearNuevoInforme = new Intent(ActividadAusenciasJefe.this, ActividadNuevoInforme.class);
                crearNuevoInforme.putExtras(datosAusencias);
                startActivity(crearNuevoInforme);
            }
        });
    }
}