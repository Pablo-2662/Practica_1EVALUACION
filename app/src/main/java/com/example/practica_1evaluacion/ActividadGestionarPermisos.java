package com.example.practica_1evaluacion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

public class ActividadGestionarPermisos extends AppCompatActivity {
    private ListView listaPermisos;
    private AdaptadorPermisos adaptadorPermisos;
    private ArrayList<Permiso> permisos;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_gestionar_permisos);
        listaPermisos = (ListView) findViewById(R.id.listaPermisos);

        Intent intentPermisos = getIntent();
        Bundle datosUsuarioPermisos = intentPermisos.getExtras();
        String correoUsuario = datosUsuarioPermisos.getString("correo");
        final Context context = this;

        Button botonVolverPermisos = (Button) findViewById(R.id.botonVolverPantallaPermisos);
        Button botonActualizarPermisos = (Button) findViewById(R.id.botonActualizarPermisos);


        botonVolverPermisos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActividadGestionarPermisos.this, ActividadMenuUsuario.class);
                intent.putExtras(datosUsuarioPermisos);
                startActivity(intent);
            }
        });

        botonActualizarPermisos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReferencePermisos = FirebaseDatabase.getInstance().getReference("permisos");
                Query query = databaseReferencePermisos.orderByChild("correoJefe").equalTo(correoUsuario);

                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        permisos = new ArrayList<>();
                        for (DataSnapshot snap : snapshot.getChildren()) {
                            Permiso permiso = snap.getValue(Permiso.class);
                            permisos.add(permiso);
                        }
                        adaptadorPermisos = new AdaptadorPermisos(context, R.layout.design_permisos,permisos);

                        listaPermisos.setAdapter(adaptadorPermisos);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });




    }
}