package com.example.pregnapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class Tools extends Fragment {

    private ImageButton kiloTakibi;
    private ImageButton tekmeSayar;
    private ImageButton saglikTestleri;
    private ImageButton gidalar;
    private ImageButton ihtiyacListesi;
    private ImageButton hamilelikGunlugum;
    private ImageButton hamilelikAjandam;
    private ImageButton bebekIsimleri;
    private ImageButton hamilelikCizelgesi;
    private ImageButton bugunNeAsersem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =
        inflater.inflate(R.layout.fragment_tools, container, false);
        kiloTakibi = view.findViewById(R.id.kilotakibiLogo);
        kiloTakibi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), KiloTakibi.class));
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

        hamilelikGunlugum = view.findViewById(R.id.hamilelikgunluguLogo);
        hamilelikGunlugum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), HamilelikAjandam.class));
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

        hamilelikCizelgesi = view.findViewById(R.id.hamilelikcizelgesiLogo);
        hamilelikCizelgesi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), HamilelikAjandam.class));
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