package com.example.practica_1evaluacion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ActividadHorario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_horario);

        Bundle datosUsuarioRecogidos = getIntent().getExtras();
        String correoRecogido = datosUsuarioRecogidos.getString("correo");

        TextView lunes = (TextView) findViewById(R.id.textoLunes);
        TextView martes = (TextView) findViewById(R.id.textoMartes);
        TextView miercoles = (TextView) findViewById(R.id.textoMiercoles);
        TextView jueves = (TextView) findViewById(R.id.textJueves);
        TextView viernes = (TextView) findViewById(R.id.textViernes);

        Button botonIrGuardias = (Button) findViewById(R.id.botonGuardiasH);
        Button botonIrReuniones = (Button) findViewById(R.id.botonReunionH);
        Button botonVolver = (Button) findViewById(R.id.botonVolverPantallaHorario);
        Button botonEditar = (Button) findViewById(R.id.boton_EditarHorario);
        Button botonGuardar = (Button) findViewById(R.id.guardarHorario);

        TextView numeroGuardias = (TextView) findViewById(R.id.numeroGuardias);
        TextView numeroReunion = (TextView) findViewById(R.id.numeroReuniones);

        lunes.setEnabled(false);
        martes.setEnabled(false);
        miercoles.setEnabled(false);
        jueves.setEnabled(false);
        viernes.setEnabled(false);


        DatabaseReference databaseReferenceReunion = FirebaseDatabase.getInstance().getReference("reuniones");
        Query queryreunion = databaseReferenceReunion.orderByChild("correoUsuario").equalTo(correoRecogido);

        queryreunion.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Reunion> reuniones = new ArrayList<>();
                for (DataSnapshot ssnapshot : snapshot.getChildren()) {
                    Reunion reunion = ssnapshot.getValue(Reunion.class);
                    reuniones.add(reunion);
                }
                numeroReunion.setText(String.valueOf(reuniones.size()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        DatabaseReference databaseReferenceGuardias = FirebaseDatabase.getInstance().getReference("guardias");
        Query queryguardia = databaseReferenceGuardias.orderByChild("correoGuardia").equalTo(correoRecogido);

        queryguardia.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Guardia> guardias = new ArrayList<>();
                for (DataSnapshot ssnapshot : snapshot.getChildren()) {
                    Guardia g = ssnapshot.getValue(Guardia.class);
                    guardias.add(g);
                }
                numeroGuardias.setText(String.valueOf(guardias.size()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("horarios");
        Query query = databaseReference.orderByChild("correoUsuario").equalTo(correoRecogido);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                    if (snapshot.exists()) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Horario h  = dataSnapshot.getValue(Horario.class);
                            lunes.setText(h.getCadenaLunes());
                            martes.setText(h.getCadenaMartes());
                            miercoles.setText(h.getCadenaMiercoles());
                            jueves.setText(h.getCadenaJueves());
                            viernes.setText(h.getCadenaViernes());

                        }
                    }
                }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        botonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lunes.setEnabled(true);
                martes.setEnabled(true);
                miercoles.setEnabled(true);
                jueves.setEnabled(true);
                viernes.setEnabled(true);
            }
        });


        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lunes.setEnabled(false);
                martes.setEnabled(false);
                miercoles.setEnabled(false);
                jueves.setEnabled(false);
                viernes.setEnabled(false);

                String cLunes, cMartes, cMiercoles, cJueves, cViernes;
                cLunes= lunes.getText().toString();
                cMartes = martes.getText().toString();
                cMiercoles = miercoles.getText().toString();
                cJueves = jueves.getText().toString();
                cViernes = viernes.getText().toString();


                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("horarios");
                Query query = databaseReference.orderByChild("correoUsuario").equalTo(correoRecogido);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                                String clavePrimaria = dataSnapshot.getKey();
                                Map<String, Object> actualizacionMap = new HashMap<>();
                                actualizacionMap.put("cadenaLunes", cLunes);
                                actualizacionMap.put("cadenaMartes", cMartes);
                                actualizacionMap.put("cadenaMiercoles", cMiercoles);
                                actualizacionMap.put("cadenaJueves", cJueves);
                                actualizacionMap.put("cadenaViernes", cViernes);

                                databaseReference.child(clavePrimaria).updateChildren(actualizacionMap);
                                Snackbar snackbar = Snackbar.make(v,"Horario actualizado",Snackbar.LENGTH_SHORT);
                                snackbar.show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        botonIrGuardias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActividadHorario.this, GestionGuardias.class);
                intent.putExtras(datosUsuarioRecogidos);
                startActivity(intent);
            }
        });

        botonIrReuniones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActividadHorario.this, ActividadReuniones.class);
                intent.putExtras(datosUsuarioRecogidos);
                startActivity(intent);
            }
        });




        botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent volverHorario = new Intent(ActividadHorario.this, ActividadMenuUsuario.class);
                volverHorario.putExtras(datosUsuarioRecogidos);
                startActivity(volverHorario);
            }
        });
    }
}