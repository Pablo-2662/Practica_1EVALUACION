package com.example.practica_1evaluacion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ActividadPaginaInicioSesion extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_pagina_inicio_sesion);

        //DEFINICIÓN DE BOTONES
        Button botonVolverInicioSesion = (Button) findViewById(R.id.botonVolverPantallaInicio);
        Button iniciarSesion = (Button) findViewById(R.id.botonCompletarInicio);


        //DEFINICIÓN DE CAMPOS
        EditText textoMail = (EditText) findViewById(R.id.textoCorreoInicioSesion);
        EditText textoPasswd = (EditText) findViewById(R.id.textoContrasenyaInicioSesion);
        RadioButton opcionInicioDocente = findViewById(R.id.opcionDocenteInicio);
        RadioButton opcionInicioCoordinador = findViewById(R.id.opcionCoordinadorInicio);
        RadioButton opcionInicioJefeEstudios = findViewById(R.id.opcionJefeEstudiosInicio);












        botonVolverInicioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent VolverActividadPrincipal = new Intent(ActividadPaginaInicioSesion.this, ActividadPrincipal.class);
                startActivity(VolverActividadPrincipal);
            }
        });

        iniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String correo = textoMail.getText().toString();
                final String contrasena = textoPasswd.getText().toString();
                String puesto = "";

                if (opcionInicioDocente.isChecked()) {
                    puesto = "Docente";
                } else if (opcionInicioCoordinador.isChecked()) {
                    puesto = "Coordinador";
                } else if (opcionInicioJefeEstudios.isChecked()) {
                    puesto = "JefeEstudios";
                }

                // Verificar si el usuario existe en la base de datos
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("usuario");
                databaseReference.child(correo).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            // El usuario existe, verificar la contraseña
                            Usuario usuario = dataSnapshot.getValue(Usuario.class);
                            if (usuario != null && usuario.getContrasena().equals(contrasena)) {
                                // Contraseña correcta, pasar a la siguiente actividad
                                Bundle datosEnviados = new Bundle();
                                datosEnviados.putString("correo",correo);
                                Intent intent = new Intent(ActividadPaginaInicioSesion.this, ActividadMenuUsuario.class);
                                intent.putExtras(datosEnviados);
                                startActivity(intent);
                            } else {
                                // Contraseña incorrecta
                                Snackbar.make(findViewById(R.id.inicioSesion), "Contraseña incorrecta", Snackbar.LENGTH_SHORT).show();
                            }
                        } else {
                            // El usuario no existe
                            Snackbar.make(findViewById(R.id.inicioSesion), "Usuario no encontrado. Regístrate primero.", Snackbar.LENGTH_SHORT).show();
                            Intent intent = new Intent(ActividadPaginaInicioSesion.this, ActividadPaginaRegistro.class);
                             startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


    }}







