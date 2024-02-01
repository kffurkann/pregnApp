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
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeFragment extends Fragment {

    private String userMail;
    private Button buttonMakale;
    TextView firstWeek;
    TextView secondWeek;
    TextView thirdWeek;
    TextView fifthWeek;
    TextView sixWeek;
    TextView heightT;
    TextView weightT;
    TextView fruitT;
    TextView fullName;
    int week;
    double height;
    double weight;
    String fruit;
    private String articleTitle; //makalelerin başlığı bunu bastırabilirsininiz
    private String articleContent;
    private String userName;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        firstWeek=view.findViewById(R.id.firstWeek);
        secondWeek=view.findViewById(R.id.secondWeek);
        thirdWeek=view.findViewById(R.id.thirdWeek);
        fifthWeek=view.findViewById(R.id.fifthWeek);
        sixWeek=view.findViewById(R.id.sixWeek);
        heightT=view.findViewById(R.id.height);
        weightT=view.findViewById(R.id.width);
        fruitT=view.findViewById(R.id.fruitT);
        buttonMakale=view.findViewById(R.id.button_makale);
        fullName=view.findViewById(R.id.fullName);


        buttonMakale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Makale.class);
                // Put the title and content as extras in the intent
                intent.putExtra("articleTitle", articleTitle);
                intent.putExtra("articleContent", articleContent);
                startActivity(intent);
            }
        });

        Bundle bundle = getArguments();
        userMail = bundle.getString("userMail");
        Log.d("userMail Deneme:", userMail);
        fetchBabyData();
        getUserData();
        getContent();
        updateTextViews();
        return view;
    }

    private void getContent() {
        OkHttpClient client = new OkHttpClient();
        String url = "http://10.0.2.2:5274/api/Article";

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

                        articleTitle = jsonObject.getString("title");
                        articleContent = jsonObject.getString("content");

                    } else {
                        Log.e("API Error", "Response not successful");
                    }
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void fetchBabyData() {
        OkHttpClient client = new OkHttpClient();
        String url = "http://10.0.2.2:5274/api/Baby/GetBabyDatas?Usermail=" + userMail;

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

                        week = jsonObject.getInt("week");
                        height = jsonObject.getDouble("height");
                        weight = jsonObject.getDouble("weight");
                        fruit = jsonObject.getString("fruit");

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                updateTextViews();
                            }
                        });

                    } else {
                        Log.e("API Error", "Response not successful");
                    }
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    private void updateTextViews() {
        firstWeek.setText(String.valueOf(week - 1));
        secondWeek.setText(String.valueOf(week - 2));
        thirdWeek.setText(String.valueOf(week));
        fifthWeek.setText(String.valueOf(week + 1));
        sixWeek.setText(String.valueOf(week + 2));
        heightT.setText(String.valueOf(height + "cm"));
        weightT.setText(String.valueOf(weight + "kg"));
        fruitT.setText(String.valueOf("Bebeğin " + fruit +  " meyvesi büyüklüğünde"));
        fullName.setText(String.valueOf("Merhaba " + userName));
    }

    private void getUserData() {
        OkHttpClient client = new OkHttpClient();
        String url = "http://10.0.2.2:5274/api/User/GetUser?mail=" + userMail;

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

                        userName = jsonObject.getString("userName");

                    } else {
                        Log.e("API Error", "Response not successful");
                    }
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}