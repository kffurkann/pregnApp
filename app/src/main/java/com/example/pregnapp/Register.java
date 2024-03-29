package com.example.pregnapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Register extends AppCompatActivity {
    private Button buttonDate;
    private EditText editTextUserMail;
    private EditText editTextUsername;
    private EditText editTextUserPassword;
    private EditText editTextUserWeight;
    private Button buttonRegister;
    private Calendar calendar;
    private String selectedDate = "";
    private TextView goLogin;
    private boolean isPasswordVisible = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_form);

        buttonDate = findViewById(R.id.buttonDate);
        editTextUserMail = findViewById(R.id.inputMail);
        editTextUsername = findViewById(R.id.inputUsername);
        editTextUserPassword = findViewById(R.id.inputPassword);
        editTextUserWeight = findViewById(R.id.inputWeight);
        buttonRegister = findViewById(R.id.buttonSignUp);
        goLogin = findViewById(R.id.textPassToLogin);
        TextView textViewMain = findViewById(R.id.textViewMain);


        calendar = Calendar.getInstance();

        setupPasswordVisibilityToggle();

        textViewMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(); // Yeni ekranı göster
            }
        });

        goLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, LoginPage.class);
                startActivity(intent);
            }
        });

        buttonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRegistrationRequest();
            }
        });
    }

    private void setupPasswordVisibilityToggle() {
        editTextUserPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.password_icon, 0);
        editTextUserPassword.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= (editTextUserPassword.getRight() - editTextUserPassword.getCompoundDrawables()[2].getBounds().width())) {
                    // Şifre görünürlüğünü değiştir
                    togglePasswordVisibility();
                    return true;
                }
            }
            return false;
        });
    }

    private void togglePasswordVisibility() {
        if (isPasswordVisible) {
            // Şifreyi gizle
            editTextUserPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            editTextUserPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.eye_off, 0);
        } else {
            // Şifreyi göster
            editTextUserPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            editTextUserPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.eye_svg, 0);
        }
        editTextUserPassword.setSelection(editTextUserPassword.getText().length());
        isPasswordVisible = !isPasswordVisible;
    }

    private void showPopup() {

        Dialog popupDialog = new Dialog(this);
        popupDialog.setContentView(R.layout.activity_popup);

        ImageView imageViewClose = popupDialog.findViewById(R.id.imageViewClose);
        imageViewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupDialog.dismiss();
            }
        });

        popupDialog.show();
    }



    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, monthOfYear);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                        selectedDate = sdf.format(calendar.getTime());
                        buttonDate.setText(selectedDate);
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );

        datePickerDialog.show();
    }

    private void sendRegistrationRequest() {
        String userMail = editTextUserMail.getText().toString();
        String userName = editTextUsername.getText().toString();
        String userPassword = editTextUserPassword.getText().toString();
        int userWeight = Integer.parseInt(editTextUserWeight.getText().toString());

        OkHttpClient client = new OkHttpClient();

        String apiUrl = "http://10.0.2.2:5274/api/User/register";
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
        String formattedDate = sdf.format(calendar.getTime());

        String jsonBody = "{\"userMail\":\"" + userMail + "\",\"userName\":\"" + userName + "\",\"userPassword\":\"" + userPassword + "\",\"reglDate\":\"" + formattedDate + "\",\"userWeight\":" + userWeight + "}";

        RequestBody requestBody = RequestBody.create(jsonBody, JSON);

        Request request = new Request.Builder()
                .url(apiUrl)
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    Log.d("başarılı", responseBody);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(Register.this, DashBoard.class);
                            intent.putExtra("userMail", userMail);
                            startActivity(intent);
                            finish();
                        }
                    });
                } else {
                    String responseBody = response.body().string();
                    Log.d("başarısız", responseBody);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject jsonObject = new JSONObject(responseBody);
                                String errorMessage = jsonObject.getString("message");
                                Toast.makeText(Register.this, errorMessage, Toast.LENGTH_LONG).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(Register.this, "Hata mesajı ayrıştırılamadı", Toast.LENGTH_LONG).show();
                            }

                        }
                    });
                }
            }
        });
    }


}
