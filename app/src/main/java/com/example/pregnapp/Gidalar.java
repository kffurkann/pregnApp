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

public class Gidalar extends AppCompatActivity {
    private RelativeLayout relativeTehlikeli, relativeTehlikesiz, relativeDikkatli;
    private Button buttonTehlikeli, buttonTehlikesiz, buttonCareful;
    private ImageButton geriGitmeGidalar;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gidalar);

        relativeTehlikeli=findViewById(R.id.relativeTehlikeli);
        relativeTehlikesiz=findViewById(R.id.relativeTehlikelisiz);
        relativeDikkatli=findViewById(R.id.relativeDikkatli);

        buttonTehlikeli=findViewById(R.id.buttonTehlikeli);
        buttonTehlikesiz=findViewById(R.id.buttonTehlikesiz);
        buttonCareful=findViewById(R.id.buttonCareful);
        geriGitmeGidalar=findViewById(R.id.button_geri_gidalar);
        geriGitmeGidalar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //startActivity(new Intent(Gidalar.this, DashBoard.class));
                finish();
            }
        });


    }
    public void showContent(View view) {
        if (view.getId() == R.id.buttonTehlikeli) {
            relativeTehlikeli.setVisibility(View.VISIBLE);
            relativeTehlikesiz.setVisibility(View.GONE);
            relativeDikkatli.setVisibility(View.GONE);
        } else if (view.getId() == R.id.buttonTehlikesiz) {
            relativeTehlikeli.setVisibility(View.GONE);
            relativeTehlikesiz.setVisibility(View.VISIBLE);
            relativeDikkatli.setVisibility(View.GONE);
        } else if (view.getId() == R.id.buttonCareful) {
            relativeTehlikeli.setVisibility(View.GONE);
            relativeTehlikesiz.setVisibility(View.GONE);
            relativeDikkatli.setVisibility(View.VISIBLE);
        }
    }
}