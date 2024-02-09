package com.example.practica_1evaluacion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLHelperUsuario extends SQLiteOpenHelper {

    String sqlCreacion ="CREATE TABLE IF NOT EXISTS Usuario (correo TEXT PRIAMRY KEY, nombre TEXT, contrasena TEXT, telefono TEXT)";
    String sqlBorrado ="DROP TABLE Usuario";

    public SQLHelperUsuario(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreacion);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(sqlBorrado);
        db.execSQL(sqlCreacion);

    }
}
