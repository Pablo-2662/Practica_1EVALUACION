package com.example.practica_1evaluacion;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class ActividadMensajes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_mensajes);

        Button mensajePrueba = (Button) findViewById(R.id.ejemploMensaje);
        ImageButton enviarMensaje = (ImageButton)  findViewById(R.id.botonEnviarMensaje);
        Bundle datosMensajes = getIntent().getExtras();

        mensajePrueba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builderMensajes = new AlertDialog.Builder(ActividadMensajes.this);
                builderMensajes.setTitle("CONFIRMACIÓN");
                builderMensajes.setMessage("¿Marcar como leído?");

                builderMensajes.setPositiveButton("CONFIRMAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mensajePrueba.setBackgroundColor(Color.GREEN);
                    }
                });

                builderMensajes.setNegativeButton("RECHAZAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mensajePrueba.setBackgroundColor(Color.RED);
                    }
                });

                AlertDialog dialog = builderMensajes.create();
                dialog.show();
            }
        });



        enviarMensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actividadNuevoMensaje = new Intent(ActividadMensajes.this, ActividadNuevoMensaje.class);
                actividadNuevoMensaje.putExtras(datosMensajes);
                startActivity(actividadNuevoMensaje);
            }
        });
    }
}