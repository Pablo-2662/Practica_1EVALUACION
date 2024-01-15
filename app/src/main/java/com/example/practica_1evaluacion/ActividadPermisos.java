package com.example.practica_1evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActividadPermisos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_permisos);

        Button volverPermisos = (Button) findViewById(R.id.botonVolverPermisos);
        Bundle datosPermisos = getIntent().getExtras();


        volverPermisos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent volverActividadPermisos = new Intent(ActividadPermisos.this, ActividadMenuUsuario.class);
                volverActividadPermisos.putExtras(datosPermisos);
                startActivity(volverActividadPermisos);
            }
        });
    }
}