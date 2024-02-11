package com.example.practica_1evaluacion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ActividadMensajes extends AppCompatActivity {
    private ListView listaMensajes;
    private AdaptadorMensajes adaptadorMensajes;
    private ArrayList<Mensaje> mensajes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_mensajes);
        final Context context = this;

        MaterialButton botonEnviarMensaje = findViewById(R.id.botonEnviarMensaje);
        MaterialButton botonVolverMensaje = findViewById(R.id.botonVolverMensaje);
        MaterialButton botonActualizarMensaje = findViewById(R.id.actualizarMensajes);

        Intent intent = getIntent();
        Bundle datosUsuarioRecogidos = intent.getExtras();
        String correoRecogido = datosUsuarioRecogidos.getString("correo"); //Correo del que va a enviar el mensaje

        listaMensajes = findViewById(R.id.listaMensajes);

        botonActualizarMensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReferenceReuniones = FirebaseDatabase.getInstance().getReference("mensajes");
                Query query = databaseReferenceReuniones.orderByChild("correoDestino").equalTo(correoRecogido);

                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        mensajes = new ArrayList<>();
                        for (DataSnapshot snap : snapshot.getChildren()) {
                            Mensaje mensaje = snap.getValue(Mensaje.class);
                            mensajes.add(mensaje);
                        }
                        adaptadorMensajes = new AdaptadorMensajes(context, R.layout.designmensaje,mensajes);

                        listaMensajes.setAdapter(adaptadorMensajes);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}
