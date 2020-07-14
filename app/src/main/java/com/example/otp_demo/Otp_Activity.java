package com.example.otp_demo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken;
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks;

import java.util.concurrent.TimeUnit;

public class Otp_Activity extends AppCompatActivity {

    Button verifyOtpNumber, resendOtpNumber;
    PinView pinInputBox;


    String verificationCodeBySystem;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(String s, ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            verificationCodeBySystem = s;
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(Otp_Activity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.rgb(1, 6, 31));
        setContentView(R.layout.activity_otp_);

        verifyOtpNumber = findViewById(R.id.verify_otp_number);
        resendOtpNumber = findViewById(R.id.resend_otp_number);
        pinInputBox = findViewById(R.id.otp_input_box);

        final String phoneNo = getIntent().getStringExtra("phoneNo");

        sendVerificationCodeToUser(phoneNo);

        verifyOtpNumber.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = pinInputBox.getText().toString();

                if (code.isEmpty() || code.length() < 6) {
                    pinInputBox.setError("Wrong OTP......");
                    pinInputBox.requestFocus();
                    return;
                }
                verifyCode(code);
            }
        });

//        resendOtpNumber.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }

    private void sendVerificationCodeToUser(String phoneNo) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                 phoneNo,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                TaskExecutors.MAIN_THREAD,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }

//    private void resendVerificationCodeToUser(String userResendPhoneNo){
//        PhoneAuthProvider.getInstance().verifyPhoneNumber(
//                "+234" +  userResendPhoneNo,        // Phone number to verify
//                60,                 // Timeout duration
//                TimeUnit.SECONDS,   // Unit of timeout
//                TaskExecutors.MAIN_THREAD,               // Activity (for callback binding)
//                mCallbacks);        // OnVerificationStateChangedCallbacks
//    }
    private void verifyCode(String codeByUser) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCodeBySystem, codeByUser);
        loginUserByCredentials(credential);
    }

    private void loginUserByCredentials(PhoneAuthCredential credential) {
        FirebaseAuth auth = FirebaseAuth.getInstance();

        auth.signInWithCredential(credential)
                .addOnCompleteListener(Otp_Activity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(getApplicationContext(), WelcomeUser.class);
                            intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        } else {
                            Toast.makeText(Otp_Activity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }

//    public void resendCodeToUser(View view) {
//        Intent intent = new Intent(Otp_Activity.this, WelcomeUser.class);
//        startActivity(intent);
//    }

//    public void openWelcomePage(View view) {
//        Intent intent = new Intent(Otp_Activity.this, WelcomeUser.class);
//        startActivity(intent);
//    }
}