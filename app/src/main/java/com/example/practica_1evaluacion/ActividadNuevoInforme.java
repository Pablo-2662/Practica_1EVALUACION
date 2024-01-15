package com.example.practica_1evaluacion;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActividadNuevoInforme extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_nuevo_informe);

        Bundle datosInforme = getIntent().getExtras();


        Button botonVolverNuevoInforme = (Button) findViewById(R.id.botonVolverPantallaCrearInforme);
        Button botonConfirmarInforme = (Button) findViewById(R.id.botonConfirmarInforme);
        EditText textoTituloInforme = (EditText) findViewById(R.id.textoTituloInforme);
        EditText textoContenidoInforme = (EditText) findViewById(R.id.textoContenidoInforme);








        botonVolverNuevoInforme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textoTituloInforme.setText("");
                textoContenidoInforme.setText("");
                Intent volverPantallaCrearInforme = new Intent(ActividadNuevoInforme.this, ActividadAusenciasJefe.class);
                volverPantallaCrearInforme.putExtras(datosInforme);
                startActivity(volverPantallaCrearInforme);
            }
        });

        botonConfirmarInforme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builderCrearInforme = new AlertDialog.Builder(ActividadNuevoInforme.this);
                builderCrearInforme.setTitle("MENSAJE");
                builderCrearInforme.setMessage("INFORME CREADO CORRECTAMENTE");
                AlertDialog dialogInforme = builderCrearInforme.create();
                dialogInforme.show();
            }
        });


    }
}