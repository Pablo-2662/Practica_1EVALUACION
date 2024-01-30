package com.example.practica_1evaluacion;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class AdaptadorReuniones extends ArrayAdapter<Reunion> {
    public AdaptadorReuniones(@NonNull Context context, int design_reuniones, @NonNull ArrayList<Reunion>reuniones) {
        super(context, design_reuniones, reuniones);
    }
}
