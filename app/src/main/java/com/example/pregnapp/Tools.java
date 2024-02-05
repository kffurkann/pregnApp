package com.example.pregnapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class Tools extends Fragment {

    private String userMailT;
    private ImageButton kiloTakibi;
    private ImageButton tekmeSayar;
    private ImageButton saglikTestleri;
    private ImageButton gidalar;
    private ImageButton ihtiyacListesi;
    private ImageButton hamilelikAjandam;
    private ImageButton bebekIsimleri;
    private ImageButton bugunNeAsersem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =
        inflater.inflate(R.layout.fragment_tools, container, false);
        Bundle bundle = getArguments();
        userMailT = bundle.getString("userMail");
        Log.d("userMail DenemeT:", userMailT != null ? userMailT : "userMail is null");
        kiloTakibi = view.findViewById(R.id.kilotakibiLogo);
        kiloTakibi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), KiloTakibi.class);
                intent.putExtra("userMailT", userMailT);
                startActivity(intent);
            }
        });

        tekmeSayar = view.findViewById(R.id.tekmesayarLogo);
        tekmeSayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), TekmeSayar.class));
            }
        });

        saglikTestleri = view.findViewById(R.id.sagliktestleriLogo);
        saglikTestleri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), TestlerVeTaramalar.class));
            }
        });

        gidalar = view.findViewById(R.id.gidalarLogo);
        gidalar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Gidalar.class));
            }
        });

        ihtiyacListesi = view.findViewById(R.id.ihtiyaclistesiLogo);
        ihtiyacListesi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), IhtiyacListesi.class));
            }
        });

        hamilelikAjandam = view.findViewById(R.id.hamilelikajandaLogo);
        hamilelikAjandam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), HamilelikAjandam.class));
            }
        });

        bebekIsimleri = view.findViewById(R.id.bebekisimleriLogo);
        bebekIsimleri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), BebekIsimleri.class));
            }
        });


        bugunNeAsersem = view.findViewById(R.id.bugunneasersemLogo);
        bugunNeAsersem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), BugunNeAsersem.class));
            }
        });

        return view;
    }
}