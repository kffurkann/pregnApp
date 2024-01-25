package com.example.pregnapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class TekmeSayar extends AppCompatActivity {
//button_geri_tekme_sayar
    private ImageButton buttongeriGitmeTekmeSayar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tekme_sayar);

        buttongeriGitmeTekmeSayar=findViewById(R.id.button_geri_tekme_sayar);
        buttongeriGitmeTekmeSayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TekmeSayar.this, MainActivity.class));
            }
        });

    }
}