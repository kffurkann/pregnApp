package com.example.pregnapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class gebelikAraclari extends AppCompatActivity {

    private ImageButton kiloTakibi;
    private ImageButton tekmeSayar;
    private ImageButton saglikTestleri;
    private ImageButton gidalar;
    private ImageButton ihtiyacListesi;
    private ImageButton hamilelikGunlugum;
    private ImageButton hamilelikAjandam;
    private ImageButton bebekIsimleri;
    private ImageButton hamilelikCizelgesi;
    private ImageButton bugunNeAsersem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gebelik_araclari);



        kiloTakibi = findViewById(R.id.kilotakibiLogo);
        kiloTakibi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(gebelikAraclari.this, KiloTakibi.class));
            }
        });

        tekmeSayar = findViewById(R.id.tekmesayarLogo);
        tekmeSayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(gebelikAraclari.this, TekmeSayar.class));
            }
        });

        saglikTestleri = findViewById(R.id.sagliktestleriLogo);
        saglikTestleri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(gebelikAraclari.this, TestlerVeTaramalar.class));
            }
        });

        gidalar = findViewById(R.id.gidalarLogo);
        gidalar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(gebelikAraclari.this, KiloTakibi.class));
            }
        });

        ihtiyacListesi = findViewById(R.id.ihtiyaclistesiLogo);
        ihtiyacListesi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(gebelikAraclari.this, ihtiyacListesi.class));
            }
        });

        hamilelikGunlugum = findViewById(R.id.hamilelikgunluguLogo);
        hamilelikGunlugum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(gebelikAraclari.this, HamilelikAjandam.class));
            }
        });

        hamilelikAjandam = findViewById(R.id.hamilelikajandaLogo);
        hamilelikAjandam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(gebelikAraclari.this, HamilelikAjandam.class));
            }
        });

        bebekIsimleri = findViewById(R.id.bebekisimleriLogo);
        bebekIsimleri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(gebelikAraclari.this, BebekIsimleri.class));
            }
        });

        hamilelikCizelgesi = findViewById(R.id.hamilelikcizelgesiLogo);
        hamilelikCizelgesi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(gebelikAraclari.this, HamilelikAjandam.class));
            }
        });

        bugunNeAsersem = findViewById(R.id.bugunneasersemLogo);
        bugunNeAsersem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(gebelikAraclari.this, BugunNeAsersem.class));
            }
        });



    }


}