package com.example.hospital.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hospital.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText emailEditText, passwordEditText, usernameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        auth = FirebaseAuth.getInstance();
        emailEditText = findViewById(R.id.etEmail);
        passwordEditText = findViewById(R.id.etPassword);
        usernameEditText = findViewById(R.id.etUserName);

        findViewById(R.id.btn).setOnClickListener(v -> signUpUser());
    }

    private void signUpUser() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String username = usernameEditText.getText().toString().trim();

        if (!email.isEmpty() && !password.isEmpty() && !username.isEmpty()) {
            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // User creation successful, update the display name
                                FirebaseUser user = auth.getCurrentUser();
                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(username)
                                        .build();

                                assert user != null;
                                user.updateProfile(profileUpdates)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    // Display name updated, move to the main activity
                                                    startActivity(new Intent(SignUpActivity.this, OrderActivity.class));
                                                    finish();
                                                } else {
                                                    // Failed to update display name
                                                    Toast.makeText(SignUpActivity.this, "Failed to update display name.", Toast.LENGTH_SHORT).show();
                                                }
                                             }
                                        });
                            } else {
                                // User creation failed, display an error message
                                Toast.makeText(SignUpActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else {
            Toast.makeText(this, "Please enter email, password, and username.", Toast.LENGTH_SHORT).show();
        }
    }
}
