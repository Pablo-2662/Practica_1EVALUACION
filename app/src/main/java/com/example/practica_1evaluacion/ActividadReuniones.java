package com.example.practica_1evaluacion;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class ActividadReuniones extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_reuniones);

        Intent intent2 = getIntent();
        Bundle datosRecogidosReuniones = intent2.getExtras();
        String nombreRecogidoReuniones = datosRecogidosReuniones.getString("Nombre");
        String puestoRecogidoReuniones = datosRecogidosReuniones.getString("Trabajo");


        Button botonCrear = (Button) findViewById(R.id.botonCrearReunion);
        Button volverReunion = (Button) findViewById(R.id.botonVolverPantallaReuniones);
        Button botonActualizar = (Button) findViewById(R.id.botonActualizarReuniones);

        if(puestoRecogidoReuniones.equals("Docente")){
            botonCrear.setEnabled(false);
            botonCrear.setVisibility(View.INVISIBLE);
        }





        volverReunion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent volverReuniones = new Intent(ActividadReuniones.this, ActividadMenuUsuario.class);
                volverReuniones.putExtras(datosRecogidosReuniones);
                startActivity(volverReuniones);
            }
        });



        //Botón para crear reunión.
        botonCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(puestoRecogidoReuniones.equals("Coordinador") || puestoRecogidoReuniones.equals("Jefe de estudios")){
                    Intent crearReunion = new Intent(ActividadReuniones.this, ActividadCrearReunion.class);
                    crearReunion.putExtras(datosRecogidosReuniones);
                    startActivity(crearReunion);

                }else if(puestoRecogidoReuniones.equals("Docente")){
                    AlertDialog.Builder permitido = new AlertDialog.Builder(ActividadReuniones.this);
                    permitido.setTitle("PERMISOS");
                    permitido.setMessage("NO TIENES PERMITIDA ESTA OPCIÓN");
                    AlertDialog dialog2 = permitido.create();
                    dialog2.show();

                }
            }
        });




    }
}