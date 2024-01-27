package com.example.pregnapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class HamilelikAjandam extends AppCompatActivity {
    public CalendarView calendarView;
    public Calendar calendar;
    private TextView tarihSaat, note;
    private ImageButton notekle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hamilelik_ajandam);

        calendarView=findViewById(R.id.calendarView);
        calendar=Calendar.getInstance();
        tarihSaat=findViewById(R.id.tarihSaat);
        notekle=findViewById(R.id.notekle);
        note=findViewById(R.id.note);


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
            addNewEntry(entryData);
        }



        getDate();
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Toast.makeText(HamilelikAjandam.this, dayOfMonth+"/"+(month+1)+"/"+year, Toast.LENGTH_SHORT).show();
                tarihSaat.setText(dayOfMonth+"/"+(month+1)+"/"+year);
            }
        });

    }
    public void getDate(){
        long date= calendarView.getDate();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/mm/yyyy", Locale.getDefault());
        calendar.setTimeInMillis(date);
        String selected_date= simpleDateFormat.format(calendar.getTime());
        //Toast.makeText(this,selected_date,Toast.LENGTH_SHORT).show();
    }
    /*public void setDate(int day, int month, int year){
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,month-1);
        calendar.set(Calendar.DAY_OF_MONTH,day);
        long milli=calendar.getTimeInMillis();
        calendarView.setDate(milli);
    }*/

    private void addNewEntry(String[] entryData) {
        LinearLayout linearLayout = findViewById(R.id.ana);

        // Yeni bir RelativeLayout oluştur
        RelativeLayout newRelativeLayout = new RelativeLayout(this);
        LinearLayout.LayoutParams relativeLayoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        newRelativeLayout.setLayoutParams(relativeLayoutParams);

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
}