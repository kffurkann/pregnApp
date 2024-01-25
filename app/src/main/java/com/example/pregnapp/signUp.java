package com.example.pregnapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class signUp extends AppCompatActivity {
    private TextView textPassToLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        textPassToLogin=findViewById(R.id.textPassToLogin);
        textPassToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(signUp.this,login.class));
            }
        });

        TextView textViewMain = findViewById(R.id.textViewMain);

        // TextView üzerine tıklama event'i
        textViewMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(); // Yeni ekranı göster
            }
        });
    }

    // Yeni ekranı gösteren metod
    private void showPopup() {
        // Dialog oluştur
        Dialog popupDialog = new Dialog(this);
        popupDialog.setContentView(R.layout.activity_popup);

        // Kapatma işaretine tıklama event'i
        ImageView imageViewClose = popupDialog.findViewById(R.id.imageViewClose);
        imageViewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupDialog.dismiss(); // Popup'ı kapat
            }
        });

        // Dialog'u göster
        popupDialog.show();
    }

}