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
/*
newMinusImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    // Remove the view from the layout
                    linearLayout.removeView(newRelativeLayout);
                    String entryKey = (String) newRelativeLayout.getTag();
                Toast.makeText(HamilelikAjandam.this, entryKey, Toast.LENGTH_SHORT).show();
                // Remove the corresponding entry from SharedPreferences
                removeEntryFromSharedPreferences(entryKey);

            }
        });


        // Horizontal Layout içine eklemeleri yap
        horizontalLayout.addView(newEllipseImageView);
        horizontalLayout.addView(newTarihSaatTextView);
        horizontalLayout.addView(newNoteTextView);
        horizontalLayout.addView(newMinusImageView);

        // RelativeLayout içine Horizontal Layout'ı ekle
        newRelativeLayout.addView(horizontalLayout);

        // Ana LinearLayout'a yeni RelativeLayout'ı ekle
        linearLayout.addView(newRelativeLayout);
    }
    private void displaySavedEntries() {
        LinearLayout linearLayout = findViewById(R.id.ana);

        SharedPreferences sharedPreferences = getSharedPreferences("entry_data", MODE_PRIVATE);
        int entryCount = sharedPreferences.getInt("entry_count", 0);

        for (int i = 0; i < entryCount; i++) {
            String entryKey = "entry_" + i;

            // Retrieve data
            String dateAndTime = sharedPreferences.getString(entryKey + "_date_time", "");
            String note = sharedPreferences.getString(entryKey + "_note", "");

            // Display entry
            String[] entryData = new String[]{dateAndTime, note};
            addNewEntry(entryData);  // Do not pass entryKey when displaying saved entries
        }
    }
    private void removeEntryFromSharedPreferences(String entryKey) {
        SharedPreferences sharedPreferences = getSharedPreferences("entry_data", MODE_PRIVATE);

        // Remove the entry
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(entryKey + "_date_time");
        editor.remove(entryKey + "_note");

        // Update entry count after removal
        int entryCount = sharedPreferences.getInt("entry_count", 0);
        if (entryCount > 0) {
            editor.putInt("entry_count", entryCount - 1);
        }

        // Save changes synchronously
        editor.commit();
    }
 */