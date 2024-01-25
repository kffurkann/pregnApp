package com.example.pregnapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;



public class ihtiyacListesi extends AppCompatActivity {
    private RelativeLayout relativeAnne, relativeBebek;
    private Button buttonAnne, buttonBebek;
    private SharedPreferences sharedPreferencesTestler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ihtiyac_listesi);

        relativeAnne=findViewById(R.id.relativeAnne);
        relativeBebek=findViewById(R.id.relativeBebek);

    buttonAnne=findViewById(R.id.buttonAnne);
    buttonBebek=findViewById(R.id.buttonBebek);

        sharedPreferencesTestler = getPreferences(Context.MODE_PRIVATE);

        CheckBox checkboxTest1 = findViewById(R.id.checkboxTest1);
        checkboxTest1.setChecked(sharedPreferencesTestler.getBoolean("checkboxTest1", false));

        CheckBox checkboxTest2 = findViewById(R.id.checkboxTest2);
        checkboxTest2.setChecked(sharedPreferencesTestler.getBoolean("checkboxTest2", false));

        CheckBox checkboxTest3 = findViewById(R.id.checkboxTest3);
        checkboxTest3.setChecked(sharedPreferencesTestler.getBoolean("checkboxTest3", false));

        CheckBox checkboxTest4 = findViewById(R.id.checkboxTest4);
        checkboxTest4.setChecked(sharedPreferencesTestler.getBoolean("checkboxTest4", false));

        CheckBox checkboxTest5 = findViewById(R.id.checkboxTest5);
        checkboxTest5.setChecked(sharedPreferencesTestler.getBoolean("checkboxTest5", false));

        CheckBox checkboxTest6 = findViewById(R.id.checkboxTest6);
        checkboxTest6.setChecked(sharedPreferencesTestler.getBoolean("checkboxTest6", false));

        CheckBox checkboxTest7 = findViewById(R.id.checkboxTest7);
        checkboxTest7.setChecked(sharedPreferencesTestler.getBoolean("checkboxTest7", false));

        CheckBox checkboxTest8 = findViewById(R.id.checkboxTest8);
        checkboxTest8.setChecked(sharedPreferencesTestler.getBoolean("checkboxTest8", false));

        CheckBox checkboxTest11 = findViewById(R.id.checkboxTest11);
        checkboxTest11.setChecked(sharedPreferencesTestler.getBoolean("checkboxTest11", false));

        CheckBox checkboxTest22 = findViewById(R.id.checkboxTest22);
        checkboxTest22.setChecked(sharedPreferencesTestler.getBoolean("checkboxTest22", false));

        CheckBox checkboxTest33 = findViewById(R.id.checkboxTest33);
        checkboxTest33.setChecked(sharedPreferencesTestler.getBoolean("checkboxTest33", false));

        CheckBox checkboxTest44 = findViewById(R.id.checkboxTest44);
        checkboxTest44.setChecked(sharedPreferencesTestler.getBoolean("checkboxTest44", false));

        CheckBox checkboxTest55 = findViewById(R.id.checkboxTest55);
        checkboxTest55.setChecked(sharedPreferencesTestler.getBoolean("checkboxTest55", false));

        CheckBox checkboxTest66 = findViewById(R.id.checkboxTest66);
        checkboxTest66.setChecked(sharedPreferencesTestler.getBoolean("checkboxTest66", false));

        CheckBox checkboxTest77 = findViewById(R.id.checkboxTest77);
        checkboxTest77.setChecked(sharedPreferencesTestler.getBoolean("checkboxTest77", false));

        CheckBox checkboxTest88 = findViewById(R.id.checkboxTest88);
        checkboxTest88.setChecked(sharedPreferencesTestler.getBoolean("checkboxTest88", false));


        checkboxTest1.setOnCheckedChangeListener((buttonView, isChecked) ->
                saveCheckboxState("checkboxTest1", isChecked));

        checkboxTest2.setOnCheckedChangeListener((buttonView, isChecked) ->
                saveCheckboxState("checkboxTest2", isChecked));

        checkboxTest3.setOnCheckedChangeListener((buttonView, isChecked) ->
                saveCheckboxState("checkboxTest3", isChecked));

        checkboxTest4.setOnCheckedChangeListener((buttonView, isChecked) ->
                saveCheckboxState("checkboxTest4", isChecked));

        checkboxTest5.setOnCheckedChangeListener((buttonView, isChecked) ->
                saveCheckboxState("checkboxTest5", isChecked));

        checkboxTest6.setOnCheckedChangeListener((buttonView, isChecked) ->
                saveCheckboxState("checkboxTest6", isChecked));

        checkboxTest7.setOnCheckedChangeListener((buttonView, isChecked) ->
                saveCheckboxState("checkboxTest7", isChecked));

        checkboxTest8.setOnCheckedChangeListener((buttonView, isChecked) ->
                saveCheckboxState("checkboxTest8", isChecked));

        checkboxTest11.setOnCheckedChangeListener((buttonView, isChecked) ->
                saveCheckboxState("checkboxTest11", isChecked));

        checkboxTest22.setOnCheckedChangeListener((buttonView, isChecked) ->
                saveCheckboxState("checkboxTest22", isChecked));

        checkboxTest33.setOnCheckedChangeListener((buttonView, isChecked) ->
                saveCheckboxState("checkboxTest33", isChecked));

        checkboxTest44.setOnCheckedChangeListener((buttonView, isChecked) ->
                saveCheckboxState("checkboxTest44", isChecked));

        checkboxTest55.setOnCheckedChangeListener((buttonView, isChecked) ->
                saveCheckboxState("checkboxTest55", isChecked));

        checkboxTest66.setOnCheckedChangeListener((buttonView, isChecked) ->
                saveCheckboxState("checkboxTest66", isChecked));

        checkboxTest77.setOnCheckedChangeListener((buttonView, isChecked) ->
                saveCheckboxState("checkboxTest77", isChecked));

        checkboxTest88.setOnCheckedChangeListener((buttonView, isChecked) ->
                saveCheckboxState("checkboxTest88", isChecked));


    }


    public void showContent(View view) {
        if (view.getId() == R.id.buttonAnne) {
                relativeAnne.setVisibility(View.VISIBLE);
                relativeBebek.setVisibility(View.GONE);

        } else if (view.getId() == R.id.buttonBebek) {
                relativeAnne.setVisibility(View.GONE);
                relativeBebek.setVisibility(View.VISIBLE);

        }
    }

    private void saveCheckboxState(String key, boolean isChecked) {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, isChecked);
        editor.apply();
    }
}