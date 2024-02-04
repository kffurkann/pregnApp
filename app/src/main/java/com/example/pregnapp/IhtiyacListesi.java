package com.example.pregnapp;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;


public class IhtiyacListesi extends AppCompatActivity {
    private RelativeLayout relativeAnne, relativeBebek, relativeDogum, relativeSusleme;
    private SharedPreferences sharedPreferencesTestler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ihtiyac_listesi);

        relativeAnne=findViewById(R.id.relativeAnne);
        relativeBebek=findViewById(R.id.relativeBebek);
        relativeDogum=findViewById(R.id.relativeDogum);
        relativeSusleme=findViewById(R.id.relativeSusleme);


        sharedPreferencesTestler = getPreferences(Context.MODE_PRIVATE);

        for (int i = 1; i <= 32; i++) {
            CheckBox checkbox = findViewById(getResources().getIdentifier("checkboxTest" + i, "id", getPackageName()));
            checkbox.setChecked(sharedPreferencesTestler.getBoolean("checkboxTest" + i, false));

            int finalI = i;
            checkbox.setOnCheckedChangeListener((buttonView, isChecked) ->
                    saveCheckboxState("checkboxTest" + finalI, isChecked));
        }

    }

    public void showContent(View view) {
        Button clickedButton = (Button) view;
        resetButtonColors();
        clickedButton.setTextColor(getResources().getColor(R.color.white));
        clickedButton.setBackgroundColor(getResources().getColor(R.color.boldPink));
        if (view.getId() == R.id.buttonAnne) {
            relativeAnne.setVisibility(View.VISIBLE);
            relativeBebek.setVisibility(View.GONE);
            relativeDogum.setVisibility(View.GONE);
            relativeSusleme.setVisibility(View.GONE);
        } else if (view.getId() == R.id.buttonBebek) {
            relativeAnne.setVisibility(View.GONE);
            relativeBebek.setVisibility(View.VISIBLE);
            relativeDogum.setVisibility(View.GONE);
            relativeSusleme.setVisibility(View.GONE);
        } else if (view.getId() == R.id.buttonDogum) {
            relativeAnne.setVisibility(View.GONE);
            relativeBebek.setVisibility(View.GONE);
            relativeDogum.setVisibility(View.VISIBLE);
            relativeSusleme.setVisibility(View.GONE);
        } else if (view.getId() == R.id.buttonSusleme) {
            relativeAnne.setVisibility(View.GONE);
            relativeBebek.setVisibility(View.GONE);
            relativeDogum.setVisibility(View.GONE);
            relativeSusleme.setVisibility(View.VISIBLE);
        }
    }
    private void resetButtonColors() {
        Button buttonAnne = findViewById(R.id.buttonAnne);
        Button buttonBebek = findViewById(R.id.buttonBebek);
        Button buttonDogum = findViewById(R.id.buttonDogum);
        Button buttonSusleme = findViewById(R.id.buttonSusleme);

        buttonAnne.setTextColor(getResources().getColor(R.color.boldPink));
        buttonBebek.setTextColor(getResources().getColor(R.color.boldPink));
        buttonDogum.setTextColor(getResources().getColor(R.color.boldPink));
        buttonSusleme.setTextColor(getResources().getColor(R.color.boldPink));

        buttonAnne.setBackgroundColor(getResources().getColor(R.color.lightPink));
        buttonBebek.setBackgroundColor(getResources().getColor(R.color.lightPink));
        buttonDogum.setBackgroundColor(getResources().getColor(R.color.lightPink));
        buttonSusleme.setBackgroundColor(getResources().getColor(R.color.lightPink));
    }


    private void saveCheckboxState(String key, boolean isChecked) {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, isChecked);
        editor.apply();
}

}