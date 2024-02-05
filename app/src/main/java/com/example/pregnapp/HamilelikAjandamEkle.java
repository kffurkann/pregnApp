package com.example.pregnapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class HamilelikAjandamEkle extends AppCompatActivity {
    private Button buttonTarih,buttonSaat;
    private EditText gunlukNote;
    private Button buttonEkleDate;
    private Calendar calendar;
    private String selectedDate = "";
    private String selectedTime = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hamilelik_ajandam_ekle);

        buttonTarih=findViewById(R.id.buttonTarihKilo);
        buttonSaat=findViewById(R.id.buttonSaatKilo);
        buttonEkleDate=findViewById(R.id.buttonEkleDate);

        gunlukNote=findViewById(R.id.gunlukNote);


        calendar = Calendar.getInstance();



        buttonTarih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        buttonSaat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });

        buttonEkleDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ekleButtonTiklandi();
            }
        });

    }

    private void showDatePickerDialog() {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Seçilen tarih ile ilgili işlemleri yapabilirsiniz
                        selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                        Toast.makeText(HamilelikAjandamEkle.this, "Seçilen Tarih: " + selectedDate, Toast.LENGTH_SHORT).show();
                    }
                },
                year, month, day
        );

        datePickerDialog.show();
    }

    private void showTimePickerDialog() {
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // Seçilen saat ile ilgili işlemleri yapabilirsiniz
                        selectedTime = hourOfDay + ":" + minute;
                        Toast.makeText(HamilelikAjandamEkle.this, "Seçilen Saat: " + selectedTime, Toast.LENGTH_SHORT).show();
                    }
                },
                hour, minute, true
        );

        timePickerDialog.show();
    }

    public void ekleButtonTiklandi() {
        // HamilelikAjandamEkle sınıfındaki tarih ve saat bilgisini al
        String secilenTarihSaat = getSecilenTarihSaat(selectedDate, selectedTime);
        String gunlukNot = gunlukNote.getText().toString();

        final String[] entryData = new String[]{"14/11/2023", "Bu bir örnek nottur."};
        entryData[0] = secilenTarihSaat;
        entryData[1] = gunlukNot;

        // Intentle bilgiyi ekleyerek HamilelikAjandam sınıfına gönder
        Intent intent = new Intent(HamilelikAjandamEkle.this, HamilelikAjandam.class);
        intent.putExtra("entryData", entryData);
        startActivity(intent);
        Toast.makeText(this, entryData[0], Toast.LENGTH_SHORT).show();

        saveEntryDataToSharedPreferences(secilenTarihSaat, gunlukNot);

        finish();


    }


    private String getSecilenTarihSaat(String selectedDate, String selectedTime) {
        // seçilen tarih ve saat bilgisini işleme yeri
        String secilenTarihSaat = "";

        if (selectedDate != null && !selectedDate.isEmpty()) {
            secilenTarihSaat += selectedDate;
        }

        if (selectedTime != null && !selectedTime.isEmpty()) {
            if (!secilenTarihSaat.isEmpty()) {
                secilenTarihSaat += " ";
            }
            secilenTarihSaat += selectedTime;
        }

        return secilenTarihSaat;
    }
    private void saveEntryDataToSharedPreferences(String selectedDateTime, String note) {
        SharedPreferences sharedPreferences = getSharedPreferences("entry_data", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Geçerli giriş sayısını al
        final int entryCount = sharedPreferences.getInt("entry_count", 0);

        // Yeni girişin anahtarı olarak entryCountı kullanın
        String entryKey = "entry_" + entryCount;

        // veriyi kaydet
        editor.putString(entryKey + "_date_time", selectedDateTime);
        editor.putString(entryKey + "_note", note);

        // entry countu güncelle
        editor.putInt("entry_count", entryCount + 1);

        editor.apply();
    }

}
