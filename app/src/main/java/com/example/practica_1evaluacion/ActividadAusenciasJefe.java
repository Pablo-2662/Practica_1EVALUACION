package com.example.practica_1evaluacion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ActividadAusenciasJefe extends AppCompatActivity {

    private ListView listaAusencias;
    private AdaptadorAusencias adaptadorAusencias;
    private ArrayList<Ausencia> ausencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_ausencias_jefe);
        final Context context = this;

        Button botonVolverAusenciasJefe = (Button) findViewById(R.id.botonVolverPantallaAusenciasJefe);
        Bundle datosAusencias = getIntent().getExtras();
        String correoJefe = datosAusencias.getString("correo");
        Button actualizar = (Button) findViewById(R.id.botonActualizarAusencias);

        listaAusencias = (ListView) findViewById(R.id.listaAusencias);
        botonVolverAusenciasJefe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent volverAusenciasJefe = new Intent(ActividadAusenciasJefe.this, ActividadMenuUsuario.class);
                volverAusenciasJefe.putExtras(datosAusencias);
                startActivity(volverAusenciasJefe);


            }
        });
        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReferenceReuniones = FirebaseDatabase.getInstance().getReference("ausencias");
                Query query = databaseReferenceReuniones.orderByChild("correoJefe").equalTo(correoJefe);

                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ausencias = new ArrayList<>();
                        for (DataSnapshot snap : snapshot.getChildren()) {
                            Ausencia ausencia = snap.getValue(Ausencia.class);
                            ausencias.add(ausencia);
                        }
                        adaptadorAusencias = new AdaptadorAusencias(context, R.layout.diseno_ausencias,ausencias);

                        listaAusencias.setAdapter(adaptadorAusencias);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });




    }
}