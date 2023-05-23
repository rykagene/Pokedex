package com.example.labex6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

public class registrationpage extends AppCompatActivity {
    Button Bregister;
    EditText ETusername, ETpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrationpage);

        Bregister = findViewById(R.id.Bregister);
        ETusername = findViewById(R.id.ETusername);
        ETpassword = findViewById(R.id.ETpass);


        Bregister.setOnClickListener(v -> {
            ETpassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            if (ETusername.getText().toString().equals("user") && ETpassword.getText().toString().equals("123")) {
                Intent intent = new Intent(getApplicationContext(), vidsearch.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
            }

        });
    }
}