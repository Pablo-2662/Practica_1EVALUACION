package com.example.practica_1evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CrearPermiso extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("permisos");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_permiso);


        Bundle bundle = getIntent().getExtras();
        String correoUsuario = bundle.getString("correo");

        Button botonCrearPermiso = (Button) findViewById(R.id.botonCrearPermiso);
        Button botonBorrarPermiso = (Button) findViewById(R.id.botonBorrarPermiso);
        Button fechaInicio = (Button) findViewById(R.id.botonSeleccionarFechaInicio);
        Button fechaFin = (Button) findViewById(R.id.botonSeleccionarFechaFin);
        EditText editText_correoUsuario = (EditText) findViewById(R.id.correoUsuario);
        Spinner spinner_tipo = (Spinner) findViewById(R.id.spinnerTipoPermiso);
        TextView textView_textoInicio = (TextView) findViewById(R.id.textoFechaInicio);
        TextView textView_textoFin = (TextView) findViewById(R.id.textoFechaFin);


        fechaInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int anio = calendar.get(Calendar.YEAR);
                int mes = calendar.get(Calendar.MONTH);
                int dia = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dpd = new DatePickerDialog(CrearPermiso.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String fecha = dayOfMonth + "/" + (month + 1) + "/" + year; // Se suma 1 al mes porque en Java los meses van de 0 a 11
                        textView_textoInicio.setText(fecha);
                    }
                }, anio, mes, dia);
                dpd.show();
            }
        });

        fechaFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int anio = calendar.get(Calendar.YEAR);
                int mes = calendar.get(Calendar.MONTH);
                int dia = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dpd = new DatePickerDialog(CrearPermiso.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String fecha = dayOfMonth + "/" + (month + 1) + "/" + year; // Se suma 1 al mes porque en Java los meses van de 0 a 11
                        textView_textoFin.setText(fecha);
                    }
                }, anio, mes, dia);
                dpd.show();
            }
        });


        List<String> tipos = new ArrayList<>();
        tipos.add("ENFERMEDAD / INCAPACIDAD");
        tipos.add("PATERNIDAD / MATERNIDAD");
        tipos.add("VIAJE / FORMACIÓN");
        tipos.add("DUELO");
        tipos.add("OTRO");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tipos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Establecer el adaptador en el Spinner
        spinner_tipo.setAdapter(adapter);



        botonCrearPermiso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correo = editText_correoUsuario.getText().toString();
                String fechaInicio = textView_textoInicio.getText().toString();
                String fechaFin  = textView_textoFin.getText().toString();
                String tipo = spinner_tipo.getSelectedItem().toString();
                String idPermiso = myRef.push().getKey();

                Permiso p = new Permiso(idPermiso,correoUsuario,correo,tipo,fechaInicio,fechaFin,false);
                myRef.child(idPermiso).setValue(p);
                editText_correoUsuario.setText("");
                textView_textoFin.setText("");
                textView_textoInicio.setText("");




                Snackbar.make(v, "Permiso solicitado", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Aceptar", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(CrearPermiso.this, ActividadMenuUsuario.class);
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        })
                        .show();
            }
        });

    }
}