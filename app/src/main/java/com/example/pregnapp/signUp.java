package com.example.pregnapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
    }
}