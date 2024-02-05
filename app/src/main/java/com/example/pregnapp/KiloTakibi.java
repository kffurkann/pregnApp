package com.example.pregnapp;

import static android.graphics.Color.parseColor;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

public class KiloTakibi extends AppCompatActivity {



    private double previousKiloValue = 0.0;
    private String selectedDate = "";
    private String selectedTime = "";



    private Calendar calendar;

    private ImageButton buttongeriGitmeKiloTakibi;
    private Button buttonKiloEkle;
    private BottomSheetDialog bottomSheetDialog2, bottomSheetDialog3;
    private TextView textViewPanelKiloSon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kilo_takibi);
        calendar = Calendar.getInstance();




        buttongeriGitmeKiloTakibi=findViewById(R.id.button_geri_kilo_takibi);
        buttonKiloEkle=findViewById(R.id.buttonKiloEkle);
        textViewPanelKiloSon=findViewById(R.id.textViewPanelKiloSon);

        buttongeriGitmeKiloTakibi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        List<WeightEntry> weightEntries = retrieveWeightEntriesFromSharedPreferences();
        for (WeightEntry entry : weightEntries) {
            createNewRelativeLayout(entry.getKiloValue(), entry.getSelectedDateTime(), entry.getDifference());
        }


        buttonKiloEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                bottomSheetDialog2 = new BottomSheetDialog(KiloTakibi.this, R.style.BottomSheetTheme);
                View sheetview2 = LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottomsheetkilo, null);


                EditText kilobefore = sheetview2.findViewById(R.id.kilobefore);
                TextView kilovirgül = sheetview2.findViewById(R.id.kilovirgül);
                EditText kiloafter = sheetview2.findViewById(R.id.kiloafter);
                Button buttonKiloiptal = sheetview2.findViewById(R.id.buttonKiloiptal);
                Button buttonKiloeEklesheet = sheetview2.findViewById(R.id.buttonKiloEklesheet);
                Button buttonTarihKilo=sheetview2.findViewById(R.id.buttonTarihKilo);
                Button buttonSaatKilo=sheetview2.findViewById(R.id.buttonSaatKilo);


                InputFilter inputFilterKiloBefore = new InputFilter() {
                    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                        // Sadece 2 haneli sayılara izin ver
                        String inputText = dest.toString() + source.toString();
                        if (inputText.matches("\\d{0,2}")) {
                            return null;
                        }
                        return "";
                    }
                };
                kilobefore.setFilters(new InputFilter[] { inputFilterKiloBefore });

                InputFilter inputFilterKiloAfter = new InputFilter() {
                    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                        // Sadece 2 haneli sayılara izin ver
                        String inputText = dest.toString() + source.toString();
                        if (inputText.matches("\\d{0,2}")) {
                            return null;
                        }
                        return "";
                    }
                };
                kiloafter.setFilters(new InputFilter[] { inputFilterKiloAfter });

                buttonKiloiptal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog2.dismiss();
                    }
                });

                buttonKiloeEklesheet.setOnClickListener(new View.OnClickListener() {
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

                        String kiloBeforeText = kilobefore.getText().toString();
                        String kiloAfterText = kiloafter.getText().toString();
                        try {
                            double kiloValue = Double.parseDouble(kiloBeforeText + "." + kiloAfterText);
                            // Çıkarma işlemi yap
                            double difference = kiloValue - previousKiloValue;
                            difference = Math.round(difference * 100.0) / 100.0;
                            // Ardından yeni RelativeLayout oluştur ve bilgileri içine ekle
                            createNewRelativeLayout(kiloValue, selectedDateTime, difference);


                            // previousKiloValue'yi güncelle
                            previousKiloValue = kiloValue;
                            textViewPanelKiloSon.setText(String.valueOf(previousKiloValue));
                            // Geri kalan işlemler
                        } catch (NumberFormatException e) {

                            e.printStackTrace();

                            Toast.makeText(getApplicationContext(), "Geçerli bir kilo değeri giriniz.", Toast.LENGTH_SHORT).show();
                        }

                        bottomSheetDialog2.dismiss();

                    }
                });
                buttonTarihKilo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDatePickerDialog();

                    }
                });
                buttonSaatKilo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showTimePickerDialog();

                    }
                });


                bottomSheetDialog2.setContentView(sheetview2);
                bottomSheetDialog2.show();
                bottomSheetDialog2.getWindow().setBackgroundDrawable(new ColorDrawable(parseColor("#80000000")));



            }


            private void showDatePickerDialog() {
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        KiloTakibi.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                // Seçilen tarih ile ilgili işlemleri yapabilirsiniz
                                selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
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
                        KiloTakibi.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                // Seçilen saat ile ilgili işlemleri yapabilirsiniz
                                selectedTime = hourOfDay + ":" + minute;
                            }
                        },
                        hour, minute, true
                );

                timePickerDialog.show();
            }



        });






    }
    private void createNewRelativeLayout(double kiloValue, String selectedDateTime,  double difference) {

        // Yeni bir RelativeLayout oluştur
        RelativeLayout newRelativeLayout = new RelativeLayout(KiloTakibi.this);
        newRelativeLayout.setId(View.generateViewId());

        // Parametreleri ayarla (örneğin, genişlik ve yükseklik)
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        int marginInDp = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                10, // 10dp
                getResources().getDisplayMetrics()
        );

        // Bu layoutParams'i yeni RelativeLayout'a uygula
        newRelativeLayout.setLayoutParams(layoutParams);
        layoutParams.setMargins(0, marginInDp, 0, 0);
        newRelativeLayout.setPadding(marginInDp, marginInDp, marginInDp, marginInDp);
        newRelativeLayout.setBackgroundColor(ContextCompat.getColor(KiloTakibi.this, R.color.tekmeBackground));



        newRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // BottomSheetDialog'u oluştur
                BottomSheetDialog bottomSheetDialog3 = new BottomSheetDialog(KiloTakibi.this, R.style.BottomSheetTheme);
                View sheetview3 = LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottomsheetkilosil, null);


                Button buttonKiloSil = sheetview3.findViewById(R.id.buttonKiloSil);
                Button buttonKiloSilsheet = sheetview3.findViewById(R.id.buttonKiloSilsheet);

                String uniqueId = (String) v.getTag();

                buttonKiloSilsheet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        removeWeightEntryFromSharedPreferences(uniqueId);
                        LinearLayout linearLayout = findViewById(R.id.listeleme);
                        linearLayout.removeView(newRelativeLayout);

                        bottomSheetDialog3.dismiss();
                    }
                });

                bottomSheetDialog3.setContentView(sheetview3);
                bottomSheetDialog3.show();
                bottomSheetDialog3.getWindow().setBackgroundDrawable(new ColorDrawable(parseColor("#80000000")));
            }
        });




        // Yeni bir HorizontalLayout oluştur
        LinearLayout horizontalLayout = new LinearLayout(KiloTakibi.this);
        LinearLayout.LayoutParams horizontalLayoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        horizontalLayout.setOrientation(LinearLayout.HORIZONTAL);
        horizontalLayout.setLayoutParams(horizontalLayoutParams);



        // TextView oluştur ve içine bilgileri yerleştir
        TextView textViewKiloTarih = new TextView(KiloTakibi.this);
        textViewKiloTarih.setText(selectedDateTime);

        // TextView'ın layoutParams'ini ayarla (örneğin, yükseklik ve genişlik)
        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        textParams.setMargins(10, 0, 5, 0);
        textParams.weight = 2;
        textViewKiloTarih.setTypeface(textViewKiloTarih.getTypeface(), Typeface.BOLD);
        textViewKiloTarih.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        textViewKiloTarih.setLayoutParams(textParams);




        TextView textViewKiloHafta = new TextView(KiloTakibi.this);
        textViewKiloHafta.setText("21.Hafta" + "\n  1.Gün");

        // TextView'ın layoutParams'ini ayarla (örneğin, yükseklik ve genişlik)
        LinearLayout.LayoutParams textParams2 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        textParams2.setMargins(5, 0, 5, 0);
        textParams2.weight = 2;
        textViewKiloHafta.setTypeface(textViewKiloHafta.getTypeface(), Typeface.BOLD);
        textViewKiloHafta.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        textViewKiloHafta.setLayoutParams(textParams2);




        TextView textViewKiloMiktar = new TextView(KiloTakibi.this);
        textViewKiloMiktar.setText(kiloValue+"\n kg");

        // TextView'ın layoutParams'ini ayarla (örneğin, yükseklik ve genişlik)
        LinearLayout.LayoutParams textParams3 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        textParams3.setMargins(5, 0, 5, 0);
        textParams3.weight = 2;
        textViewKiloMiktar.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textViewKiloMiktar.setTypeface(textViewKiloMiktar.getTypeface(), Typeface.BOLD);
        textViewKiloMiktar.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        textViewKiloMiktar.setLayoutParams(textParams3);




        TextView textViewKiloFark = new TextView(KiloTakibi.this);
        if (difference > 0) {
            textViewKiloFark.setText(" +" + difference);
        } else {
            textViewKiloFark.setText(" " + difference);
        }

