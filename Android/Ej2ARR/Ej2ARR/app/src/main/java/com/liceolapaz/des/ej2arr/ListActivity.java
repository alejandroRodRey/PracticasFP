package com.liceolapaz.des.ej2arr;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    SQLiteDatabase database;
    BaseDatos db;
    TextView lblCount;

    Item[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Lista de Productos");
        setSupportActionBar(toolbar);
        lblCount = (TextView) findViewById(R.id.LblCount);

        cargarDatos();

        AdapterList adaptador = new AdapterList(this, data);
        ListView list = (ListView) findViewById(R.id.List);
        list.setAdapter(adaptador);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {

                int selectedID = ((Item) a.getItemAtPosition(position)).getId();

                // String selected = ((TextView)v.findViewById(R.id.LblTitulo)).getText().toString();

                Intent intent = new Intent(ListActivity.this, ItemActivity.class);
                intent.putExtra("id", selectedID);
                startActivity(intent);
            }
        });
    }

    private void cargarDatos() {
        db = new BaseDatos(this, "Ej2ARR", null, 1);
        database = db.getWritableDatabase();

        Cursor getDatos = database.rawQuery("SELECT codigo, nombre, precio, moneda FROM productos", null);
        getDatos.moveToFirst();

        ArrayList<Item> itemsDB = new ArrayList<Item>();
        if (getDatos.moveToFirst()) {
            do {
                int codigo = getDatos.getInt(0);
                String nombre = getDatos.getString(1);
                double precio = getDatos.getDouble(2);
                String moneda = getDatos.getString(3);
                itemsDB.add(new Item(codigo, nombre, precio, moneda));
            } while (getDatos.moveToNext());
        }
        data = new Item[itemsDB.size()];
        int i = 0;
        while (i < itemsDB.size()) {
            data[i] = itemsDB.get(i);
            i++;
        }
        lblCount.setText("Número de productos:     " + itemsDB.size() + "   ");

        database.close();
        db.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuNuevo:
                Intent intent = new Intent(this, ItemNewActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

class Item {
    private int id;
    private String name;
    private double price;
    private String moneda;

    public Item(int id, String name, double price, String moneda) {
        this.id = id;
        this.name = name;
        this.price = price;
        switch (moneda) {
            case "0":
                moneda = "€";
                break;
            case "1":
                moneda = "$";
                break;
            case "2":
                moneda = "¥";
                break;
            case "3":
                moneda = "£";
                break;
            default:
                moneda = "";
                break;
        }
        this.moneda = moneda;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getMoneda() {
        return moneda;
    }
}

class AdapterList extends ArrayAdapter<Item> {
    Item[] data;

    public AdapterList(Context context, Item[] data) {
        super(context, R.layout.item_layout, data);
        this.data = data;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.item_layout, null);

        TextView lblName = (TextView) item.findViewById(R.id.LblName);
        lblName.setText(data[position].getName());

        TextView lblPrice = (TextView) item.findViewById(R.id.LblPrice);
        lblPrice.setText(data[position].getPrice() + " " + data[position].getMoneda());

        return (item);
    }
}