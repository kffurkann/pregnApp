package com.example.pregnapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class HamilelikAjandam extends AppCompatActivity {
    public CalendarView calendarView;
    public Calendar calendar;
    private TextView gunluk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hamilelik_ajandam);

        calendarView=findViewById(R.id.calendarView);
        calendar=Calendar.getInstance();
        gunluk=findViewById(R.id.gunluk);


        getDate();
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Toast.makeText(HamilelikAjandam.this, dayOfMonth+"/"+(month+1)+"/"+year, Toast.LENGTH_SHORT).show();
                gunluk.setText(dayOfMonth+"/"+(month+1)+"/"+year);
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
}