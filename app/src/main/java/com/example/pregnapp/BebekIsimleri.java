package com.example.pregnapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pregnapp.R;

import java.util.Random;

public class BebekIsimleri extends AppCompatActivity {

    private Button buttonKiz;
    private Button buttonErkek;
    private ImageView carkImageView;
    private TextView textViewRandomIsim;
    private ImageButton geriGitmeBebek;

    private String[] kizIsimleri = {"Bahar", "Elif", "Belkıs", "Canan", "Ceren", "Çağla", "Çise", "Damla", "Demet", "Deren",
            "Alin", "Alisa", "Amine", "Bade", "Didem", "Ece", "Eda", "Emel", "Esra", "Feyza",
            "Figen", "Fulya", "Gonca", "Göknur", "Gözde", "Hazal", "Ilgın", "Işıl", "Melisa", "Müge"};
    private String[] erkekIsimleri = {"Aras", "Ataman", "Aybars", "Bayhan", "Barış", "Bahadır", "Gökay", "Gökhan", "Kutadgu", "Yazgan",
            "Toygar", "Efe", "Emir", "Can", "Arda", "Kerem", "Berk", "Yiğit", "Mert", "Oğuz",
            "Uraz", "Kutay", "Ediz", "Eymen", "Dumrul", "Boğaç", "Bora", "Cengiz", "Mehmet", "Selim"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bebek_isimleri);

        buttonKiz = findViewById(R.id.button_kiz);
        buttonErkek = findViewById(R.id.button_erkek);
        carkImageView = findViewById(R.id.carkImageView);
        textViewRandomIsim = findViewById(R.id.textViewRandomIsim);
        geriGitmeBebek = findViewById(R.id.button_geri_bebek_isimleri);

        geriGitmeBebek=findViewById(R.id.button_geri_bebek_isimleri);
        geriGitmeBebek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BebekIsimleri.this, MainActivity.class));
            }
        });

        buttonKiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimationAndShowRandomName(kizIsimleri);
            }
        });

        buttonErkek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimationAndShowRandomName(erkekIsimleri);
            }
        });
    }

    private void startAnimationAndShowRandomName(final String[] isimler) {
        Animation rotation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_animation);
        carkImageView.startAnimation(rotation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                int randomIndex = random.nextInt(isimler.length);
                String randomIsim = isimler[randomIndex];
                textViewRandomIsim.setText("Rastgele gelen isim: " + randomIsim);
            }
        }, 5000); // 5 saniye beklet
    }
}