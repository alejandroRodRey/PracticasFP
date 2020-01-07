package com.liceolapaz.des.ej2arr;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BaseDatos extends SQLiteOpenHelper {

    public BaseDatos(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS usuarios(Codigo INTEGER, nombre TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS productos(" +
                "    codigo INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "    nombre TEXT NOT NULL," +
                "    descripcion TEXT NOT NULL," +
                "    precio REAL NOT NULL," +
                "    moneda TEXT NOT NULL)");
        db.execSQL("INSERT INTO usuarios VALUES ('1', 'admin');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
