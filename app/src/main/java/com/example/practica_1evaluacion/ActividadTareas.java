package com.example.practica_1evaluacion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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

public class ActividadTareas extends AppCompatActivity {
    private ListView listaTareas;
    private AdaptadorTareas adaptadorTareas;
    private ArrayList<Tarea> tareas;
    FirebaseDatabase database = FirebaseDatabase.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_tareas);

        Intent intentTareas = getIntent();
        Bundle datosUsuarioTareas = intentTareas.getExtras();
        String correoUsuario = datosUsuarioTareas.getString("correo");
        final Context context = this;

        Button botonVolver = (Button) findViewById(R.id.botonVolverTareas);
        Button actualizarTareas = (Button) findViewById(R.id.botonActualizarTareas);

        botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActividadTareas.this, ActividadMenuUsuario.class);
                intent.putExtras(datosUsuarioTareas);
                startActivity(intent);
            }
        });


        listaTareas = (ListView)findViewById(R.id.listaTareas);


        actualizarTareas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReferenceTareas = FirebaseDatabase.getInstance().getReference("tareas");
                Query query = databaseReferenceTareas.orderByChild("correoReceptor").equalTo(correoUsuario);

                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        tareas = new ArrayList<>();
                        for (DataSnapshot snap : snapshot.getChildren()) {
                            Tarea tarea = snap.getValue(Tarea.class);
                            tareas.add(tarea);
                        }
                       adaptadorTareas = new AdaptadorTareas(context, R.layout.designtareas,tareas);

                        listaTareas.setAdapter(adaptadorTareas);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });



    }
}