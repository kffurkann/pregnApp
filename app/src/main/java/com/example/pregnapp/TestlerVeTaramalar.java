package com.example.pregnapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class TestlerVeTaramalar extends AppCompatActivity {
    //button_geri_testlervetaramalar
    private ImageButton buttongeriGitmeTestler;
    private SharedPreferences sharedPreferencesTestler;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testler_ve_taramalar);
        toolbar = findViewById(R.id.my_toolbar_testlervetaramalar);
        setSupportActionBar(toolbar);



        buttongeriGitmeTestler=findViewById(R.id.button_geri_testlervetaramalar);
        buttongeriGitmeTestler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TestlerVeTaramalar.this, MainActivity.class));
            }
        });

        for (int i = 1; i <= 12; i++) {
            CheckBox checkbox = findViewById(getResources().getIdentifier("checkboxTestler" + i, "id", getPackageName()));
            checkbox.setChecked(sharedPreferencesTestler.getBoolean("checkboxTestler" + i, false));

            int finalI = i;
            checkbox.setOnCheckedChangeListener((buttonView, isChecked) ->
                    saveCheckboxState("checkboxTestler" + finalI, isChecked));
        }

    }

    private void saveCheckboxState(String key, boolean isChecked) {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, isChecked);
        editor.apply();
    }
}