package com.example.otp_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

public class WelcomeUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.rgb(255, 255, 255));
        setContentView(R.layout.activity_welcome_user);
    }

    public void openLogoutPage(View view) {
        Intent intent = new Intent(WelcomeUser.this, Logout_page.class);
        startActivity(intent);
    }

    public void openUserBase(View view) {
        Intent intent = new Intent(WelcomeUser.this, UserBasePage.class);
        startActivity(intent);
    }
}