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

 public class GestionGuardias extends AppCompatActivity {

     private ListView listaGuardias;
     private AdaptadorGuardias adaptadorGuardias;
     private ArrayList<Guardia> guardias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_guardias);
        final Context context = this;


        Button botonVolverGuardias = (Button) findViewById(R.id.botonVolverPantallaGuardias);
        Bundle datosGuardias = getIntent().getExtras();
        String correoUsuario = datosGuardias.getString("correo");
        Button actualizar = (Button) findViewById(R.id.botonActualizarGuardias);

        listaGuardias = (ListView) findViewById(R.id.listaGuardias);


        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReferenceReuniones = FirebaseDatabase.getInstance().getReference("guardias");
                Query query = databaseReferenceReuniones.orderByChild("correoGuardia").equalTo(correoUsuario);
                System.out.println("correo del usuario: "+correoUsuario);

                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        guardias = new ArrayList<>();
                        for (DataSnapshot snap : snapshot.getChildren()) {
                            Guardia guardia = snap.getValue(Guardia.class);
                            guardias.add(guardia);
                        }
                        adaptadorGuardias = new AdaptadorGuardias(context, R.layout.designguardias,guardias);

                        listaGuardias.setAdapter(adaptadorGuardias);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


                botonVolverGuardias.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(GestionGuardias.this, ActividadMenuUsuario.class);
                        intent.putExtras(datosGuardias);
                        startActivity(intent);
                    }
                });
            }
        });
    }
}