package com.example.hospital;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    private String[] medicalConditions;
    private String selectedCondition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText etDateOfBirth = findViewById(R.id.etDateOfBirth);
        Spinner spGender = findViewById(R.id.spGender);
        EditText etFullName = findViewById(R.id.etFullName);
        EditText etAddress = findViewById(R.id.etAddress);
        Spinner spMedicalCondition = findViewById(R.id.spMedicalCondition);
        EditText etMedicationDetails = findViewById(R.id.etMedicationDetails);
        EditText etEmergencyContactName = findViewById(R.id.etEmergencyContactName);
        EditText etEmergencyContactPhone = findViewById(R.id.etEmergencyContactPhone);
        AppCompatButton btnSubmit = findViewById(R.id.btnSubmit);

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

        btnSubmit.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, OrderActivity.class);
            intent.putExtra("selectedCondition", selectedCondition);
            startActivity(intent);
        });
    }
}