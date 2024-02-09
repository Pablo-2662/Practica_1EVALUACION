package com.example.practica_1evaluacion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;


public class ActividadReuniones extends AppCompatActivity {

    private ListView listaReuniones;
    private AdaptadorReuniones adaptadorReuniones;
    private ArrayList<Reunion>reuniones;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_reuniones);
        final Context context = this;






        Button botonCrear = (Button) findViewById(R.id.botonCrearReunion);
        Button volverReunion = (Button) findViewById(R.id.botonVolverPantallaReuniones);
        Button botonActualizar = (Button) findViewById(R.id.botonActualizarReuniones);

        Intent intent = getIntent();
        Bundle datosUsuarioRecogidos = intent.getExtras();
        String correoRecogido = datosUsuarioRecogidos.getString("correo");

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("usuario");
        Query query = databaseReference.orderByChild("email").equalTo(correoRecogido);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Usuario usuario = dataSnapshot.getValue(Usuario.class);
                        String puesto="";

                        if (usuario != null) {
                            puesto = usuario.getPuesto();
                            // Resto del código
                        }
                        System.out.println("PUESTO EXTRAIDO "+puesto);
                        if(puesto.equals("Docente")){
                            botonCrear.setVisibility(View.INVISIBLE);
                            botonCrear.setEnabled(false);

                        }else if(puesto.equals("JefeEstudios")){

                        }else if(puesto.equals("Coordinador")){

                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //ACCIÓN REALIZADA AL PULSAR EL BOTÓN DE ACTUALIZAR
        listaReuniones = findViewById(R.id.listview_reuniones);
        botonActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReferenceReuniones = FirebaseDatabase.getInstance().getReference("reuniones");
                Query query = databaseReferenceReuniones.orderByChild("correoUsuario").equalTo(correoRecogido);

                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        reuniones = new ArrayList<>();
                        for (DataSnapshot snap : snapshot.getChildren()) {
                            Reunion reunion = snap.getValue(Reunion.class);
                            reuniones.add(reunion);
                            System.out.println(reuniones.size());
                        }
                        adaptadorReuniones = new AdaptadorReuniones(context, R.layout.estilo_listview_reuniones,reuniones);

                        listaReuniones.setAdapter(adaptadorReuniones);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });



        botonCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actividadCrear = new Intent(ActividadReuniones.this, ActividadCrearReunion.class);
                actividadCrear.putExtras(datosUsuarioRecogidos);
                startActivity(actividadCrear);
            }
        });







        volverReunion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent volverReuniones = new Intent(ActividadReuniones.this, ActividadMenuUsuario.class);
                volverReuniones.putExtras(datosUsuarioRecogidos);
                startActivity(volverReuniones);
            }
        });



    }
}