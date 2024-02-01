package com.example.pregnapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Makale extends AppCompatActivity {

    TextView titleTextView;
    TextView contentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makale);

        // Retrieve the data from the Intent
        String articleTitle = getIntent().getStringExtra("articleTitle");
        String articleContent = getIntent().getStringExtra("articleContent");

        titleTextView = findViewById(R.id.textTitle);
        contentTextView = findViewById(R.id.textContent);

        titleTextView.setText(articleTitle);
        contentTextView.setText(articleContent);
    }
}
