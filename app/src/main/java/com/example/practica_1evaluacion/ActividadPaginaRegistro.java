package com.example.practica_1evaluacion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

                Bundle datosRegistro = new Bundle();
                datosRegistro.putString("correo",correoUsuario);
                datosRegistro.putString("nombre",nombreUsuario);
                datosRegistro.putString("contrasena",contrasena);
                datosRegistro.putString("tel",telefono);
                datosRegistro.putString("puesto",puesto);


                if(nombreUsuario.length()>30){
                    editText_nombre.setText("longitud no permitida");

                }else{
                    if(correoUsuario.length()>30 || correoUsuario.contains(".")){
                        editText_correo.setText("Longitud no permitida / cambia el . por _");

                    }else{
                        if(contrasena.length()>10){
                            editText_contrasena.setText("Longitud no permitida");

                        }else{
                            if(telefono.length()>9){
                                editText_telefono.setText("Longitud no permitida");

                            }else{

                                final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("usuario");
                                String finalPuesto = puesto;
                                databaseReference.child(correoUsuario).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.exists()) {
                                            // El correo electrónico ya existe en la base de datos
                                            Snackbar.make(findViewById(R.id.layout_registro), "El usuario ya está en uso. Inicia sesión.", Snackbar.LENGTH_SHORT).show();
                                        } else {
                                            // El correo electrónico no existe en la base de datos, se puede registrar el usuario
                                            Usuario u = new Usuario(correoUsuario, nombreUsuario, contrasena, telefono, finalPuesto);
                                            databaseReference.child(correoUsuario).setValue(u);
                                            Snackbar.make(findViewById(R.id.layout_registro), "Registrado!!!", Snackbar.LENGTH_INDEFINITE)
                                                    .setAction("Aceptar", new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View v) {
                                                            Intent IrPantallaInicioSesion = new Intent(ActividadPaginaRegistro.this, ActividadPaginaInicioSesion.class);
                                                            IrPantallaInicioSesion.putExtras(datosRegistro);
                                                            startActivity(IrPantallaInicioSesion);
                                                        }
                                                    })
                                                    .show();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });

                            }
                        }
                    }
                }



            }
        });
    }
}