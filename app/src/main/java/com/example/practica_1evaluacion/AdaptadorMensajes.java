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

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AdaptadorMensajes extends ArrayAdapter<Mensaje> {
    Context contexto;
     ArrayList<Mensaje>mensajes;
     private int layout_estilo;

    public AdaptadorMensajes(@NonNull Context context, int resource, @NonNull ArrayList<Mensaje> lista) {
        super(context, resource, lista);
        this.contexto = context;
        this.mensajes = lista;
        this.layout_estilo = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View vista = convertView;
        if (vista == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            vista = inflater.inflate(R.layout.designmensaje, null);
        }

        // Obtener la reunión en la posición actual
        Mensaje mensaje = mensajes.get(position);

        // Obtener las vistas del diseño
        TextView correoEmisor = (TextView) vista.findViewById(R.id.textview_correoemisor);
        TextView fechaMensaje = (TextView) vista.findViewById(R.id.textoFecha_mensaje);
        TextView horaMensaje = (TextView) vista.findViewById(R.id.textoHoramensaje);
        TextView contenidoMensaje = (TextView) vista.findViewById(R.id.contenidoMensaje);


        // Asignar los valores de la reunión a las vistas
        correoEmisor.setText(mensaje.getCorreoOrigen());
        fechaMensaje.setText(mensaje.getFecha());
        horaMensaje.setText(mensaje.getHora());
        contenidoMensaje.setText(mensaje.getMensaje());


        return vista;
    }
}
