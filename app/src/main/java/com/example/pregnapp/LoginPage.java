package com.example.pregnapp;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginPage extends AppCompatActivity {
    private EditText loginMail;
    private EditText loginPassword;
    private TextView goRegister;
    Button buttonLogin;

    private boolean isPasswordVisible = false;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        loginMail = findViewById(R.id.inputLoginMail);
        loginPassword = findViewById(R.id.inputLoginPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        goRegister = findViewById(R.id.textPassToSignUp);

        setupPasswordVisibilityToggle();
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

        Log.d("LoginRequest", "E-mail: " + userMail);
        Log.d("LoginRequest", "Password: [protected]");

        OkHttpClient client = new OkHttpClient();

        String apiUrl = "http://10.0.2.2:5274/api/User/login";
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        String jsonBody = "{\"email\":\"" + userMail + "\",\"şifre\":\"" + userPassword + "\"}";
        RequestBody requestBody = RequestBody.create(jsonBody, JSON);

        Request request = new Request.Builder()
                .url(apiUrl)
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("LoginRequest", "HTTP request failed: " + e.getMessage());
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
                    Log.d("LoginRequest", "Response successful: " + responseBody);

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
                    Log.d("LoginRequest", "Response unsuccessful: " + responseBody);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject jsonObject = new JSONObject(responseBody);
                                String errorMessage = jsonObject.getString("message");
                                Toast.makeText(LoginPage.this, errorMessage, Toast.LENGTH_LONG).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(LoginPage.this, "Hata mesajı ayrıştırılamadı", Toast.LENGTH_LONG).show();
                            }

                        }
                    });
                }
            }
        });
    }
    private void setupPasswordVisibilityToggle() {
        loginPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.password_icon, 0);
        loginPassword.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= (loginPassword.getRight() - loginPassword.getCompoundDrawables()[2].getBounds().width())) {
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
            loginPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            loginPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.eye_off, 0);
        } else {
            // Şifreyi göster
            loginPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            loginPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.eye_svg, 0);
        }
        loginPassword.setSelection(loginPassword.getText().length());
        isPasswordVisible = !isPasswordVisible;
    }
}
