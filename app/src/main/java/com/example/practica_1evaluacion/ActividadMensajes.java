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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ActividadMensajes extends AppCompatActivity {
    private ListView listaMensajes;
    private AdaptadorMensajes adaptadorMensajes;
    private ArrayList<Mensaje> mensajes;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("mensajes");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_mensajes);
        final Context context = this;

        MaterialButton botonEnviarMensaje = findViewById(R.id.botonEnviarMensaje);
        MaterialButton botonVolverMensaje = findViewById(R.id.botonVolverMensaje);
        MaterialButton botonActualizarMensaje = findViewById(R.id.actualizarMensajes);

        EditText textoPara = (EditText) findViewById(R.id.textoPara);
        EditText textoMensaje = (EditText) findViewById(R.id.textview_mensaje);
        textoMensaje.setEnabled(true);
        textoPara.setEnabled(true);

        Intent intent = getIntent();
        Bundle datosUsuarioRecogidos = intent.getExtras();
        String correoRecogido = datosUsuarioRecogidos.getString("correo"); //Correo del que va a enviar el mensaje

        listaMensajes = findViewById(R.id.listaMensajes);



        botonEnviarMensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String correoReceptor = textoPara.getText().toString();
                String correoEmisor = correoRecogido;
                String contenido =  textoMensaje.getText().toString();
                String fecha = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
                String hora = new SimpleDateFormat("HH:mm:ss").format(new Date());
                String idMensaje = myRef.push().getKey();


                Mensaje m = new Mensaje(idMensaje,correoRecogido,correoReceptor,fecha,hora,contenido);
                myRef.child(idMensaje).setValue(m);

                textoMensaje.setText("");
                textoPara.setText("");

                Snackbar.make(v, "Mensaje enviado con éxito a "+correoReceptor, Snackbar.LENGTH_INDEFINITE)
                        .setAction("Aceptar", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(ActividadMensajes.this, ActividadMenuUsuario.class);
                                intent.putExtras(datosUsuarioRecogidos);
                                startActivity(intent);
                            }
                        })
                        .show();
            }
        });

        botonVolverMensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVolver = new Intent(ActividadMensajes.this, ActividadMenuUsuario.class);
                intentVolver.putExtras(datosUsuarioRecogidos);
                startActivity(intentVolver);
            }
        });

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
