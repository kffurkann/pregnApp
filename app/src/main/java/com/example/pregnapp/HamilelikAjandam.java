package com.example.pregnapp;

import static android.graphics.Color.parseColor;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class HamilelikAjandam extends AppCompatActivity {
    private BottomSheetDialog bottomSheetDialogAjandam;
    private ImageButton button_geri_ajandam;
    public CalendarView calendarView;
    public Calendar calendar;
    private ImageButton notekle;
    private String selectedDate = "";
    private String selectedTime = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hamilelik_ajandam);
        button_geri_ajandam=findViewById(R.id.button_geri_ajandam);
        calendarView=findViewById(R.id.calendarView);
        calendar=Calendar.getInstance();
        notekle=findViewById(R.id.notekle);





        button_geri_ajandam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { onBackPressed(); }
        });


        String uniqueKey = "Ajandam_" + System.currentTimeMillis();
        List<JSONObject> savedDataList = getSavedData();

        addNewEntry(savedDataList);


        notekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bottomSheetDialogAjandam = new BottomSheetDialog(HamilelikAjandam.this, R.style.BottomSheetThemeAjandam);
                View sheetviewAjandam = LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottomsheetajandam, null);


                Button buttonTarih=sheetviewAjandam.findViewById(R.id.buttonTarihAjandam);
                Button buttonSaat=sheetviewAjandam.findViewById(R.id.buttonSaatAjandam);
                Button buttonEkleDate=sheetviewAjandam.findViewById(R.id.buttonEkleDate);
                Button buttonBackHamilelik=sheetviewAjandam.findViewById(R.id.buttonBackHamilelik);

                EditText gunlukNote = sheetviewAjandam.findViewById(R.id.gunlukNote);
                TextView characterCount = sheetviewAjandam.findViewById(R.id.characterCount);

                InputFilter[] inputFilters = new InputFilter[1];
                inputFilters[0] = new InputFilter.LengthFilter(102);
                gunlukNote.setFilters(inputFilters);

                gunlukNote.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                        // Önceki metin durumunu kontrol etme
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                        // Metinde değişiklik olduğunda çalışır
                        int currentLength = charSequence.length();
                        characterCount.setText(currentLength + "/102");
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        // Metindeki değişiklik sonrasında çalışır
                    }
                });


                calendar = Calendar.getInstance();

                buttonBackHamilelik.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialogAjandam.dismiss();

                    }
                });
                buttonTarih.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDatePickerDialog(buttonTarih);
                    }
                });

                buttonSaat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showTimePickerDialog(buttonSaat);
                    }
                });

                buttonEkleDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (selectedDate.isEmpty()) {
                            selectedDate = Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "/" +
                                    (Calendar.getInstance().get(Calendar.MONTH) + 1) + "/" +
                                    Calendar.getInstance().get(Calendar.YEAR);
                        }

                        if (selectedTime.isEmpty()) {
                            selectedTime = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" +
                                    Calendar.getInstance().get(Calendar.MINUTE);
                        }


                        String selectedDateTime = selectedDate + "\n" + selectedTime;
                        String gunlukNot = gunlukNote.getText().toString();

                        saveToSharedPreferences(selectedDateTime, gunlukNot);

                        List<JSONObject> updatedDataList = getSavedData();

                        LinearLayout linearLayout = findViewById(R.id.ana);
                        linearLayout.removeAllViews();
                        addNewEntry(updatedDataList);

                        bottomSheetDialogAjandam.dismiss();



                    }
                });

                bottomSheetDialogAjandam.setContentView(sheetviewAjandam);
                bottomSheetDialogAjandam.show();
                bottomSheetDialogAjandam.getWindow().setBackgroundDrawable(new ColorDrawable(parseColor("#80000000")));

            }
            private void showDatePickerDialog(Button buttonTarih) {
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        HamilelikAjandam.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                // Seçilen tarih ile ilgili işlemleri yapabilirsiniz
                                selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                                buttonTarih.setText(selectedDate);
                            }
                        },
                        year, month, day
                );

                datePickerDialog.show();
            }

            private void showTimePickerDialog(Button buttonSaat) {
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        HamilelikAjandam.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                // Seçilen saat ile ilgili işlemleri yapabilirsiniz
                                selectedTime = hourOfDay + ":" + minute;
                                buttonSaat.setText(selectedTime);
                            }
                        },
                        hour, minute, true
                );

                timePickerDialog.show();
            }

        });



    }

    private void addNewEntry(List<JSONObject> dataList) {
        LinearLayout linearLayout = findViewById(R.id.ana);

        for (JSONObject data : dataList) {
            try {
                String selectedDateTime = data.getString("selectedDateTime");
                String gunlukNot = data.getString("gunlukNot");
                String uniqueId = data.getString("id");

                RelativeLayout newRelativeLayout = new RelativeLayout(this);
                newRelativeLayout.setId(View.generateViewId());
                newRelativeLayout.setTag(uniqueId);
                RelativeLayout.LayoutParams relativeLayoutParams = new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                relativeLayoutParams.setMargins(0, 10, 0, 20);
                newRelativeLayout.setLayoutParams(relativeLayoutParams);

                // Yeni bir Horizontal LinearLayout oluştur
                LinearLayout horizontalLayout = new LinearLayout(this);
                LinearLayout.LayoutParams horizontalLayoutParams = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                horizontalLayout.setOrientation(LinearLayout.HORIZONTAL);
                horizontalLayout.setLayoutParams(horizontalLayoutParams);

                // Yeni bir ImageView oluştur (Ellipse)
                ImageView newEllipseImageView = new ImageView(this);
                newEllipseImageView.setId(View.generateViewId());
                newEllipseImageView.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.MATCH_PARENT));
                newEllipseImageView.setImageResource(R.drawable.ellipse_icon);

                // Yeni bir TextView oluştur (Tarih ve Saat)
                TextView newTarihSaatTextView = new TextView(this);
                newTarihSaatTextView.setId(View.generateViewId());
                newTarihSaatTextView.setText(selectedDateTime);
                newTarihSaatTextView.setLayoutParams(new LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        1));

                newTarihSaatTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                newTarihSaatTextView.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                newTarihSaatTextView.setPadding(5, 0, 3, 0);
                newTarihSaatTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
                newTarihSaatTextView.setTypeface(null, Typeface.BOLD);

                // Yeni bir TextView oluştur (Not)
                TextView newNoteTextView = new TextView(this);
                newNoteTextView.setId(View.generateViewId());
                newNoteTextView.setText(gunlukNot);
                newNoteTextView.setLayoutParams(new LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        3));
                newNoteTextView.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                newNoteTextView.setPadding(2, 0, 5, 0);
                newNoteTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);

                // Yeni bir ImageView oluştur (Minus)
                ImageView newMinusImageView = new ImageView(this);
                newMinusImageView.setId(View.generateViewId());
                newMinusImageView.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.MATCH_PARENT));
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

                newMinusImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        removeFromSharedPreferences(uniqueId);

                        // UI'daki görüntüyü güncelle
                        LinearLayout linearLayout = findViewById(R.id.ana);
                        linearLayout.removeView(newRelativeLayout);
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }



    private void saveToSharedPreferences(String selectedDateTime, String gunlukNot) {
        SharedPreferences sharedPreferences = getSharedPreferences("AjandamData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // JSON nesnesi oluştur
        JSONObject jsonObject = new JSONObject();
        try {
            // Benzersiz bir ID oluştur
            String uniqueId = "Ajandam_" + System.currentTimeMillis();

            jsonObject.put("id", uniqueId);
            jsonObject.put("gunlukNot", gunlukNot);
            jsonObject.put("selectedDateTime", selectedDateTime);

            editor.putString(uniqueId, jsonObject.toString());
            editor.apply();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private List<JSONObject> getSavedData() {
        SharedPreferences sharedPreferences = getSharedPreferences("AjandamData", MODE_PRIVATE);
        Map<String, ?> allEntries = sharedPreferences.getAll();
        List<JSONObject> dataList = new ArrayList<>();

        // Anahtarları ve JSON nesnelerini bir listeye topla
        List<Map.Entry<String, ?>> entries = new ArrayList<>(allEntries.entrySet());

        // Anahtarları (zaman damgaları) kullanarak listeyi sırala
        Collections.sort(entries, new Comparator<Map.Entry<String, ?>>() {
            @Override
            public int compare(Map.Entry<String, ?> entry1, Map.Entry<String, ?> entry2) {
                return entry1.getKey().compareTo(entry2.getKey());
            }
        });

        for (Map.Entry<String, ?> entry : entries) {
            try {
                JSONObject jsonObject = new JSONObject((String) entry.getValue());
                dataList.add(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return dataList;
    }

    private void removeFromSharedPreferences(String uniqueId) {
        SharedPreferences sharedPreferences = getSharedPreferences("AjandamData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(uniqueId);
        editor.apply();
}
}