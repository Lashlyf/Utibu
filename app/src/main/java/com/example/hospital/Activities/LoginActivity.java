package com.example.hospital.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;


import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hospital.R;

public class LoginActivity extends AppCompatActivity {
    EditText lgnUsername, lgnPassword;
    TextView signupRedirect;
    AppCompatButton lgnButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        jdcDemo = new JDCDemo(this);
        lgnUsername = findViewById(R.id.lgnusername);
        lgnPassword = findViewById(R.id.lgnpassword);
        lgnButton = findViewById(R.id.lgnButton);
        signupRedirect = findViewById(R.id.signUp);


        signupRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });


        lgnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create and execute the DatabaseConnectionTask
                Intent intent = new Intent(LoginActivity.this, OrderActivity.class);
                startActivity(intent);
            }
        });
    }

   }