package com.example.practica_1evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActividadPerfil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_perfil);


        EditText datosNombre = (EditText) findViewById(R.id.datoNombre);
        EditText datosPuesto = (EditText) findViewById(R.id.datoPuesto);
        EditText datosTelefono = (EditText) findViewById(R.id.datoTelefono);
        EditText datosCorreo = (EditText) findViewById(R.id.datoCorreo);

        Intent intent = getIntent();
        Bundle datosRecogidosPerfil = intent.getExtras();
        String nombreRecogido = datosRecogidosPerfil.getString("Nombre");
        String puestoRecogido = datosRecogidosPerfil.getString("Trabajo");
        datosNombre.setText(nombreRecogido);
        datosPuesto.setText(puestoRecogido);







        //Volvemos al menú de usuario
        Button volverPerfil = (Button) findViewById(R.id.botonVolverPantallaPerfil);
        volverPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent volverActividadPerfil = new Intent(ActividadPerfil.this, ActividadMenuUsuario.class);
                volverActividadPerfil.putExtras(datosRecogidosPerfil);
                startActivity(volverActividadPerfil);
            }
        });

        Button botonEditar = (Button) findViewById(R.id.botonEditarPerfil);
        botonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datosNombre.setEnabled(true);
                datosPuesto.setEnabled(true);
                datosTelefono.setEnabled(true);
                datosCorreo.setEnabled(true);

            }
        });

        Button botonHecho = (Button) findViewById(R.id.botonConfirmarEdiccion);
        botonHecho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datosNombre.setEnabled(false);
                datosPuesto.setEnabled(false);
                datosTelefono.setEnabled(false);
                datosCorreo.setEnabled(false);
            }
        });

    }
}