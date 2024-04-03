package com.example.hospital;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;


import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginActivity extends AppCompatActivity implements DatabaseConnectionTask.OnDatabaseConnectionListener {
    EditText lgnUsername, lgnPassword;
    TextView signupRedirect;
    AppCompatButton lgnButton;
    private JDCDemo jdcDemo;

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
                new DatabaseConnectionTask(jdcDemo, LoginActivity.this).execute();
            }
        });
    }

    @Override
    public void onDatabaseConnected(Connection connection) {
        String username = lgnUsername.getText().toString();
        String password = lgnPassword.getText().toString();

        try {
            // Execute a query to check if the username and password match
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            // Check if a matching record is found
            if (resultSet.next()) {
                // Authentication successful
                showToast("Login successful!");
                // Proceed to the next activity or perform further actions
            } else {
                // Authentication failed
                showToast("Invalid username or password!");
            }

            // Close the result set, statement, and connection
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            showToast("Error: " + e.getMessage());
        } finally {
            // Close the database connection
            jdcDemo.closeConnection(connection);
        }
    }

    @Override
    public void onDatabaseConnectionFailed() {
        // Handle the connection failure case
        showToast("Failed to connect to the database");
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}