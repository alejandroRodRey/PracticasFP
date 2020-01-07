package com.liceolapaz.des.ej2arr;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText usernameEditText;
    EditText passwordEditText;
    TextView errorLabel;
    Button loginButton;

    SQLiteDatabase database;
    BaseDatos db;

    private static final String pass = "liceo";
    private static int i = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        errorLabel = findViewById(R.id.errordLabel);
        loginButton = findViewById(R.id.login);

        db = new BaseDatos(this, "Ej2ARR", null, 1);
        database = db.getWritableDatabase();

    }

    public void checkLogin(View view) {
        Cursor getUsers = database.rawQuery("SELECT * FROM usuarios", null);
        getUsers.moveToFirst();
        String userDB = getUsers.getString(1);
        if (String.valueOf(usernameEditText.getText()).equals(userDB) && String.valueOf(passwordEditText.getText()).equals(pass)) {
            database.close();
            db.close();
            Intent intent = new Intent(this, ListActivity.class);
            startActivity(intent);
            i = 2;
        } else if (i > 0) {
            errorLabel.setText("Usuario y/o contraseña incorrectos.\r\nIntentos restantes = " + i);
            passwordEditText.setText("");
            i--;
        } else {
            database.close();
            db.close();
            System.exit(0);
        }

    }
}
