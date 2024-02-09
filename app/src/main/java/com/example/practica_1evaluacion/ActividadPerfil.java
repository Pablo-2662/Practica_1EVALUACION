package com.example.practica_1evaluacion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class ActividadPerfil extends AppCompatActivity {

    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_perfil);


        EditText datosNombre = (EditText) findViewById(R.id.datoNombre);
        EditText datosContrasena = (EditText) findViewById(R.id.datoContrasena);
        EditText datosTelefono = (EditText) findViewById(R.id.datoTelefono);
        EditText datosCorreo = (EditText) findViewById(R.id.datoCorreo);

        MaterialButton volverPerfil, editarPerfil;


        //Recogemos el Bundle
        Intent intent = getIntent();
        Bundle datosRecogidosPerfil = intent.getExtras();
        String correoRecogido = datosRecogidosPerfil.getString("correo");



        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("usuario");
        Query query = databaseReference.orderByChild("email").equalTo(correoRecogido);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Usuario usuario = dataSnapshot.getValue(Usuario.class);
                        String nombre = usuario.getNombre();
                        String pass = usuario.getContrasena();
                        String telefono = usuario.getTlf();
                        datosNombre.setText(nombre);
                        datosContrasena.setText(pass);
                        datosTelefono.setText(telefono);
                        datosCorreo.setText(correoRecogido);
                        //
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //Volvemos al menú de usuario
        volverPerfil = (MaterialButton) findViewById(R.id.botonVolverPantallaPerfil);
        volverPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent volverActividadPerfil = new Intent(ActividadPerfil.this, ActividadMenuUsuario.class);
                volverActividadPerfil.putExtras(datosRecogidosPerfil);
                startActivity(volverActividadPerfil);
            }
        });

        editarPerfil = (MaterialButton) findViewById(R.id.botonEditarPerfil);
        editarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datosNombre.setEnabled(true);
                datosContrasena.setEnabled(true);
                datosTelefono.setEnabled(true);

                SQLHelperUsuario helperUsuario = new SQLHelperUsuario(v.getContext(),"bbdd_ususarios",null,1);
                database = helperUsuario.getWritableDatabase();

                String correo = datosCorreo.getText().toString();
                String nombre = datosNombre.getText().toString();
                String contrasena = datosContrasena.getText().toString();
                String telefono = datosTelefono.getText().toString();

                // Consultar si ya existe un registro con el mismo correo
                Cursor cursor = database.rawQuery("SELECT correo FROM Usuario WHERE correo = ?", new String[]{correo});
                if (cursor.getCount() > 0) {

                } else {
                    // No hay ningún registro con el mismo correo, proceder con la inserción
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("correo", correo);
                    contentValues.put("nombre", nombre);
                    contentValues.put("contrasena", contrasena);
                    contentValues.put("telefono", telefono);
                    database.insert("Usuario", null, contentValues);
                }
                cursor.close();
            }
        });

        Button botonHecho = (Button) findViewById(R.id.botonConfirmarEdiccion);
        botonHecho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datosNombre.setEnabled(false);
                datosContrasena.setEnabled(false);
                datosTelefono.setEnabled(false);
                datosCorreo.setEnabled(false);

                String nombre, contrasena, telefono;
                nombre = datosNombre.getText().toString();
                contrasena = datosContrasena.getText().toString();
                telefono = datosTelefono.getText().toString();
                String correo = datosCorreo.getText().toString();


                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("usuario");
                Query query = databaseReference.orderByChild("email").equalTo(correoRecogido);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                                String clavePrimaria = dataSnapshot.getKey();
                                Map<String, Object> actualizacionMap = new HashMap<>();
                                actualizacionMap.put("nombre", nombre);
                                actualizacionMap.put("contrasena", contrasena);
                                actualizacionMap.put("telefono", telefono);

                                databaseReference.child(clavePrimaria).updateChildren(actualizacionMap);
                                Snackbar snackbar = Snackbar.make(v,"Perfil Actualizado",Snackbar.LENGTH_SHORT);
                                snackbar.show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                SQLHelperUsuario helperUsuario = new SQLHelperUsuario(v.getContext(),"bbdd_usuarios",null,1);
                database = helperUsuario.getWritableDatabase();

                ContentValues contentValues = new ContentValues();
                contentValues.put("correo", correo);
                contentValues.put("nombre", nombre);
                contentValues.put("contrasena", contrasena);
                contentValues.put("telefono", telefono);
                database.update("Usuario",contentValues,"correo=?",new String[]{correoRecogido});

            }
        });

    }
}