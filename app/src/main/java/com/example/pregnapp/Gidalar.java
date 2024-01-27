package com.example.pregnapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Gidalar extends AppCompatActivity {
    private Toolbar toolbarGidalar;
    private RelativeLayout relativeTehlikeli, relativeTehlikesiz, relativeBeCareful;
    private Button buttonTehlikeli, buttonTehlikesiz, buttonCareful;
    private ImageButton button_geri_gidalar;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gidalar);
        toolbarGidalar=findViewById(R.id.toolbarGidalar);
        toolbarGidalar.setBackgroundColor(getResources().getColor(R.color.darkBlue));

        button_geri_gidalar=findViewById(R.id.button_geri_gidalar);
        button_geri_gidalar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Gidalar.this, MainActivity.class));
            }
        });



        relativeTehlikeli=findViewById(R.id.relativeTehlikeli);
        relativeTehlikesiz=findViewById(R.id.relativeTehlikelisiz);
        relativeBeCareful=findViewById(R.id.relativeBeCareful);

        buttonTehlikeli=findViewById(R.id.buttonTarih);
        buttonTehlikesiz=findViewById(R.id.buttonSaat);
        buttonCareful=findViewById(R.id.buttonCareful);


    }


    public void showContent(View view) {
        if (view.getId() == R.id.buttonTarih) {
            relativeTehlikeli.setVisibility(View.VISIBLE);
            relativeTehlikesiz.setVisibility(View.GONE);
            relativeBeCareful.setVisibility(View.GONE);

        } else if (view.getId() == R.id.buttonSaat) {
            relativeTehlikeli.setVisibility(View.GONE);
            relativeTehlikesiz.setVisibility(View.VISIBLE);
            relativeBeCareful.setVisibility(View.GONE);

        }else if (view.getId() == R.id.buttonCareful) {
            Toast.makeText(this, "alfa", Toast.LENGTH_SHORT).show();
            relativeTehlikeli.setVisibility(View.GONE);
            relativeTehlikesiz.setVisibility(View.GONE);
            relativeBeCareful.setVisibility(View.VISIBLE);
        }
    }
}