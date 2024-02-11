package com.example.practica_1evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ActividadCrearTarea extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("tareas");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_crear_tarea);

        Bundle bundle = getIntent().getExtras();
        String correoRecogido = bundle.getString("correo");

        EditText edit_descripcion = (EditText) findViewById(R.id.descripcionTarea);
        RadioButton opcionIndividual = (RadioButton) findViewById(R.id.opcionIndividual);
        RadioButton opcionColectiva = (RadioButton) findViewById(R.id.opcionColectiva);
        TextView textoFechaSeleccionada = (TextView) findViewById(R.id.textoFechaLimite);
        EditText edit_correoDestino = (EditText) findViewById(R.id.CorreoDestino);
        Button botonSeleccionarFecha = (Button) findViewById(R.id.botonSeleccionarFecha);
        Button botonBorrar = (Button) findViewById(R.id.botonBorrarTarea);
        Spinner spinner = (Spinner) findViewById(R.id.spinnerCategoria);
        Button botonCrear = (Button) findViewById(R.id.botonCrearTarea);

        List<String> categorias = new ArrayList<>();
        categorias.add("ASESORAMIENTO");
        categorias.add("EVALUACION");
        categorias.add("EXAMEN");
        categorias.add("CALIFICACIONES");
        categorias.add("OTRO");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categorias);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Establecer el adaptador en el Spinner
        spinner.setAdapter(adapter);


        botonSeleccionarFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int anio = calendar.get(Calendar.YEAR);
                int mes = calendar.get(Calendar.MONTH);
                int dia = calendar.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog dpd = new DatePickerDialog(ActividadCrearTarea.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String fecha = dayOfMonth + "/" + (month + 1) + "/" + year;
                        textoFechaSeleccionada.setText(fecha);
                    }
                }, anio, mes, dia);
                dpd.show();
            }
        });

        botonBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_descripcion.setText("");
                edit_correoDestino.setText("");
                textoFechaSeleccionada.setText("");
                edit_correoDestino.setText("");
            }
        });

        botonCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String descripcion = edit_descripcion.getText().toString();
                String fecha = textoFechaSeleccionada.getText().toString();
                String correoDestino = edit_correoDestino.getText().toString();
                String correoEmisor = correoRecogido;
                String opcionTipo ="";
                if (opcionColectiva.isChecked()){
                    opcionTipo = "Colectiva";
                }else if(opcionIndividual.isChecked()){
                    opcionTipo = "Individual";
                }
                String categoria = spinner.getSelectedItem().toString();
                String idTarea = myRef.push().getKey();


                Tarea t = new Tarea(idTarea,descripcion,opcionTipo,categoria,correoEmisor,correoDestino,fecha);
                myRef.child(idTarea).setValue(t);
                edit_descripcion.setText("");
                edit_correoDestino.setText("");
                textoFechaSeleccionada.setText("");
                edit_correoDestino.setText("");




                Snackbar.make(v, "Tarea creada", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Aceptar", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(ActividadCrearTarea.this, ActividadMenuUsuario.class);
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        })
                        .show();
            }
        });



    }
}