package com.example.practica_1evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class ActividadPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_principal);


        //Declaración de la imagen de la pantalla principal (logo)
        ImageButton logo = (ImageButton) findViewById(R.id.imagenLogo);


        //Declaración de los botones de la ActividadPrincipal
        Button botonPaginaRegistro = (Button) findViewById(R.id.botonPaginaRegistro);
        Button botonPaginaInicioSesion = (Button) findViewById(R.id.botonPaginaInicioSesion);




        //El botón botonPaginaRegistro me lleva a la actividad nueva.
        botonPaginaRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent actividadRegistro = new Intent(ActividadPrincipal.this, ActividadPaginaRegistro.class);
                startActivity(actividadRegistro);
            }
        });

        //Boton que me lleva a la pagina de inicio de sesion
        botonPaginaInicioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actividadInicioSesion = new Intent(ActividadPrincipal.this, ActividadPaginaInicioSesion.class);
                startActivity(actividadInicioSesion);
            }
        });
    }
}