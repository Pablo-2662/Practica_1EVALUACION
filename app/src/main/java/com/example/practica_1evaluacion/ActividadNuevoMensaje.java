package com.example.practica_1evaluacion;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActividadNuevoMensaje extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_nuevo_mensaje);


        Bundle datosNuevoMensaje = getIntent().getExtras();
        Button enviarMensaje = (Button) findViewById(R.id.enviar);


        enviarMensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builderNew = new AlertDialog.Builder(ActividadNuevoMensaje.this);
                builderNew.setTitle("INFORMACIÓN");
                builderNew.setMessage("SE HA ENVIADO EL MENSAJE");
                AlertDialog dialogNew = builderNew.create();
                dialogNew.show();
            }
        });
    }
}