// TextView'ın layoutParams'ini ayarla (örneğin, yükseklik ve genişlik)
        FrameLayout.LayoutParams textParams4 = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
        );

        textParams4.setMargins(0, 0, 0, 0);
        textParams4.gravity = Gravity.END | Gravity.CENTER_VERTICAL; // Sağa ve ortaya hizala
        textViewKiloFark.setTypeface(textViewKiloFark.getTypeface(), Typeface.BOLD);
        textViewKiloFark.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        textViewKiloFark.setLayoutParams(textParams4);




        // TextView'ı HorizontalLayout'a ekle
        horizontalLayout.addView(textViewKiloTarih);
        horizontalLayout.addView(textViewKiloHafta);
        horizontalLayout.addView(textViewKiloMiktar);
        horizontalLayout.addView(textViewKiloFark);


        // HorizontalLayout'ı yeni RelativeLayout'a ekle
        newRelativeLayout.addView(horizontalLayout);
        String uniqueId = generateUniqueId();

        newRelativeLayout.setTag(uniqueId);

        if (!isWeightEntryExists(uniqueId)) {
            // Bu yeni RelativeLayout'ı tanımlı olan linearLayout'a ekle
            LinearLayout linearLayout = findViewById(R.id.listeleme);
            linearLayout.addView(newRelativeLayout);

            // Save the entry to SharedPreferences
            List<WeightEntry> entryList = new ArrayList<>();
            entryList.add(new WeightEntry(uniqueId, kiloValue, selectedDateTime, difference));
            saveWeightEntriesToSharedPreferences(entryList);
        }

    }

    private String generateUniqueId() {
        return String.valueOf(System.currentTimeMillis());
    }

    private boolean isWeightEntryExists(String uniqueId) {
        List<WeightEntry> weightEntries = retrieveWeightEntriesFromSharedPreferences();
        for (WeightEntry entry : weightEntries) {
            if (entry.getUniqueId().equals(uniqueId)) {
                return true;
            }
        }
        return false;
    }

    private List<WeightEntry> retrieveWeightEntriesFromSharedPreferences() {
        SharedPreferences preferences = getSharedPreferences("WeightData", MODE_PRIVATE);
        String json = preferences.getString("weightEntries", "[]");

        try {
            JSONArray jsonArray = new JSONArray(json);
            List<WeightEntry> weightEntries = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String uniqueId = jsonObject.getString("uniqueId");
                double kiloValue = jsonObject.getDouble("kiloValue");
                String selectedDateTime = jsonObject.getString("selectedDateTime");
                double difference = jsonObject.getDouble("difference");

                weightEntries.add(new WeightEntry(uniqueId, kiloValue, selectedDateTime, difference));
            }

            return weightEntries;
        } catch (JSONException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void saveWeightEntriesToSharedPreferences(List<WeightEntry> weightEntries) {
        SharedPreferences preferences = getSharedPreferences("WeightData", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        try {
            JSONArray jsonArray = new JSONArray();
            for (WeightEntry entry : weightEntries) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("uniqueId", entry.getUniqueId());
                jsonObject.put("kiloValue", entry.getKiloValue());
                jsonObject.put("selectedDateTime", entry.getSelectedDateTime());
                jsonObject.put("difference", entry.getDifference());

                jsonArray.put(jsonObject);
            }

            editor.putString("weightEntries", jsonArray.toString());
            editor.apply();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public class WeightEntry implements Serializable {
        private String uniqueId;
        private double kiloValue;
        private String selectedDateTime;
        private double difference;

        // Constructor with parameters
        public WeightEntry(String uniqueId, double kiloValue, String selectedDateTime, double difference) {
            this.uniqueId = uniqueId;
            this.kiloValue = kiloValue;
            this.selectedDateTime = selectedDateTime;
            this.difference = difference;
        }

        public double getKiloValue() {
            return kiloValue;
        }

        public String getSelectedDateTime() {
            return selectedDateTime;
        }

        public double getDifference() {
            return difference;
        }

        // Getter for uniqueId
        public String getUniqueId() {
            return uniqueId;
        }

        // Other methods or fields if needed
    }

    private void removeWeightEntryFromSharedPreferences(String uniqueId) {
        List<WeightEntry> weightEntries = retrieveWeightEntriesFromSharedPreferences();

        // Remove the entry with the specified uniqueId
        Iterator<WeightEntry> iterator = weightEntries.iterator();
        while (iterator.hasNext()) {
            WeightEntry entry = iterator.next();
            if (entry.getUniqueId().equals(uniqueId)) {
                iterator.remove();
                break;
            }
        }

        // Save the updated list to SharedPreferences
        saveWeightEntriesToSharedPreferences(weightEntries);
    }




}