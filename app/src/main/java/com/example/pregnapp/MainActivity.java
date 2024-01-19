package com.example.pregnapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    private Button buttonGecis;
    private Button buttonMakale;
    private Button buttonTekme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonGecis=findViewById(R.id.buttonGecis);
        buttonGecis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ihtiyacListesi.class));
            }
        });
        buttonMakale=findViewById(R.id.button_makale);
        buttonMakale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Makale.class));
            }
        });
        buttonTekme=findViewById(R.id.button_tekme);
        buttonTekme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,TekmeSayar.class));
            }
        });
    }
}