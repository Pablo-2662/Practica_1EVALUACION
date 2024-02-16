package com.example.practica_1evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ActividadAsignarGuardia extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("guardias");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_asignar_guardia);

        EditText editText_correoGuardia = (EditText) findViewById(R.id.correoGuardia);
        Spinner spinner_motivoGuardia = (Spinner) findViewById(R.id.motivoGuardia);
        TextView textView_fechaGuardia = (TextView) findViewById(R.id.textoFechaGuardia);
        TextView textView_horaGuardia = (TextView) findViewById(R.id.textoHoraGuardia);
        Button boton_fechaGuardia = (Button) findViewById(R.id.botonSeleccionarFecha);
        Button boton_horaGuardia = (Button) findViewById(R.id.botonSeleccionarHora);
        Button boton_borrarGuardia = (Button) findViewById(R.id.botonBorrarGuardia);
        Button boton_AsignarGuardia = (Button) findViewById(R.id.botonCrearGuardia);

        Bundle bundle = getIntent().getExtras();
        String correoRecogido = bundle.getString("correo");

        List<String> listaCadenas = new ArrayList<>();
        listaCadenas.add("ENFERMEDAD REPENTINA");
        listaCadenas.add("EMERGENCIA PERSONAL");
        listaCadenas.add("PROBLEMAS DE TRANSPORTE");
        listaCadenas.add("CUESTIONES LEGALES");
        listaCadenas.add("OTRO");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaCadenas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Establecer el adaptador en el Spinner
        spinner_motivoGuardia.setAdapter(adapter);


        boton_fechaGuardia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int anio = calendar.get(Calendar.YEAR);
                int mes = calendar.get(Calendar.MONTH);
                int dia = calendar.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog dpd = new DatePickerDialog(ActividadAsignarGuardia.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String fecha = dayOfMonth + "/" + (month + 1) + "/" + year;
                        textView_fechaGuardia.setText(fecha);
                    }
                }, anio, mes, dia);
                dpd.show();

            }
        });

        boton_horaGuardia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int hora = calendar.get(Calendar.HOUR_OF_DAY);
                int minuto = calendar.get(Calendar.MINUTE);

                TimePickerDialog tpd = new TimePickerDialog(ActividadAsignarGuardia.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String hora = String.format("%02d:%02d", hourOfDay, minute);
                        textView_horaGuardia.setText(hora);
                    }
                }, hora, minuto, true);
                tpd.show();
            }
        });

        boton_AsignarGuardia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correo = editText_correoGuardia.getText().toString();
                String fecha = textView_fechaGuardia.getText().toString();
                String hora = textView_horaGuardia.getText().toString();
                String motivo = spinner_motivoGuardia.getSelectedItem().toString();
                String idGuardia = myRef.push().getKey();


                Guardia guardia = new Guardia(idGuardia,correo,fecha,hora,motivo);
                myRef.child(idGuardia).setValue(guardia);

                editText_correoGuardia.setText("");
                textView_fechaGuardia.setText("");
                textView_horaGuardia.setText("");

                Snackbar.make(v, "Guardia asignada a "+correo, Snackbar.LENGTH_INDEFINITE)
                        .setAction("Aceptar", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(ActividadAsignarGuardia.this, ActividadMenuUsuario.class);
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        })
                        .show();
            }
        });
    }
}