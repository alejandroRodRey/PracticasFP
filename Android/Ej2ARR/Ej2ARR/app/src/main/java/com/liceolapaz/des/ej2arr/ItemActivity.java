package com.liceolapaz.des.ej2arr;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ItemActivity extends AppCompatActivity {

    int itemID;
    EditText nombre;
    EditText descripcion;
    EditText precio;
    Spinner moneda;

    SQLiteDatabase database;
    BaseDatos db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        itemID = getIntent().getExtras().getInt("id");

        nombre = findViewById(R.id.nombre);
        descripcion = findViewById(R.id.descripcion);
        precio = findViewById(R.id.precio);
        moneda = findViewById(R.id.moneda);

        String[] monedas = {"Euro (€)", "Dólar USA ($)", "Yen (¥)", "Libra esterlina (£)"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.monedas_layout, monedas);
        arrayAdapter.setDropDownViewResource(R.layout.monedas_down_layout);
        moneda.setAdapter(arrayAdapter);

        cargarDatos();
    }

    public void updateItem(View view) {
        if (nombre.getText().toString().isEmpty() || descripcion.getText().toString().isEmpty() || precio.getText().toString().isEmpty()) {
            Toast toast = Toast.makeText(this, "LLENE TODOS LOS CAMPOS", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        try {
            Double.parseDouble(precio.getText().toString());
        } catch (Exception excepcion) {
            Toast toast = Toast.makeText(this, "PRECIO SOLO ADMITE NÚMEROS", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        actualizarDatos();
                        exit();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        exit();
                        break;
                    case DialogInterface.BUTTON_NEUTRAL:
                        break;
                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AppTheme_AlertDialogTheme);
        builder.setMessage("Los datos se guardarán en la base de datos. ¿Está seguro?");
        builder.setTitle("Aceptar");
        builder.setPositiveButton("Sí", dialogClickListener);
        builder.setNeutralButton("Cancelar", dialogClickListener);
        builder.setNegativeButton("No", dialogClickListener).show();
    }

    public void exitItem(View view) {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        exit();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AppTheme_AlertDialogTheme);
        builder.setMessage("Los datos no se guardarán. ¿Está seguro de cancelar?");
        builder.setTitle("Cancelar");
        builder.setPositiveButton("Sí", dialogClickListener);
        builder.setNegativeButton("No", dialogClickListener).show();
    }

    public void removeItem(View view) {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        eliminarDatos();
                        exit();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        exit();
                        break;
                    case DialogInterface.BUTTON_NEUTRAL:
                        break;
                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AppTheme_AlertDialogTheme);
        builder.setMessage("Los datos se borrarán de la base de datos. ¿Está seguro?");
        builder.setTitle("Eliminar");
        builder.setPositiveButton("Sí", dialogClickListener);
        builder.setNeutralButton("Cancelar", dialogClickListener);
        builder.setNegativeButton("No", dialogClickListener).show();
    }

    public void exit() {
        Intent intent = new Intent(this, ListActivity.class);
        this.startActivity(intent);
    }

    private void cargarDatos() {
        db = new BaseDatos(this, "Ej2ARR", null, 1);
        database = db.getWritableDatabase();

        Cursor getDatos = database.rawQuery("SELECT * FROM productos WHERE codigo=" + itemID, null);
        getDatos.moveToFirst();
        setTitle(getDatos.getString(1));
        nombre.setText(getDatos.getString(1));
        descripcion.setText(getDatos.getString(2));
        precio.setText("" + getDatos.getDouble(3));
        String monedaValue = getDatos.getString(4);
        switch (monedaValue) {
            case "0":
                moneda.setSelection(0);
                break;
            case "1":
                moneda.setSelection(1);
                break;
            case "2":
                moneda.setSelection(2);
                break;
            case "3":
                moneda.setSelection(3);
                break;
            default:
                break;
        }

        database.close();
        db.close();
    }

    private void actualizarDatos() {
        String monedaValue = moneda.getSelectedItem().toString();
        switch (monedaValue) {
            case "Euro (€)":
                monedaValue = "0";
                break;
            case "Dólar USA ($)":
                monedaValue = "1";
                break;
            case "Yen (¥)":
                monedaValue = "2";
                break;
            case "Libra esterlina (£)":
                monedaValue = "3";
                break;
            default:
                break;
        }
        db = new BaseDatos(this, "Ej2ARR", null, 1);
        database = db.getWritableDatabase();
        try {
            database.execSQL("UPDATE productos SET nombre='" + nombre.getText() + "', descripcion='" + descripcion.getText() + "', precio=" + precio.getText() + ", moneda=" + monedaValue + " WHERE codigo=" + itemID);
            database.close();
            db.close();
            Toast toast = Toast.makeText(this, "DATOS ACTUALIZADOS", Toast.LENGTH_SHORT);
            toast.show();
        } catch (Exception e) {
            database.close();
            db.close();
            Toast toast = Toast.makeText(this, "ERROR DE ACTUALIZACIÓN", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    private void eliminarDatos() {
        db = new BaseDatos(this, "Ej2ARR", null, 1);
        database = db.getWritableDatabase();
        database.execSQL("DELETE FROM productos WHERE codigo= " + itemID);
        database.close();
        db.close();
        Toast toast = Toast.makeText(this, "DATOS ELIMINADOS CORRECTAMENTE", Toast.LENGTH_SHORT);
        toast.show();
    }

}
