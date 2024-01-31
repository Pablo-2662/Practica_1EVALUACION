package com.example.practica_1evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ActividadPaginaRegistro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_pagina_registro);

        //DECLARACIÓN DE BOTONES
        Button botonVolverRegistro = (Button) findViewById(R.id.botonVolverPantallaRegistro);
        Button botonHecho = (Button) findViewById(R.id.botonCompletarRegistro);


        //DECLARACIÓN DE CAMPOS RELLENABLES

        EditText editText_correo = (EditText) findViewById(R.id.textoEmailRegistro);
        EditText editText_nombre = (EditText) findViewById(R.id.textoNombreRegistro);
        EditText editText_contrasena = (EditText) findViewById(R.id.textoContrasenyaRegistro);
        EditText editText_telefono = (EditText) findViewById(R.id.textoTelefonoRegistro);
        RadioButton opcionDocente = (RadioButton) findViewById(R.id.opcionDocente);
        RadioButton opcionCoordinador = (RadioButton) findViewById(R.id.opcionCoordinador);
        RadioButton opcionJefe = (RadioButton) findViewById(R.id.opcionJefeEstudios);




        botonVolverRegistro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent ActividadRegistro = new Intent(ActividadPaginaRegistro.this, ActividadPrincipal.class);
                startActivity(ActividadRegistro);
            }
        });

        //Intent IrPantallaInicioSesion = new Intent(ActividadPaginaRegistro.this, ActividadPaginaInicioSesion.class);
        //startActivity(IrPantallaInicioSesion);

        botonHecho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String correoUsuario = String.valueOf(editText_correo.getText());
                String nombreUsuario = editText_nombre.getText().toString();
                String contrasena = editText_contrasena.getText().toString();
                String telefono = editText_telefono.getText().toString();
                String puesto="";
                if(opcionDocente.isChecked()){
                    puesto = "Docente";
                }else if(opcionCoordinador.isChecked()){
                    puesto = "Coordinador";
                }else if(opcionJefe.isChecked()){
                    puesto="JefeEstudios";
                }


                if(nombreUsuario.length()>30){
                    editText_nombre.setText("longitud no permitida");
                    editText_nombre.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                }else{
                    if(correoUsuario.length()>30 || correoUsuario.contains(".")){
                        editText_correo.setText("Longitud no permitida / cambia el . por _");
                        editText_correo.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                    }else{
                        if(contrasena.length()>10){
                            editText_contrasena.setText("Longitud no permitida");
                            editText_contrasena.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                        }else{
                            if(telefono.length()>9){
                                editText_telefono.setText("Longitud no permitida");
                                editText_telefono.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                            }else{


                                Usuario u = new Usuario(correoUsuario, nombreUsuario, contrasena, telefono, puesto);
                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                                DatabaseReference usuariosReference = databaseReference.child("usuario");

                                usuariosReference.child(correoUsuario).setValue(u);
                                Snackbar.make(findViewById(R.id.layout_registro), "Registrado", Snackbar.LENGTH_SHORT);
                            }
                        }
                    }
                }



            }
        });
    }
}