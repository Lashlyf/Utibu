package com.example.hospital;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        AppCompatButton getInBtn = findViewById(R.id.button);
        getInBtn.setOnClickListener(v -> {
            Intent intent =new Intent(IntroActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }
}