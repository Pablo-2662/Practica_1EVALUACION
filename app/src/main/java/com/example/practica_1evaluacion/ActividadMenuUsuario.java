package com.example.practica_1evaluacion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.telephony.TelephonyCallback;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ActividadMenuUsuario extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_menu_usuario);


        //RECOGEMOS EL BUNDLE
        Intent intent = getIntent();
        Bundle datosUsuarioRecogidos = intent.getExtras();
        String correoRecogido = datosUsuarioRecogidos.getString("correo");


        //DEFINICIÓN DE ELEMENTOS
        TextView nombreBienvenida = (TextView) findViewById(R.id.nombreSaludo);
        ImageButton botonPerfil = (ImageButton) findViewById(R.id.botonPerfil);


        System.out.println("Correo: "+correoRecogido);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("usuario");
        Query query = databaseReference.orderByChild("correo").equalTo(correoRecogido);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int count = (int) snapshot.getChildrenCount();
                System.out.println("Numero registros: "+count);
                Log.i("entra a la funcion","entra");
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Log.i("entra a la funcion","entra2");
                        Usuario usuario = dataSnapshot.getValue(Usuario.class);
                        String nombre = usuario.getNombre();
                        nombreBienvenida.setText(nombre);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}





/*

        //Boton ir al perfil
        botonPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pasarActividadPerfil = new Intent(ActividadMenuUsuario.this, ActividadPerfil.class);
                pasarActividadPerfil.putExtras(datosUsuarioRecogidos);
                startActivity(pasarActividadPerfil);
            }
        });
        //Botón ir al horario
        Button botonHorario = (Button) findViewById(R.id.botonActividadHorario);
        botonHorario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actividadHorario = new Intent(ActividadMenuUsuario.this, ActividadHorario.class);
                actividadHorario.putExtras(datosUsuarioRecogidos);
                startActivity(actividadHorario);
            }
        });

        //Botón ir a reuniones
        Button botonActividadReuniones =(Button) findViewById(R.id.botonActividadReuniones);
        botonActividadReuniones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actividadReuniones = new Intent(ActividadMenuUsuario.this, ActividadReuniones.class);
                actividadReuniones.putExtras(datosUsuarioRecogidos);
                startActivity(actividadReuniones);
            }
        });


        Button botonAusencias = (Button) findViewById(R.id.botonActividadAusencias);
        botonAusencias.setOnClickListener(new View.OnClickListener() {
            final String  puestoRecogido = datosUsuarioRecogidos.getString("Trabajo");
            @Override
            public void onClick(View v) {
                if(puestoRecogido.equals("Jefe de estudios")){
                    Intent actividadAusencias1 = new Intent(ActividadMenuUsuario.this, ActividadAusenciasJefe.class);
                    actividadAusencias1.putExtras(datosUsuarioRecogidos);
                    startActivity(actividadAusencias1);
                }else if(puestoRecogido.equals("Coordinador") || (puestoRecogido.equals("Docente"))){
                    Intent actividadAusencias2 = new Intent(ActividadMenuUsuario.this, ActividadAusenciasNotificar.class);
                    actividadAusencias2.putExtras(datosUsuarioRecogidos);
                    startActivity(actividadAusencias2);
                }
            }
        });


        Button botonTareas = (Button) findViewById(R.id.botonActividadTareas);
        botonTareas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent irATareas = new Intent(ActividadMenuUsuario.this, ActividadTareas.class);
                irATareas.putExtras(datosUsuarioRecogidos);
                startActivity(irATareas);
            }
        });

        Button botonPermisos = (Button) findViewById(R.id.botonActividadPermisos);
        botonPermisos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String  puestoRecogido = datosUsuarioRecogidos.getString("Trabajo");
                if(puestoRecogido.equals("Jefe de estudios")){
                    Intent actividadPermisos = new Intent(ActividadMenuUsuario.this, ActividadPermisos.class);
                    actividadPermisos.putExtras(datosUsuarioRecogidos);
                    startActivity(actividadPermisos);

                }else if (puestoRecogido.equals("Coordinador")||puestoRecogido.equals("Docente")){
                    Intent actividadCrearPermisos = new Intent(ActividadMenuUsuario.this, ActividadCrearPermisos.class);
                    actividadCrearPermisos.putExtras(datosUsuarioRecogidos);
                    startActivity(actividadCrearPermisos);
                }
            }
        });


        Button botonMensajes = (Button) findViewById(R.id.botonActividadMensajes);
        botonMensajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actividadMensajes = new Intent(ActividadMenuUsuario.this, ActividadMensajes.class);
                actividadMensajes.putExtras(datosUsuarioRecogidos);
                startActivity(actividadMensajes);
            }
        });



    }
}

*/
