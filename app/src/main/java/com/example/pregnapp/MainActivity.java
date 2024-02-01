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
    private Button buttonLogin;
    private Button buttonBebek;
    private Button buttonGebelik;
    private Button buttonAsermek;
    private Button buttonSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        buttonSignUp=findViewById(R.id.button_signUp);
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        buttonAsermek=findViewById(R.id.button_bugunneAsersem);
        buttonAsermek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BugunNeAsersem.class));
            }
        });

        buttonGebelik=findViewById(R.id.button_gebelikAraclari);
        buttonGebelik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DashBoard.class));
            }
        });


        buttonGecis=findViewById(R.id.buttonGecis);
        buttonGecis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Gidalar.class));
            }
        });

        buttonBebek=findViewById(R.id.button_bebekİsimleri);
        buttonBebek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BebekIsimleri.class));
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

        buttonLogin=findViewById(R.id.button_login);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,TestlerVeTaramalar.class));
            }
        });
    }
}