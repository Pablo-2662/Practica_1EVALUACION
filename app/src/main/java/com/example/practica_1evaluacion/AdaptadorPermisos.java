package com.example.practica_1evaluacion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorPermisos extends ArrayAdapter<Permiso> {

    Context contexto;
    int r_layout_disenyo;
    ArrayList<Permiso>permisos;
    public AdaptadorPermisos(@NonNull Context context, int resource, @NonNull ArrayList<Permiso> objects) {
        super(context, resource, objects);
        this.contexto = context;
        this.r_layout_disenyo = resource;
        this.permisos = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final View vista;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            vista = inflater.inflate(R.layout.design_permisos, parent, false);
        } else {
            vista = convertView;
        }

        // Obtener la reunión en la posición actual
        Permiso p = permisos.get(position);

         TextView textView_correoUsuario = (TextView) vista.findViewById(R.id.correoUsuario);
         TextView textView_tipo = (TextView) vista.findViewById(R.id.textoTipo);
         TextView textView_fechaInicio = (TextView) vista.findViewById(R.id.textoFechaInicial);
         TextView textView_fechaFinal = (TextView) vista.findViewById(R.id.textoFechaFin);
         CheckBox checkBox_estado = (CheckBox) vista.findViewById(R.id.estadoPermiso);


         textView_correoUsuario.setText(p.getCorreoUsuario());
         textView_tipo.setText(p.getTipo());
         textView_fechaInicio.setText(p.getFechaMin());
         textView_fechaFinal.setText(p.getFechaMax());
         checkBox_estado.setChecked(false);


         checkBox_estado.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (checkBox_estado.isChecked()){
                     p.setAceptado(true);
                     Snackbar snackbar = Snackbar.make(vista,"Has aceptado el permiso del usuario "+p.getCorreoUsuario(),Snackbar.LENGTH_SHORT);
                     snackbar.show();
                 }else{
                     p.setAceptado(false);
                     Snackbar snackbar = Snackbar.make(vista,"Has rechazado el permiso del usuario "+p.getCorreoUsuario(),Snackbar.LENGTH_SHORT);
                     snackbar.show();
                 }
             }
         });

        return vista;
    }
}
