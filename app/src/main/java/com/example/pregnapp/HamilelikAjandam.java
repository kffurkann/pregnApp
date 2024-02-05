package com.example.pregnapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class HamilelikAjandam extends AppCompatActivity {
    public CalendarView calendarView;
    public Calendar calendar;
    private ImageButton notekle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hamilelik_ajandam);

        calendarView=findViewById(R.id.calendarView);
        calendar=Calendar.getInstance();

        notekle=findViewById(R.id.notekle);


        displaySavedEntries();

        notekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HamilelikAjandam.this, HamilelikAjandamEkle.class));
            }
        });

        Intent intent = getIntent();
        String[] entryData = intent.getStringArrayExtra("entryData");

        // Eğer entryData boş değilse ve en az iki eleman içeriyorsa
        if (entryData != null && entryData.length >= 2) {
            // Verileri göstermek için yeni bir giriş ekleyin
            //addNewEntry(entryData);
        }



    }


    private void addNewEntry(String[] entryData) {
        LinearLayout linearLayout = findViewById(R.id.ana);

        // Yeni bir RelativeLayout oluştur
        RelativeLayout newRelativeLayout = new RelativeLayout(this);


        newRelativeLayout.setId(View.generateViewId());
        LinearLayout.LayoutParams relativeLayoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        newRelativeLayout.setLayoutParams(relativeLayoutParams);

        String entryKey = "entry_" + System.currentTimeMillis(); // Use timestamp as a unique identifier
        newRelativeLayout.setTag(entryKey);


        // Yeni bir Horizontal LinearLayout oluştur
        LinearLayout horizontalLayout = new LinearLayout(this);
        LinearLayout.LayoutParams horizontalLayoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        horizontalLayout.setOrientation(LinearLayout.HORIZONTAL);
        horizontalLayout.setLayoutParams(horizontalLayoutParams);

        // Yeni bir ImageView oluştur (Ellipse)
        ImageView newEllipseImageView = new ImageView(this);
        newEllipseImageView.setId(View.generateViewId());
        newEllipseImageView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        newEllipseImageView.setImageResource(R.drawable.ellipse_icon);

        // Yeni bir TextView oluştur (Tarih ve Saat)
        TextView newTarihSaatTextView = new TextView(this);
        newTarihSaatTextView.setId(View.generateViewId());
        newTarihSaatTextView.setLayoutParams(new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1
        ));
        newTarihSaatTextView.setText(entryData[0]);
        newTarihSaatTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        newTarihSaatTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);

        // Yeni bir TextView oluştur (Not)
        TextView newNoteTextView = new TextView(this);
        newNoteTextView.setId(View.generateViewId());
        newNoteTextView.setLayoutParams(new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                6
        ));
        newNoteTextView.setText(entryData[1]);
        newNoteTextView.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);

        // Yeni bir ImageView oluştur (Minus)
        ImageView newMinusImageView = new ImageView(this);
        newMinusImageView.setId(View.generateViewId());
        newMinusImageView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        newMinusImageView.setImageResource(R.drawable.minus_icon);

        newMinusImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String entryKey = (String) newRelativeLayout.getTag();

                // Remove the view from the layout
                linearLayout.removeView(newRelativeLayout);
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
        editor.apply();
    }

}