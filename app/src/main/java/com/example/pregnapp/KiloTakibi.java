package com.example.pregnapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class KiloTakibi extends AppCompatActivity {
//button_geri_kilo_takibi
    private ImageButton buttongeriGitmeKiloTakibi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kilo_takibi);

        buttongeriGitmeKiloTakibi=findViewById(R.id.button_geri_kilo_takibi);
        buttongeriGitmeKiloTakibi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KiloTakibi.this, MainActivity.class));
            }
        });


    }
}