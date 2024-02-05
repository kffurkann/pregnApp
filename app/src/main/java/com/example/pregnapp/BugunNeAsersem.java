package com.example.pregnapp;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Random;

public class BugunNeAsersem extends AppCompatActivity {

    private ImageView asermekadinView;
    private TextView textViewAser;
    private Button buttonAser;
    private ImageButton geriGitmeAserme;

    private String[] yemekler = {"Pizza", "Hamburger", "Salata", "Pasta", "Sushi", "Makarna", "Adana Kebap",
            "Brokoli Salatası", "Çin Mantısı", "Et Döner", "Fırında Tavuk", "Humus", "İskender Kebap", "Izgara Somon",
            "Karpuz", "Kivi", "Kumpir", "Kuzu Tandır", "Mercimek Çorbası", "Nohut Ezme", "Omlet", "Patates Kızarması",
            "Tavuk Şiş", "Yaprak Sarma", "Yeşil Erik"};
    private int[] yemekResimleri = {
            R.drawable.pizza, R.drawable.hamburger, R.drawable.salata, R.drawable.pasta, R.drawable.sushi,
            R.drawable.makarna, R.drawable.adanakebap, R.drawable.brokolisalatasi, R.drawable.cinmantisi,
            R.drawable.etdoner, R.drawable.firindatavuk, R.drawable.humus, R.drawable.iskender, R.drawable.izgarasomon,
            R.drawable.karpuz, R.drawable.kivi, R.drawable.kumpir, R.drawable.kuzutandir, R.drawable.mercimekcorbasi,
            R.drawable.nohutezme, R.drawable.omlet, R.drawable.patateskizartmasi, R.drawable.tavuksis, R.drawable.yapraksarma,
            R.drawable.yesilerik
    };

    private String sonSecilenYemek = "";

    private Random random = new Random(); //

    private ArrayList<Integer> secilenYemekIndeksleri = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bugun_ne_asersem);
        geriGitmeAserme = findViewById(R.id.button_geri_aserme);
        asermekadinView = findViewById(R.id.asermekadinView);
        textViewAser = findViewById(R.id.textViewAser);
        buttonAser = findViewById(R.id.button_aser);

        geriGitmeAserme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        buttonAser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aserYemek();
            }
        });
    }

    private void aserYemek() {
        int randomIndex;

        if (secilenYemekIndeksleri.size() == yemekler.length) {
            secilenYemekIndeksleri.clear();
        }

        do {
            randomIndex = random.nextInt(yemekler.length);
        } while (secilenYemekIndeksleri.contains(randomIndex));

        secilenYemekIndeksleri.add(randomIndex);

        sonSecilenYemek = yemekler[randomIndex];

        int secilenResim = yemekResimleri[randomIndex];

        textViewAser.setText("Aşerdiğiniz Yemek: " + sonSecilenYemek);
        asermekadinView.setImageResource(secilenResim);
    }
}
