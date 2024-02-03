package com.example.pregnapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginPage extends AppCompatActivity {
    private EditText loginMail;
    private EditText loginPassword;
    private Button buttonLogin, buttonalfa;
    private TextView goRegister;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        loginMail = findViewById(R.id.loginMail);
        loginPassword = findViewById(R.id.loginPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        goRegister = findViewById(R.id.goRegister);
        buttonalfa=findViewById(R.id.buttonalfa);

        buttonalfa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginPage.this,DashBoard.class));
            }
        });


        goRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginPage.this, Register.class);
                startActivity(intent);
            }
        });


       buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginRequest();
            }
        });
    }
    private void loginRequest() {
        String userMail = loginMail.getText().toString();
        String userPassword = loginPassword.getText().toString();
        Log.d("usermail", userMail);
        Log.d("userpassword", userPassword);

        OkHttpClient client = new OkHttpClient();

        String apiUrl = "http://10.0.2.2:5274/api/User/login" + "?mail=" + userMail + "&şifre=" + userPassword;
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        String jsonBody = "{\"mail\":\"" + userMail + "\",\"şifre\":\"" + userPassword + "}";

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
                            Intent intent = new Intent(LoginPage.this, DashBoard.class);
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
                        }
                    });
                }
            }
        });
    }
}
