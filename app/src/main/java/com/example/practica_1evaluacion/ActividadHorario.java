package com.example.practica_1evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActividadHorario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_horario);

        Bundle datosUsuarioRecogidos = getIntent().getExtras();

        Button botonVolverHorario = (Button) findViewById(R.id.botonVolverPantallaHorario);
        botonVolverHorario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent volverHorario = new Intent(ActividadHorario.this, ActividadMenuUsuario.class);
                volverHorario.putExtras(datosUsuarioRecogidos);
                startActivity(volverHorario);
            }
        });
    }
}