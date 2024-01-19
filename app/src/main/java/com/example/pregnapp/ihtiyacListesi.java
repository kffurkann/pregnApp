package com.example.pregnapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class ihtiyacListesi extends AppCompatActivity {
    private RelativeLayout relativeAnne, relativeBebek;
    private Button buttonAnne, buttonBebek, buttonDogum, buttonSusleme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ihtiyac_listesi);
    relativeAnne=findViewById(R.id.relativeAnne);
    relativeBebek=findViewById(R.id.relativeBebek);

    buttonAnne=findViewById(R.id.buttonAnne);
    buttonBebek=findViewById(R.id.buttonBebek);
    buttonDogum=findViewById(R.id.buttonDogum);
    buttonSusleme=findViewById(R.id.buttonSusleme);
    }


    public void showContent(View view) {
        // Tıklanan butona göre ilgili RelativeLayout'i görünür yap
        if (view.getId() == R.id.buttonAnne) {
                relativeAnne.setVisibility(View.VISIBLE);
                relativeBebek.setVisibility(View.GONE);

        } else if (view.getId() == R.id.buttonBebek) {
                relativeAnne.setVisibility(View.GONE);
                relativeBebek.setVisibility(View.VISIBLE);

        }/* else if (view.getId() == R.id.button2) {
                layoutButton1.setVisibility(View.GONE);
                layoutButton2.setVisibility(View.GONE);
                layoutButton3.setVisibility(View.VISIBLE);
        } else if (view.getId() == R.id.button2) {
                layoutButton1.setVisibility(View.GONE);
                layoutButton2.setVisibility(View.GONE);
                layoutButton3.setVisibility(View.VISIBLE);

        }*/
    }
}