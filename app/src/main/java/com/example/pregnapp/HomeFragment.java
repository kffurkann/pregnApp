package com.example.pregnapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeFragment extends Fragment {

    ImageView firstTab;
    ImageView secondTab;
    ImageView thirdTab;
    ImageView fourthTab;
    private String userMail;
    TextView textView_14;
    TextView textView_15;
    TextView firstWeek;
    TextView secondWeek;
    TextView thirdWeek;
    TextView fifthWeek;
    TextView sixWeek;
    TextView heightT;
    TextView weightT;
    TextView fruitT;
    TextView fullName;
    RelativeLayout relativeLayoutMakale;
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
        fullName=view.findViewById(R.id.fullName);
        textView_14=view.findViewById(R.id.textView14);
        textView_15=view.findViewById(R.id.textView15);
        relativeLayoutMakale=view.findViewById(R.id.relativeMakale1);
        firstTab=view.findViewById(R.id.imageView5);
        secondTab=view.findViewById(R.id.imageView6);
        thirdTab=view.findViewById(R.id.imageView7);
        fourthTab=view.findViewById(R.id.imageView4);



        relativeLayoutMakale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Makale.class);
                intent.putExtra("articleTitle", articleTitle);
                intent.putExtra("articleContent", articleContent);
                startActivity(intent);
            }
        });

        firstTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Gidalar.class);
                startActivity(intent);
            }
        });

        secondTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TekmeSayar.class);
                startActivity(intent);
            }
        });

        thirdTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BebekIsimleri.class);
                startActivity(intent);
            }
        });

        fourthTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BugunNeAsersem.class);
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

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                updateArticleViews();
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
        weightT.setText(String.valueOf(weight + "gr"));
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

    private void updateArticleViews() {
        textView_14.setText(articleTitle);

        if (articleContent != null && articleContent.length() > 100) {
            String shortenedContent = articleContent.substring(0, 100) + "...";
            textView_15.setText(shortenedContent);
        } else {
            textView_15.setText(articleContent);
        }
    }


}