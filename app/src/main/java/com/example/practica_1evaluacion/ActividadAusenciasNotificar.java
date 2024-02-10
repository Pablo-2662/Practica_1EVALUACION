package com.example.practica_1evaluacion;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ActividadAusenciasNotificar extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("ausencias");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_ausencias_notificar);

        EditText editText_correoJefe = (EditText) findViewById(R.id.edittext_correoJefe);
        MaterialButton fecha = (MaterialButton) findViewById(R.id.botonCalendario);
        MaterialButton hora = (MaterialButton) findViewById(R.id.botonReloj);
        TextView fechaSeleccionada = (TextView) findViewById(R.id.texto_fechaSeleccionada);
        TextView horaSeleccionada = (TextView) findViewById(R.id.texto_horaSeleccionada);
        Spinner spinerMotivo = (Spinner) findViewById(R.id.spinerOpciones);
        Button botonBorrar = (Button) findViewById(R.id.botonBorrarAusencia);
        Button botonEnviar = (Button) findViewById(R.id.botonConfirmarAusencia);

        Bundle bundle = getIntent().getExtras();
        String correoRecogido = bundle.getString("correo");

        List<String> listaCadenas = new ArrayList<>();
        listaCadenas.add("ENFERMEDAD");
        listaCadenas.add("CITA MÉDICA");
        listaCadenas.add("PROBLEMA TRANSPORTE");
        listaCadenas.add("CLIMA");
        listaCadenas.add("OTRO");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaCadenas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Establecer el adaptador en el Spinner
        spinerMotivo.setAdapter(adapter);



        hora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int hora = calendar.get(Calendar.HOUR_OF_DAY);
                int minuto = calendar.get(Calendar.MINUTE);

                TimePickerDialog tpd = new TimePickerDialog(ActividadAusenciasNotificar.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String hora = String.format("%02d:%02d", hourOfDay, minute);
                        horaSeleccionada.setText(hora);
                    }
                }, hora, minuto, true);
                tpd.show();
            }

        });

        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int anio = calendar.get(Calendar.YEAR);
                int mes = calendar.get(Calendar.MONTH);
                int dia = calendar.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog dpd = new DatePickerDialog(ActividadAusenciasNotificar.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String fecha = dayOfMonth + "/" + (month + 1) + "/" + year;
                        fechaSeleccionada.setText(fecha);
                    }
                }, anio, mes, dia);
                dpd.show();
            }
        });
        botonBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText_correoJefe.setText("");
                fechaSeleccionada.setText("");
                horaSeleccionada.setText("");
            }
        });


        botonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correoJefe = editText_correoJefe.getText().toString();
                String fecha = fechaSeleccionada.getText().toString();
                String hora = horaSeleccionada.getText().toString();
                String motivo = spinerMotivo.getSelectedItem().toString();
                String idAusencia = myRef.push().getKey();


                Ausencia ausencia = new Ausencia(idAusencia,correoRecogido,correoJefe,fecha,hora,motivo);
                myRef.child(idAusencia).setValue(ausencia);

                editText_correoJefe.setText("");
                fechaSeleccionada.setText("");
                horaSeleccionada.setText("");

                Snackbar.make(v, "Ausencia enviada con exito", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Aceptar", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(ActividadAusenciasNotificar.this, ActividadMenuUsuario.class);
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        })
                        .show();
            }
        });

    }
}