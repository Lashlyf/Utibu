package com.example.hospital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class SignUpActivity extends AppCompatActivity {
    private String[] medicalConditions;
    private String selectedCondition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        // Initialize views
        EditText etFullName = findViewById(R.id.etFullName);
        EditText etUserName = findViewById(R.id.etUserName);
        EditText etDateOfBirth = findViewById(R.id.etDateOfBirth);
        Spinner spGender = findViewById(R.id.spGender);
        EditText etEmail = findViewById(R.id.etEmail);
        EditText etPhoneNumber = findViewById(R.id.etPhoneNumber);
        EditText etAddress = findViewById(R.id.etAddress);
        Spinner spMedicalCondition = findViewById(R.id.spMedicalCondition);
        EditText etMedicationDetails = findViewById(R.id.etMedicationDetails);
        EditText etEmergencyContactName = findViewById(R.id.etEmergencyContactName);
        EditText etEmergencyContactPhone = findViewById(R.id.etEmergencyContactPhone);
        EditText etPassword = findViewById(R.id.etPassword);
        Button btnSignUp = findViewById(R.id.btnSignUp);

        // Get medical conditions from string array
        medicalConditions = getResources().getStringArray(R.array.medical_condition_array);

        // Set up medical condition spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, medicalConditions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spMedicalCondition.setAdapter(adapter);

        spMedicalCondition.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCondition = medicalConditions[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        btnSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
            intent.putExtra("selectedCondition", selectedCondition);
            startActivity(intent);
        });
    }
}










