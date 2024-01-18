package com.example.pregnapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class BugunNeAsersem extends AppCompatActivity {

    private Button buttonAser;
    private ImageButton buttonGeri;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bugun_ne_asersem);
        toolbar = findViewById(R.id.my_toolbar_aserme);
        setSupportActionBar(toolbar);

        buttonAser = findViewById(R.id.button_aser);
        buttonGeri = findViewById(R.id.button_geri_aserme);



    }
}