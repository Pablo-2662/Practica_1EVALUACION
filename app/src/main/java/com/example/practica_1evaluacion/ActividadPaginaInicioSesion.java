package com.example.practica_1evaluacion;

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

public class ActividadPaginaInicioSesion extends AppCompatActivity {

    private String opcionSeleccionada="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_pagina_inicio_sesion);


        Button botonVolverInicioSesion = (Button) findViewById(R.id.botonVolverPantallaInicio);
        Button iniciarSesion = (Button) findViewById(R.id.botonCompletarInicio);
        String nombre1;
        String contraseña1;
        String puestoDeTrabajo1;
        String nombre2;
        String contraseña2;
        String puestoDeTrabajo2;
        String nombre3;
        String contraseña3;
        String puestoDeTrabajo3;
        EditText textoName = (EditText) findViewById(R.id.textoNombreInicioSesion);
        EditText textoPasswd = (EditText) findViewById(R.id.textoContraseñasInicioSesion);
        TextView textoOculto = (TextView) findViewById(R.id.textoErrorInicio);
        RadioGroup opcionesPuestoInicio = findViewById(R.id.SelectorPuestoTrabajoInicio);
        RadioButton opcionInicioDocente = findViewById(R.id.opcionDocenteInicio);
        RadioButton opcionInicioCoordinador = findViewById(R.id.opcionCoordinadorInicio);
        RadioButton opcionInicioJefeEstudios = findViewById(R.id.opcionJefeEstudiosInicio);


        //Boton que me devuelve a la pagina principal de la aplicacion
        botonVolverInicioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent VolverActividadPrincipal = new Intent(ActividadPaginaInicioSesion.this, ActividadPrincipal.class);
                startActivity(VolverActividadPrincipal);
            }
        });

        //Datos para comprobar el inicio de sesion.

        nombre1="Pablo";
        contraseña1="1234";
        puestoDeTrabajo1="Docente";

        nombre2="Juan";
        contraseña2="4321";
        puestoDeTrabajo2="Jefe de estudios";

        nombre3="Santiago";
        contraseña3="2143";
        puestoDeTrabajo3="Coordinador";



       opcionInicioDocente.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               opcionSeleccionada="Docente";
           }
       });

       opcionInicioCoordinador.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               opcionSeleccionada="Coordinador";
           }
       });

       opcionInicioJefeEstudios.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               opcionSeleccionada="Jefe de estudios";
           }
       });

        iniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name= textoName.getText().toString();
                String passwd= textoPasswd.getText().toString();
                Intent pasarAlMenu;
                Bundle datosUsuario;

                if(name.equals(nombre1)&& passwd.equals(contraseña1) && opcionSeleccionada.equals(puestoDeTrabajo1)) {
                    pasarAlMenu = new Intent(ActividadPaginaInicioSesion.this, ActividadMenuUsuario.class);
                    datosUsuario = new Bundle();
                    datosUsuario.putString("Nombre", name);
                    datosUsuario.putString("Contraseña",passwd);
                    datosUsuario.putString("Trabajo",opcionSeleccionada);
                    pasarAlMenu.putExtras(datosUsuario);
                    startActivity(pasarAlMenu);
                }else if(name.equals(nombre2)&& passwd.equals(contraseña2) && opcionSeleccionada.equals(puestoDeTrabajo2)){
                    pasarAlMenu = new Intent(ActividadPaginaInicioSesion.this, ActividadMenuUsuario.class);
                    datosUsuario = new Bundle();
                    datosUsuario.putString("Nombre", name);
                    datosUsuario.putString("Contraseña",passwd);
                    datosUsuario.putString("Trabajo",opcionSeleccionada);
                    pasarAlMenu.putExtras(datosUsuario);
                    startActivity(pasarAlMenu);
                }else if(name.equals(nombre3)&& passwd.equals(contraseña3) && opcionSeleccionada.equals(puestoDeTrabajo3)){
                    pasarAlMenu = new Intent(ActividadPaginaInicioSesion.this, ActividadMenuUsuario.class);
                    datosUsuario = new Bundle();
                    datosUsuario.putString("Nombre", name);
                    datosUsuario.putString("Contraseña",passwd);
                    datosUsuario.putString("Trabajo",opcionSeleccionada);
                    pasarAlMenu.putExtras(datosUsuario);
                    startActivity(pasarAlMenu);
                }else {
                    textoOculto.setVisibility(View.VISIBLE);
                    textoName.setText("");
                    textoPasswd.setText("");
                }


            }
        });






        
    }
}