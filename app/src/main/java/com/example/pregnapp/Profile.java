package com.example.pregnapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Profile extends Fragment {

    private String userMailP;
    private String userNameP;
    private TextView userNameArea;
    private TextView lastReglDate;
    private Button editMailButton;
    private Button deleteAccountButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Initialize views
        userNameArea = view.findViewById(R.id.userNameArea);
        lastReglDate = view.findViewById(R.id.lastReglDate);
        editMailButton = view.findViewById(R.id.editMail);
        deleteAccountButton = view.findViewById(R.id.deleteAccount);

        Bundle bundle = getArguments();
        userMailP = bundle.getString("userMail");
        Log.d("userMail DenemeP:", userMailP);

        getUserData();

        editMailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start EditMail activity
                Intent intent = new Intent(getActivity(), EditMail.class);
                intent.putExtra("userMail", userMailP);
                startActivity(intent);
            }
        });

        return view;
    }

    private void getUserData() {
        OkHttpClient client = new OkHttpClient();
        String url = "http://10.0.2.2:5274/api/User/GetUser?mail=" + userMailP;

        Request request = new Request.Builder()
                .url(url)
                .build();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful() && response.body() != null) {
                        String responseData = response.body().string();
                        JSONObject jsonObject = new JSONObject(responseData);

                        userNameP = jsonObject.getString("userName");
                        String rawReglDate = jsonObject.getString("reglDate");
                        String formattedReglDate = formatDateTime(rawReglDate);

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                userNameArea.setText(userNameP);
                                lastReglDate.setText(formattedReglDate);
                            }
                        });

                    } else {
                        Log.e("API Error", "Response not successful");
                    }
                } catch (IOException | JSONException | ParseException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private String formatDateTime(String rawDateTime) throws ParseException {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        Date date = inputFormat.parse(rawDateTime);
        return outputFormat.format(date);
    }
}
