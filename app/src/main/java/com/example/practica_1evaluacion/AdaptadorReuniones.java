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

public class AdaptadorReuniones extends ArrayAdapter<Reunion> {

    private ArrayList<Reunion> lista;

    public AdaptadorReuniones(@NonNull Context context, int design_reuniones, @NonNull ArrayList<Reunion> reuniones) {
        super(context, design_reuniones, reuniones);
        this.lista = reuniones;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View vista = convertView;
        if (vista == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            vista = inflater.inflate(R.layout.estilo_listview_reuniones, null);
        }

        // Obtener la reunión en la posición actual
        Reunion reunion = getItem(position);

        // Obtener las vistas del diseño
        TextView textViewAsunto = vista.findViewById(R.id.textoAsunto_reunion);
        TextView textViewFecha = vista.findViewById(R.id.textoFecha_reunion);
        TextView textViewHora = vista.findViewById(R.id.textoHora_reunion);
        CheckBox checkBoxAsistencia = vista.findViewById(R.id.checkbox_asistenciaReunion);

        // Asignar los valores de la reunión a las vistas
        textViewAsunto.setText(reunion.getAsunto());
        textViewFecha.setText(reunion.getFecha());
        textViewHora.setText(reunion.getHora());
        checkBoxAsistencia.setChecked(reunion.isAsistencia());

        return vista;
    }
}
