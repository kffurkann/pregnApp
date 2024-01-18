package com.example.pregnapp;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class gebelikAraclari extends AppCompatActivity {

    private RelativeLayout kilo_takibi_relative;
    private RelativeLayout tekme_sayar_relative;
    private RelativeLayout saglik_testleri_relative;
    private RelativeLayout gidalar_relative;
    private RelativeLayout ihtiyac_listesi_relative;
    private RelativeLayout hamilelik_gunlugum_relative;
    private RelativeLayout hamilelik_ajandam_relative;
    private RelativeLayout bebek_isimleri_relative;
    private RelativeLayout hamilelik_cizelgesi_relative;
    private RelativeLayout bugunneasersemLogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gebelik_araclari);
        kilo_takibi_relative=findViewById(R.id.kilo_takibi_relative);
        tekme_sayar_relative=findViewById(R.id.tekme_sayar_relative);
        saglik_testleri_relative=findViewById(R.id.saglik_testleri_relative);
        gidalar_relative=findViewById(R.id.gidalar_relative);
        ihtiyac_listesi_relative=findViewById(R.id.ihtiyac_listesi_relative);
        hamilelik_gunlugum_relative=findViewById(R.id.hamilelik_gunlugum_relative);
        hamilelik_ajandam_relative=findViewById(R.id.hamilelik_ajandam_relative);
        bebek_isimleri_relative=findViewById(R.id.bebek_isimleri_relative);
        hamilelik_cizelgesi_relative=findViewById(R.id.hamilelik_cizelgesi_relative);
        bugunneasersemLogo=findViewById(R.id.bugunne_asersem_relative);

        kilo_takibi_relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Tıklandığında yapılacak işlemler
                showToast("Kilo Takibi tıklandı!");
            }
        });
    }

    // Toast mesajı gösteren yardımcı metot
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}