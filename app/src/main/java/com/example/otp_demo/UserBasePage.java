package com.example.otp_demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener;

public class UserBasePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_base_page);

        getWindow().setStatusBarColor(Color.rgb(255,255,255));

        BottomNavigationView navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(navSelected);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragments_container, new TagsFragment()).commit();
    }

private BottomNavigationView.OnNavigationItemSelectedListener navSelected = new OnNavigationItemSelectedListener() {
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment selectedFragment = null;

        switch (menuItem.getItemId()){
            case R.id.tags:
                selectedFragment = new TagsFragment();
                break;
            case R.id.trending:
                selectedFragment = new TrendingFragment();
                break;
            case R.id.explore:
                selectedFragment = new ExploreFragment();
                break;
                case R.id.saved:
                selectedFragment = new SavedFragment();
                break;
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fragments_container, selectedFragment).commit();
        return true;
    }
};
}