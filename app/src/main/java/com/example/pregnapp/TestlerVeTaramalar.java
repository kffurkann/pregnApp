package com.example.pregnapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class TestlerVeTaramalar extends AppCompatActivity {

    private SharedPreferences sharedPreferencesTestler;
    private ImageButton buttonGeriTestlerVeTaramalar;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testler_ve_taramalar);
        toolbar = findViewById(R.id.my_toolbar_testlervetaramalar);
        setSupportActionBar(toolbar);

        buttonGeriTestlerVeTaramalar = findViewById(R.id.button_geri_testlervetaramalar);
        sharedPreferencesTestler = getPreferences(Context.MODE_PRIVATE);

        // Her bir checkbox'un durumunu SharedPreferences'ten oku ve ayarla
        CheckBox checkboxTest1 = findViewById(R.id.checkboxTest1);
        checkboxTest1.setChecked(sharedPreferencesTestler.getBoolean("checkboxTest1", false));

        CheckBox checkboxTest2 = findViewById(R.id.checkboxTest2);
        checkboxTest2.setChecked(sharedPreferencesTestler.getBoolean("checkboxTest2", false));

        // Diğer checkbox'ları buraya ekleyin...

        // CheckBox'ların durumları değiştiğinde SharedPreferences'e kaydet
        checkboxTest1.setOnCheckedChangeListener((buttonView, isChecked) ->
                saveCheckboxState("checkboxTest1", isChecked));

        checkboxTest2.setOnCheckedChangeListener((buttonView, isChecked) ->
                saveCheckboxState("checkboxTest2", isChecked));


    }

    private void saveCheckboxState(String key, boolean isChecked) {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, isChecked);
        editor.apply();
    }
}