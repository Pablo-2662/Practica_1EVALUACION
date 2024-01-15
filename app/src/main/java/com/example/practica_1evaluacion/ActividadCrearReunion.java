package com.example.practica_1evaluacion;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class ActividadCrearReunion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_crear_reunion);

        Button botonConfirmarActividadNueva = (Button) findViewById(R.id.botonCrearConfirmar);
        Button botonEnviarReunion = (Button) findViewById(R.id.botonEnviarReunion);
        ImageButton eliminarReunion = (ImageButton) findViewById(R.id.imagenEliminarReunión);


        EditText asunto = (EditText) findViewById(R.id.textoAsunto);
        EditText participantes = (EditText) findViewById(R.id.textoParticipantes);

        Bundle datosReuniones = getIntent().getExtras();



        botonConfirmarActividadNueva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builderCrear = new AlertDialog.Builder(ActividadCrearReunion.this);
                builderCrear.setTitle("CREACCIÓN");
                builderCrear.setMessage("Se ha creado una nueva reunión");
                AlertDialog dialog2 = builderCrear.create();
                dialog2.show();
            }
        });


        botonEnviarReunion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builderEnviar = new AlertDialog.Builder(ActividadCrearReunion.this);
                builderEnviar.setTitle("CREACCIÓN");
                builderEnviar.setMessage("Se ha enviado un mensaje a los participantes");
                AlertDialog dialog3 = builderEnviar.create();
                dialog3.show();
            }
        });

        eliminarReunion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asunto.setText("");
                participantes.setText("");
                Intent volverCrearReunion = new Intent(ActividadCrearReunion.this, ActividadReuniones.class);
                volverCrearReunion.putExtras(datosReuniones);
                startActivity(volverCrearReunion);

            }
        });

    }
}