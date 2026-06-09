package com.israt.healthtwin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    Button btnLogin, btnCancel;
    TextView createAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.Username);
        password = findViewById(R.id.Password);
        btnLogin = findViewById(R.id.btnLogin);
        btnCancel = findViewById(R.id.btnCancel);
        createAccount = findViewById(R.id.CreateAccount);

        // login
        btnLogin.setOnClickListener(v -> {
            String inputUsername = username.getText().toString();
            String inputPassword = password.getText().toString();

            // Simple validation and navigation to Dashboard
            if (!inputUsername.isEmpty() && !inputPassword.isEmpty()) {
                Intent intent = new Intent(MainActivity.this, Dashboard_Activity.class);
                intent.putExtra("USERNAME", inputUsername);
                startActivity(intent);
                finish(); // Close MainActivity so user can't go back to login
            } else {
                Toast.makeText(MainActivity.this, "Please enter username and password", Toast.LENGTH_SHORT).show();
            }
        });

        // cancel
        btnCancel.setOnClickListener(v -> {
            username.setText("");
            password.setText("");
            Intent intent = new Intent(MainActivity.this, SleepInfoActivity.class);
            startActivity(intent);

        });

        // create account link
        createAccount.setOnClickListener(v -> Toast.makeText(MainActivity.this, "Registration feature coming soon!", Toast.LENGTH_SHORT).show());
    }

    // Test line
}