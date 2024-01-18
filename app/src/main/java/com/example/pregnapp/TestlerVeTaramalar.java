package com.example.pregnapp;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class TestlerVeTaramalar extends AppCompatActivity {

    private ImageButton buttonGeriTestlerVeTaramalar;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testler_ve_taramalar);
        toolbar = findViewById(R.id.my_toolbar_testlervetaramalar);
        setSupportActionBar(toolbar);

        buttonGeriTestlerVeTaramalar = findViewById(R.id.button_geri_testlervetaramalar);
    }
}