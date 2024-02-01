package com.example.pregnapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;

public class EditMail extends AppCompatActivity {

    private EditText editUserNameEditText;
    private Button changeInfosButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_mail);

        /*editUserNameEditText = findViewById(R.id.editTextUsername);
        changeInfosButton = findViewById(R.id.changeInfos);

        String userMail = getIntent().getStringExtra("userMail");

        changeInfosButton.setOnClickListener(view -> {
            String newUsername = editUserNameEditText.getText().toString();

            updateUserInfo(userMail, newUsername);
        });
    }

    private void updateUserInfo(String originalMail, String newUsername) {
        new Thread(() -> {
            try {
                OkHttpClient client = new OkHttpClient();
                String url = "http://10.0.2.2:5274/api/User/" + originalMail;

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("userMail", originalMail);
                jsonObject.put("userName", newUsername);

                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                RequestBody body = RequestBody.create(JSON, jsonObject.toString());

                Request request = new Request.Builder()
                        .url(url)
                        .put(body)
                        .build();


                Response response = client.newCall(request).execute();

                if (response.isSuccessful()) {
                    Intent intent = new Intent(EditMail.this, LoginPage.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else {

                }
            } catch (Exception e) {
                e.printStackTrace();

            }
        }).start();*/
    }
}
