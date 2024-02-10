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

public class AdaptadorAusencias extends ArrayAdapter<Ausencia> {

    private ArrayList<Ausencia> listado;
    private Context contexto;
    private int layoutPersonalizado;
    public AdaptadorAusencias(@NonNull Context context, int resource, @NonNull ArrayList<Ausencia> ausencias) {
        super(context, resource, ausencias);
        this.listado = ausencias;
        this.contexto = context;
        this.layoutPersonalizado = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View vista = convertView;
        if (vista == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            vista = inflater.inflate(R.layout.diseno_ausencias, null);
        }

        // Obtener la reunión en la posición actual
        Ausencia ausencia = listado.get(position);

        // Obtener las vistas del diseño
        TextView textViewMotivo = vista.findViewById(R.id.texto_motivoAusencia);
        TextView textViewHora = vista.findViewById(R.id.horaAusencias);
        TextView textViewFecha = vista.findViewById(R.id.fechaAusencias);
        TextView textViewReceptor = vista.findViewById(R.id.textoReceptor_ausencia);
        TextView textViewEmisor = vista.findViewById(R.id.textoEmisor_ausencia);


        // Asignar los valores de la reunión a las vistas
        textViewMotivo.setText(ausencia.getMotivo());
        textViewFecha.setText(ausencia.getFecha());
        textViewHora.setText(ausencia.getHora());
        textViewEmisor.setText(ausencia.getCorreoUsuario());
        textViewReceptor.setText(ausencia.getCorreoJefe());


        return vista;
    }
}
