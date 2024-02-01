package com.example.pregnapp;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashBoard extends AppCompatActivity {

    String userMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        userMail = getIntent().getStringExtra("userMail");

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        Fragment initialFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("userMail", userMail);
        initialFragment.setArguments(bundle);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                  initialFragment).commit();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            item -> {
                Fragment selectedFragment;

                switch (item.getItemId()) {
                    case R.id.nav_home:
                        selectedFragment = new HomeFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("userMail", userMail);
                        Log.d("usermail bundle:", userMail);
                        selectedFragment.setArguments(bundle);
                        break;
                    case R.id.nav_forum:
                        selectedFragment = new Forum();
                        break;
                    case R.id.nav_tools:
                        selectedFragment = new Tools();
                        break;
                    case R.id.nav_profile:
                        selectedFragment = new Profile();
                        Bundle bundleP = new Bundle();
                        bundleP.putString("userMail", userMail);
                        Log.d("usermail bundle:", userMail);
                        selectedFragment.setArguments(bundleP);
                        break;
                    default:
                        return false;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        selectedFragment).commit();

                return true;
            };
}
