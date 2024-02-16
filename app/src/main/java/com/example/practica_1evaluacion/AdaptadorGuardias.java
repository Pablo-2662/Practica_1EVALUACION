package com.example.practica_1evaluacion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorGuardias extends ArrayAdapter<Guardia> {
    private Context contexto;
    private ArrayList<Guardia> guardias;
    private int layout_estilo;



    public AdaptadorGuardias(@NonNull Context context, int resource, @NonNull ArrayList<Guardia> objects) {
        super(context, resource, objects);
        this.contexto = context;
        this.guardias = objects;
        this.layout_estilo = resource;


    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View vista = convertView;
        if (vista == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            vista = inflater.inflate(R.layout.designguardias, null);
        }

        // Obtener la reunión en la posición actual
        Guardia  guardia= guardias.get(position);

        // Obtener las vistas del diseño
        TextView fechaGuardia = (TextView) vista.findViewById(R.id.fechaGuardia );
        TextView horaGuardia = (TextView) vista.findViewById(R.id.horaGuardia);
        TextView asuntoGuardia = (TextView) vista.findViewById(R.id.textoMotivoGuardia);



        // Asignar los valores de la reunión a las vistas
        fechaGuardia.setText(guardia.getFechaGuardia());
        horaGuardia.setText(guardia.getHoraGuardia());
        asuntoGuardia.setText(guardia.getMotivoGuardia());


        return vista;
    }
}
