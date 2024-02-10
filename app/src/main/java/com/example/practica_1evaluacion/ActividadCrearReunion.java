package com.example.practica_1evaluacion;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class ActividadCrearReunion extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("reuniones");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_crear_reunion);

        EditText edit_asunto = (EditText) findViewById(R.id.edittext_asunto);
        TextView textView_fecha = (TextView) findViewById(R.id.texto_fechaSeleccionada);
        TextView textView_hora = (TextView) findViewById(R.id.texto_horaSeleccionada);
        EditText editText_participante = (EditText) findViewById(R.id.edittext_correo);
        Bundle bundle = getIntent().getExtras();

        Button botonAbrirCalendario = (Button) findViewById(R.id.botonCalendario);
        TextView texto_fechaSeleccionada =(TextView) findViewById(R.id.texto_fechaSeleccionada);
        botonAbrirCalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int anio = calendar.get(Calendar.YEAR);
                int mes = calendar.get(Calendar.MONTH);
                int dia = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dpd = new DatePickerDialog(ActividadCrearReunion.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String fecha = dayOfMonth + "/" + (month + 1) + "/" + year; // Se suma 1 al mes porque en Java los meses van de 0 a 11
                        texto_fechaSeleccionada.setText(fecha);
                    }
                }, anio, mes, dia);
                dpd.show();

            }
        });

        Button botonAbrirReloj = (Button) findViewById(R.id.botonReloj);
        TextView texto_horaSeleccionada = (TextView) findViewById(R.id.texto_horaSeleccionada);
        botonAbrirReloj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int hora = calendar.get(Calendar.HOUR_OF_DAY);
                int minuto = calendar.get(Calendar.MINUTE);

                TimePickerDialog tpd = new TimePickerDialog(ActividadCrearReunion.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String horaSeleccionada = String.format("%02d:%02d", hourOfDay, minute);
                        texto_horaSeleccionada.setText(horaSeleccionada);
                    }
                }, hora, minuto, true);
                tpd.show();
            }
        });


        Button crearReunion = (Button) findViewById(R.id.botonCrearConfirmar);
        crearReunion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String asunto = edit_asunto.getText().toString();
                String fecha = textView_fecha.getText().toString();
                String hora = textView_hora.getText().toString();
                String participante = editText_participante.getText().toString();
                String idReunion = myRef.push().getKey();

                // Crear el objeto Reunion
                Reunion reunion = new Reunion(idReunion, fecha, hora, asunto, false, participante);


                myRef.child(idReunion).setValue(reunion);

                // Limpiar los EditTexts después de guardar la reunión
                edit_asunto.setText("");
                texto_fechaSeleccionada.setText("");
                texto_horaSeleccionada.setText("");
                editText_participante.setText("");

                // Opcional: mostrar un mensaje de éxito
                Snackbar.make(v, "Reunión creada con exito", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Aceptar", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(ActividadCrearReunion.this, ActividadReuniones.class);
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        })
                        .show();


            }
        });

        Button borrar = (Button) findViewById(R.id.botonBorrarReunion);
        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                texto_fechaSeleccionada.setText("");
                texto_horaSeleccionada.setText("");
                edit_asunto.setText("");
                editText_participante.setText("");
            }
        });



    }
}