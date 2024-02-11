package com.example.practica_1evaluacion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorTareas extends ArrayAdapter<Tarea> {
    Context context;
    int id_layout_diseno;
    ArrayList<Tarea>tareas;

    public AdaptadorTareas(@NonNull Context context, int resource, @NonNull ArrayList<Tarea> objects) {
        super(context, resource, objects);
        this.context = context;
        this.id_layout_diseno = resource;
        this.tareas = objects;

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View vista = convertView;
        if (vista == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            vista = inflater.inflate(R.layout.designtareas, null);
        }

        // Obtener la reunión en la posición actual
        Tarea tarea = tareas.get(position);

        TextView textView_correoEmisor = (TextView) vista.findViewById(R.id.correoEmisor_tarea);
        TextView textView_tipo = (TextView) vista.findViewById(R.id.tipo_tarea);
        TextView textView_categoria = (TextView) vista.findViewById(R.id.categoria_tarea);
        TextView textView_fecha = (TextView) vista.findViewById(R.id.fechaTarea);
        TextView textView_descripcion = (TextView) vista.findViewById(R.id.descripcionTarea);

        textView_correoEmisor.setText(tarea.getCorreoEmisor());
        textView_tipo.setText(tarea.getTipo());
        textView_categoria.setText(tarea.getCategoria());
        textView_fecha.setText(tarea.getFechaLimite());
        textView_descripcion.setText(tarea.getDescripcion());




        return vista;
    }
}
