package com.example.practica_1evaluacion;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;

public class ActividadAusenciasNotificar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_ausencias_notificar);


        MultiAutoCompleteTextView textoMultiAuto = (MultiAutoCompleteTextView) findViewById(R.id.causasDeAusencia);
        Button botonVolverNotificar = (Button) findViewById(R.id.botonVolverPantallaInformarAusencia);
        Button notificarAusencia = (Button) findViewById(R.id.botonNotificarAusencia);





        String[]opcionesCausas={"baja","enfermedad","consulta medica", "otros..."};
        ArrayAdapter<String> multiopcion = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,opcionesCausas);
        textoMultiAuto.setAdapter(multiopcion);

        botonVolverNotificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent volverPantallaNotificar = new Intent(ActividadAusenciasNotificar.this, ActividadMenuUsuario.class);
                startActivity(volverPantallaNotificar);
            }
        });

        notificarAusencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builderNotificar = new AlertDialog.Builder(ActividadAusenciasNotificar.this);
                builderNotificar.setTitle("CONFIRMACIÓN");
                builderNotificar.setMessage("Se ha  notificado  tu ausencia");
                AlertDialog dialog5 = builderNotificar.create();
                dialog5.show();
            }
        });
    }
}