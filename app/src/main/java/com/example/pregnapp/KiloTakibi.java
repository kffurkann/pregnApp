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
import android.util.Log;
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

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class KiloTakibi extends AppCompatActivity {

    private String weightT;
    private double previousKiloValue = 0.0;
    private String selectedDate = "";
    private String selectedTime = "";
    private Calendar calendar;
    private ImageButton buttongeriGitmeKiloTakibi;
    private Button buttonKiloEkle;
    private BottomSheetDialog bottomSheetDialog2, bottomSheetDialog3;
    private TextView textViewPanelKiloSon;
    private TextView textViewPanelKiloFark;
    private String userMailT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kilo_takibi);
        userMailT = getIntent().getStringExtra("userMailT");

        Log.d("userMailT", userMailT != null ? userMailT : "userMail is null");
        String uniqueKey = "Entry_" + System.currentTimeMillis();
        List<JSONObject> savedDataList = getSavedData();
        textViewPanelKiloSon = findViewById(R.id.textViewPanelKiloSon);
        if (!savedDataList.isEmpty()) {
            try {
                JSONObject lastEntry = savedDataList.get(savedDataList.size() - 1);
                previousKiloValue = lastEntry.getDouble("kiloValue");
                textViewPanelKiloSon.setText(String.valueOf(previousKiloValue));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        createNewRelativeLayout(savedDataList);
        calendar = Calendar.getInstance();
        buttongeriGitmeKiloTakibi = findViewById(R.id.button_geri_kilo_takibi);
        buttonKiloEkle = findViewById(R.id.buttonKiloEkle);
        textViewPanelKiloFark = findViewById(R.id.textViewPanelKiloFark);

        getUserData();

        buttongeriGitmeKiloTakibi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


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
                Button buttonTarihKilo = sheetview2.findViewById(R.id.buttonTarihKilo);
                Button buttonSaatKilo = sheetview2.findViewById(R.id.buttonSaatKilo);


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
                kilobefore.setFilters(new InputFilter[]{inputFilterKiloBefore});

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
                kiloafter.setFilters(new InputFilter[]{inputFilterKiloAfter});

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
                        InputFilter inputFilterNumeric = new InputFilter() {
                            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                                // Sadece sayısal değerlere izin ver
                                for (int i = start; i < end; i++) {
                                    if (!Character.isDigit(source.charAt(i))) {
                                        return "";
                                    }
                                }
                                return null;
                            }
                        };

                        kilobefore.setFilters(new InputFilter[]{inputFilterNumeric});
                        kiloafter.setFilters(new InputFilter[]{inputFilterNumeric});

                        String kiloBeforeText = kilobefore.getText().toString();
                        String kiloAfterText = kiloafter.getText().toString();
                        try {
                            double kiloValue=0.0;

                            kiloValue = Double.parseDouble(kiloBeforeText + "." + kiloAfterText);

                            double lastWeight = Double.parseDouble(weightT);

                            double difference2 = kiloValue - lastWeight;
                            // Farkı hesapla
                            double difference = kiloValue - previousKiloValue;
                            difference = Math.round(difference * 100.0) / 100.0;

                            textViewPanelKiloFark.setText(String.format("%.2f", difference2));


                            previousKiloValue = kiloValue;

                            textViewPanelKiloSon.setText(String.valueOf(previousKiloValue));
                            textViewPanelKiloSon.setText(String.format("%.2f", kiloValue));
                            saveToSharedPreferences(kiloValue, selectedDateTime, difference);

                            List<JSONObject> updatedDataList = getSavedData();

                            // UI güncellemesi
                            LinearLayout linearLayout = findViewById(R.id.listeleme);
                            linearLayout.removeAllViews();
                            createNewRelativeLayout(updatedDataList);
                            // ... diğer işlemler ...
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
                        showDatePickerDialog(buttonTarihKilo);

                    }
                });
                buttonSaatKilo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showTimePickerDialog(buttonSaatKilo);

                    }
                });


                bottomSheetDialog2.setContentView(sheetview2);
                bottomSheetDialog2.show();
                bottomSheetDialog2.getWindow().setBackgroundDrawable(new ColorDrawable(parseColor("#80000000")));


            }


            private void showDatePickerDialog(Button buttonTarihKilo) {
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
                                buttonTarihKilo.setText(selectedDate);
                            }
                        },
                        year, month, day
                );

                datePickerDialog.show();
            }

            private void showTimePickerDialog(Button buttonSaatKilo) {
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        KiloTakibi.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                // Seçilen saat ile ilgili işlemleri yapabilirsiniz
                                selectedTime = hourOfDay + ":" + minute;
                                buttonSaatKilo.setText(selectedTime);
                            }
                        },
                        hour, minute, true
                );

                timePickerDialog.show();
            }
        });
    }


   private void getUserData() {
        OkHttpClient client = new OkHttpClient();
        String url = "http://10.0.2.2:5274/api/User/GetUser?mail=" + userMailT;

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Request request = new Request.Builder()
                            .url(url)
                            .build();

                    Response response = client.newCall(request).execute();
                    final String responseData = response.body() != null ? response.body().string() : null;

                    if (response.isSuccessful() && responseData != null) {
                        Log.d("ProfileAPI", "Response data: " + responseData);
                        JSONObject jsonObject = new JSONObject(responseData);

                        weightT = jsonObject.getString("userWeight");
                        Log.d("weightT:", weightT);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TextView textViewPanelKiloBaslangic = findViewById(R.id.textViewPanelKiloBaslangic);
                                textViewPanelKiloBaslangic.setText(weightT);
                                double ilk = Double.parseDouble(weightT);
                                double fark = previousKiloValue - ilk;
                                textViewPanelKiloFark.setText(String.valueOf(fark));
                            }
                        });

                    } else {
                        Log.e("ProfileAPI", "Response unsuccessful or empty data");
                    }
                } catch (Exception e) {
                    Log.e("ProfileAPI", "Error fetching user data", e);
                }
            }
        }).start();
    }

    private void createNewRelativeLayout(List<JSONObject> dataList) {
        LinearLayout linearLayout = findViewById(R.id.listeleme);

        for (JSONObject data : dataList) {
            try {
                double kiloValue = data.getDouble("kiloValue");
                String selectedDateTime = data.getString("selectedDateTime");
                double difference = data.getDouble("difference");
                String uniqueId = data.getString("id");


                // Yeni bir RelativeLayout oluştur
                RelativeLayout newRelativeLayout = new RelativeLayout(KiloTakibi.this);
                newRelativeLayout.setId(View.generateViewId());
                newRelativeLayout.setTag(uniqueId);

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
                                removeFromSharedPreferences(uniqueId);

                                // UI'daki görüntüyü güncelle
                                LinearLayout linearLayout = findViewById(R.id.listeleme);
                                linearLayout.removeView(newRelativeLayout);

                                List<JSONObject> updatedDataList = getSavedData();
                                if (!updatedDataList.isEmpty()) {
                                    try {
                                        JSONObject lastEntry = updatedDataList.get(updatedDataList.size() - 1);
                                        double lastKiloValue = lastEntry.getDouble("kiloValue");
                                        textViewPanelKiloSon.setText(String.valueOf(lastKiloValue));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    textViewPanelKiloSon.setText(""); // Tüm veriler silindiğinde
                                }

                                double difference = previousKiloValue - Double.parseDouble(weightT);
                                difference = Math.round(difference * 100.0) / 100.0;
                                textViewPanelKiloFark.setText(String.format("%.2f", difference));

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

                textParams.setMargins(50, 0, 0, 0);
                textParams.weight = 1;
                textParams.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL;
                textViewKiloTarih.setTypeface(textViewKiloTarih.getTypeface(), Typeface.BOLD);
                textViewKiloTarih.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                textViewKiloTarih.setLayoutParams(textParams);

                TextView textViewKiloMiktar = new TextView(KiloTakibi.this);
                textViewKiloMiktar.setText(kiloValue + "\n kg");

                LinearLayout.LayoutParams textParams3 = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                textParams3.setMargins(0, 0, 70, 0);
                textParams3.weight = 1;
                textParams3.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL;
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

                LinearLayout.LayoutParams textParams4 = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

                textParams4.setMargins(100, 0, 0, 0);
                textParams4.weight=1;
                textParams4.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL; // Sağa ve ortaya hizala
                textViewKiloFark.setTypeface(textViewKiloFark.getTypeface(), Typeface.BOLD);
                textViewKiloFark.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                textViewKiloFark.setLayoutParams(textParams4);


                // TextView'ı HorizontalLayout'a ekle
                horizontalLayout.addView(textViewKiloTarih);
                horizontalLayout.addView(textViewKiloMiktar);
                horizontalLayout.addView(textViewKiloFark);


                // HorizontalLayout'ı yeni RelativeLayout'a ekle
                newRelativeLayout.addView(horizontalLayout);
                linearLayout.addView(newRelativeLayout);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveToSharedPreferences(double kiloValue, String selectedDateTime, double difference) {
        SharedPreferences sharedPreferences = getSharedPreferences("KiloData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // JSON nesnesi oluştur
        JSONObject jsonObject = new JSONObject();
        try {
            // Benzersiz bir ID oluştur
            String uniqueId = "Entry_" + System.currentTimeMillis();

            jsonObject.put("id", uniqueId);
            jsonObject.put("kiloValue", kiloValue);
            jsonObject.put("selectedDateTime", selectedDateTime);
            jsonObject.put("difference", difference);

            editor.putString(uniqueId, jsonObject.toString());
            editor.apply();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // JSON nesnesini kaydet

    }

    private List<JSONObject> getSavedData() {
        SharedPreferences sharedPreferences = getSharedPreferences("KiloData", MODE_PRIVATE);
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
    private double removeFromSharedPreferences(String uniqueId) {
        SharedPreferences sharedPreferences = getSharedPreferences("KiloData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        double kiloValue = 0.0;
        try {
            JSONObject jsonObject = new JSONObject(sharedPreferences.getString(uniqueId, ""));
            kiloValue = jsonObject.getDouble("kiloValue");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        editor.remove(uniqueId);
        editor.apply();
        return kiloValue;

    }


}