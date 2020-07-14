package com.example.otp_demo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SignUp_Activity extends AppCompatActivity {

    EditText mCountryCode, phoneNumField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_sign_up_);

        mCountryCode = findViewById(R.id.countryCode);
        phoneNumField = findViewById(R.id.num_textField);

    }

    public void verifyNumber(View view) {
        String countryCode = mCountryCode.getText().toString();
        String phoneNumber = phoneNumField.getText().toString();

        String complete_phonenumber = "+"+countryCode  + phoneNumber;

        if(countryCode.isEmpty() || phoneNumber.isEmpty()){
            mCountryCode.setError("Please input country code");
            phoneNumField.setError("Please input phone number");
        }

        else{
            Intent intent = new Intent(getApplicationContext(), Otp_Activity.class);
            intent.putExtra("phoneNo",complete_phonenumber);
            startActivity(intent);
        }
    }

    }

