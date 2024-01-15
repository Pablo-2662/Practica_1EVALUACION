package com.example.practica_1evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.MultiAutoCompleteTextView;

public class ActividadCrearTarea extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_crear_tarea);

        MultiAutoCompleteTextView textoTipoTarea = (MultiAutoCompleteTextView) findViewById(R.id.tipoTarea);
        CheckBox opcionIndividual = (CheckBox) findViewById(R.id.opcionIndividual);
        CheckBox opcionColectiva = (CheckBox) findViewById(R.id.opcionColectiva);

        String[]opcionesTipos={"entreaga de programaciones","entrega de hojas mensuales de actividad","firma de actas", "otros..."};
        ArrayAdapter<String> multiTarea = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,opcionesTipos);
        textoTipoTarea.setAdapter(multiTarea);


        opcionIndividual.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //Codigo a ejecutar si se selecciona.

                }else{

                }
            }
        });
    }
}