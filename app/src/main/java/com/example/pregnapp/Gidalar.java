package com.example.pregnapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class Gidalar extends AppCompatActivity {
    private Toolbar toolbarGidalar;
    private RelativeLayout relativeTehlikeli, relativeTehlikesiz;
    private Button buttonTehlikeli, buttonTehlikesiz, buttonCareful;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gidalar);
        toolbarGidalar=findViewById(R.id.toolbarGidalar);

        toolbarGidalar.setTitle("Gıdalar");
        toolbarGidalar.setBackgroundColor(getResources().getColor(R.color.darkBlue));

        relativeTehlikeli=findViewById(R.id.relativeTehlikeli);
        relativeTehlikesiz=findViewById(R.id.relativeTehlikelisiz);

        buttonTehlikeli=findViewById(R.id.buttonTehlikeli);
        buttonTehlikesiz=findViewById(R.id.buttonTehlikesiz);
        buttonCareful=findViewById(R.id.buttonCareful);


    }
    public void showContent(View view) {
        if (view.getId() == R.id.buttonTehlikeli) {
            relativeTehlikeli.setVisibility(View.VISIBLE);
            relativeTehlikesiz.setVisibility(View.GONE);

        } else if (view.getId() == R.id.buttonTehlikesiz) {
            relativeTehlikeli.setVisibility(View.GONE);
            relativeTehlikesiz.setVisibility(View.VISIBLE);

        }/* else if (view.getId() == R.id.button2) {
                layoutButton1.setVisibility(View.GONE);
                layoutButton2.setVisibility(View.GONE);
                layoutButton3.setVisibility(View.VISIBLE);
        } */
    }
}