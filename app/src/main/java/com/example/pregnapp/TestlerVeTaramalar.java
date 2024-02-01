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
                onBackPressed();
            }
        });

        sharedPreferencesTestler = getPreferences(Context.MODE_PRIVATE);

        // Her bir checkbox'un durumunu SharedPreferences'ten oku ve ayarla
        CheckBox checkboxTest1Test = findViewById(R.id.checkboxTest1);
        checkboxTest1Test.setChecked(sharedPreferencesTestler.getBoolean("checkboxTest1Test", false));

        CheckBox checkboxTest2Test = findViewById(R.id.checkboxTest2);
        checkboxTest2Test.setChecked(sharedPreferencesTestler.getBoolean("checkboxTest2Test", false));

        CheckBox checkboxTest3Test = findViewById(R.id.checkboxTest3);
        checkboxTest3Test.setChecked(sharedPreferencesTestler.getBoolean("checkboxTest3Test", false));

        CheckBox checkboxTest4Test = findViewById(R.id.checkboxTest4);
        checkboxTest4Test.setChecked(sharedPreferencesTestler.getBoolean("checkboxTest4Test", false));

        CheckBox checkboxTest5Test = findViewById(R.id.checkboxTest5);
        checkboxTest5Test.setChecked(sharedPreferencesTestler.getBoolean("checkboxTest5Test", false));

        CheckBox checkboxTest6Test = findViewById(R.id.checkboxTest6);
        checkboxTest6Test.setChecked(sharedPreferencesTestler.getBoolean("checkboxTest6Test", false));

        CheckBox checkboxTest7Test = findViewById(R.id.checkboxTest7);
        checkboxTest7Test.setChecked(sharedPreferencesTestler.getBoolean("checkboxTest7Test", false));

        CheckBox checkboxTest8Test = findViewById(R.id.checkboxTest8);
        checkboxTest8Test.setChecked(sharedPreferencesTestler.getBoolean("checkboxTest8Test", false));

        CheckBox checkboxTest9Test = findViewById(R.id.checkboxTest9);
        checkboxTest9Test.setChecked(sharedPreferencesTestler.getBoolean("checkboxTest9Test", false));

        CheckBox checkboxTest10Test = findViewById(R.id.checkboxTest10);
        checkboxTest10Test.setChecked(sharedPreferencesTestler.getBoolean("checkboxTest10Test", false));

        CheckBox checkboxTest11Test = findViewById(R.id.checkboxTest11);
        checkboxTest11Test.setChecked(sharedPreferencesTestler.getBoolean("checkboxTest11Test", false));

        CheckBox checkboxTest12Test = findViewById(R.id.checkboxTest12);
        checkboxTest12Test.setChecked(sharedPreferencesTestler.getBoolean("checkboxTest12Test", false));


        checkboxTest1Test.setOnCheckedChangeListener((buttonView, isChecked) ->
                saveCheckboxState("checkboxTest1Test", isChecked));

        checkboxTest2Test.setOnCheckedChangeListener((buttonView, isChecked) ->
                saveCheckboxState("checkboxTest2Test", isChecked));

        checkboxTest3Test.setOnCheckedChangeListener((buttonView, isChecked) ->
                saveCheckboxState("checkboxTest3Test", isChecked));

        checkboxTest4Test.setOnCheckedChangeListener((buttonView, isChecked) ->
                saveCheckboxState("checkboxTest4Test", isChecked));

        checkboxTest5Test.setOnCheckedChangeListener((buttonView, isChecked) ->
                saveCheckboxState("checkboxTest5Test", isChecked));

        checkboxTest6Test.setOnCheckedChangeListener((buttonView, isChecked) ->
                saveCheckboxState("checkboxTest6Test", isChecked));

        checkboxTest7Test.setOnCheckedChangeListener((buttonView, isChecked) ->
                saveCheckboxState("checkboxTest7Test", isChecked));

        checkboxTest8Test.setOnCheckedChangeListener((buttonView, isChecked) ->
                saveCheckboxState("checkboxTest8Test", isChecked));

        checkboxTest9Test.setOnCheckedChangeListener((buttonView, isChecked) ->
                saveCheckboxState("checkboxTest9Test", isChecked));

        checkboxTest10Test.setOnCheckedChangeListener((buttonView, isChecked) ->
                saveCheckboxState("checkboxTest10Test", isChecked));

        checkboxTest11Test.setOnCheckedChangeListener((buttonView, isChecked) ->
                saveCheckboxState("checkboxTest11Test", isChecked));

        checkboxTest12Test.setOnCheckedChangeListener((buttonView, isChecked) ->
                saveCheckboxState("checkboxTest12Test", isChecked));


    }

    private void saveCheckboxState(String key, boolean isChecked) {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, isChecked);
        editor.apply();
    }
}