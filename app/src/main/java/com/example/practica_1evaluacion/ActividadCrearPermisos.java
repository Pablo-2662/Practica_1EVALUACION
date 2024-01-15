package com.example.practica_1evaluacion;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActividadCrearPermisos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_crear_permisos);

        Bundle datosSolicitar = getIntent().getExtras();

        Button volverSolicitar = (Button) findViewById(R.id.botonVolverPermisos);
        Button solicitar = (Button) findViewById(R.id.botonSolicitarPermiso);



        volverSolicitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent volverSolicitarPermiso = new Intent(ActividadCrearPermisos.this, ActividadMenuUsuario.class);
                volverSolicitarPermiso.putExtras(datosSolicitar);
                startActivity(volverSolicitarPermiso);
            }
        });

        solicitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builderSolicitar = new AlertDialog.Builder(ActividadCrearPermisos.this);
                builderSolicitar.setTitle("INFORMACIÓN");
                builderSolicitar.setMessage("Se ha solicitado el permiso");
                AlertDialog dialogSolicitar = builderSolicitar.create();
                dialogSolicitar.show();
            }
        });
    }
}