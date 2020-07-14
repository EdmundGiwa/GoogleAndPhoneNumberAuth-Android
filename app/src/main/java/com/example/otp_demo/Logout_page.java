package com.example.otp_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Logout_page extends AppCompatActivity {
Button logoutButton;

private FirebaseAuth mAuth;
private FirebaseUser mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout_page);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        getWindow().setLayout((int)(width * .8),(int)(height * .7));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y=-20;

        getWindow().setAttributes(params);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        logoutButton = findViewById(R.id.logOut);

        logoutButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                mAuth.signOut();
                sendUserToLogin();
            }
        });
    }

    public void stayOnWelcomePage(View view) {
        onBackPressed();
    }

  private void sendUserToLogin() {
        Intent loginIntent = new Intent(Logout_page.this, MainActivity.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginIntent);
        finish();
    }
}