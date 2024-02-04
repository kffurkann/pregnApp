package com.example.pregnapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import androidx.fragment.app.Fragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Profile extends Fragment {

    private String userMailP;
    private String userNameP;
    private String reglDateP;
    private String weightUser;
    private TextView nameP;
    private TextView weightP;
    private TextView reglDateText;
    private EditText changeName;
    private EditText changeWeight;
    private Calendar calendarP;
    private Button buttonChangeRegl, saveDataProfil, deleteAccountButton;
    private DatePickerDialog datePickerDialog;
    private String selectedDate = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Initialize views
        nameP = view.findViewById(R.id.text_profil_adiniz);
        weightP = view.findViewById(R.id.text_profil_kilonuz);
        reglDateText = view.findViewById(R.id.text_profil_son_adet_tarihi);
        changeName = view.findViewById(R.id.input_profil_adiniz);
        changeWeight = view.findViewById(R.id.input_profil_kilonuz);
        buttonChangeRegl = view.findViewById(R.id.buttonReglDateP);
        saveDataProfil = view.findViewById(R.id.saveDataProfil);
        deleteAccountButton = view.findViewById(R.id.deleteAccountProfil);

        Bundle bundle = getArguments();
        userMailP = bundle.getString("userMail");
        Log.d("userMail DenemeP:", userMailP);

        calendarP = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(
                getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendarP.set(Calendar.YEAR, year);
                calendarP.set(Calendar.MONTH, month);
                calendarP.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDateText();
            }
        }, calendarP.get(Calendar.YEAR), calendarP.get(Calendar.MONTH), calendarP.get(Calendar.DAY_OF_MONTH));

        buttonChangeRegl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        saveDataProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUser();
            }
        });

        deleteAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteUser();
            }
        });


        getUserData();
        return view;
    }

    private void getUserData() {
        OkHttpClient client = new OkHttpClient();
        String url = "http://10.0.2.2:5274/api/User/GetUser?mail=" + userMailP;

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

                        userNameP = jsonObject.getString("userName");
                        reglDateP = jsonObject.getString("reglDate");
                        weightUser = jsonObject.getString("userWeight");

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                nameP.setText(userNameP);
                                weightP.setText(String.valueOf(weightUser));
                                reglDateText.setText(formatDate(reglDateP));
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

    private void updateUser() {
        String updatedName = changeName.getText().toString().isEmpty() ? userNameP : changeName.getText().toString();
        String updatedWeight = changeWeight.getText().toString().isEmpty() ? weightUser : changeWeight.getText().toString();
        String updatedReglDate = selectedDate.isEmpty() ? reglDateP : convertCalendarToDateTime(calendarP);

        OkHttpClient client = new OkHttpClient();
        String url = "http://10.0.2.2:5274/api/User/UpdateUser";

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("userMail", userMailP);
            jsonBody.put("userName", updatedName);
            jsonBody.put("reglDate", updatedReglDate);
            jsonBody.put("userWeight", Integer.parseInt(updatedWeight));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(jsonBody.toString(), JSON);

        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .build();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        Log.d("UpdateUserAPI", "User updated successfully");
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getContext(), "Profil bilgileriniz güncellendi", Toast.LENGTH_LONG).show();
                            }
                        });
                    } else {
                        Log.e("UpdateUserAPI", "Response not successful");
                    }
                } catch (IOException e) {
                    Log.e("UpdateUserAPI", "Error updating user", e);
                }
            }
        }).start();
    }

    private void deleteUser() {
        OkHttpClient client = new OkHttpClient();
        String url = "http://10.0.2.2:5274/api/User/" + userMailP;

        Request request = new Request.Builder()
                .url(url)
                .delete()
                .build();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        Log.d("DeleteUserAPI", "User deleted successfully");

                        // UI thread üzerinde çalıştır
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                navigateToLoginScreen();
                            }
                        });
                    } else {
                        Log.e("DeleteUserAPI", "Response not successful");
                    }
                } catch (IOException e) {
                    Log.e("DeleteUserAPI", "Error deleting user", e);
                }
            }
        }).start();
    }



    private String formatDate(String rawDateTime) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault());
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date date = inputFormat.parse(rawDateTime);
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    private void updateDateText() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        selectedDate = format.format(calendarP.getTime());
        reglDateText.setText(selectedDate);
    }

    private String convertCalendarToDateTime(Calendar calendar) {
        Date date = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault());
        return dateFormat.format(date);
    }
    private void navigateToLoginScreen() {
        Intent intent = new Intent(getActivity(), LoginPage.class);
        startActivity(intent);
        getActivity().finish();
    }

}
