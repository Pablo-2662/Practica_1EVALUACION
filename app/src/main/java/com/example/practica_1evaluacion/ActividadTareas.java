package com.example.practica_1evaluacion;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActividadTareas extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_tareas);

        Intent intentTareas = getIntent();
        Bundle datosUsuarioTareas = intentTareas.getExtras();
        String nombreRecogido="";
        String contraseñaRecogida="";

        nombreRecogido = datosUsuarioTareas.getString("Nombre");
        contraseñaRecogida = datosUsuarioTareas.getString("Contraseña");


        Button botonVolverTareas = (Button) findViewById(R.id.botonVolverTareas);
        Button añadirTarea = (Button) findViewById(R.id.botonAgregarTarea);



        botonVolverTareas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent volverPantallaTareas = new Intent(ActividadTareas.this, ActividadMenuUsuario.class);
                volverPantallaTareas.putExtras(datosUsuarioTareas);
                startActivity(volverPantallaTareas);
            }
        });

        añadirTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String  puestoRecogido = datosUsuarioTareas.getString("Trabajo");
                if (puestoRecogido.equals("Jefe de estudios")){
                    Intent pantallaCrearTareas = new Intent(ActividadTareas.this, ActividadCrearTarea.class);
                    startActivity(pantallaCrearTareas);
                }else{
                    AlertDialog.Builder builderTareas = new AlertDialog.Builder(ActividadTareas.this);
                    builderTareas.setTitle("ERROR");
                    builderTareas.setMessage("No puedes acceder a esta opción");
                    AlertDialog dialogTareas = builderTareas.create();
                    dialogTareas.show();
                }
            }
        });
    }
}