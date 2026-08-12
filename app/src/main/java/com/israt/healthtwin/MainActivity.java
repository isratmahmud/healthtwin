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
            new Thread(() -> {
                AppDatabase database = AppDatabase.getDatabase(MainActivity.this);
                UserData loggedInUser;
                UserData loggedDefault;
                loggedInUser = database.userDao().getUserByUsername(inputUsername);
                loggedDefault = database.userDao().getUserByUsername("Jenny");

                runOnUiThread(() -> {
                    Intent  intent = new Intent(MainActivity.this, Dashboard_Activity.class);

                    if (!inputUsername.isEmpty() && !inputPassword.isEmpty()) {
                        //intent = new Intent(MainActivity.this, Dashboard_Activity.class);
                        intent.putExtra("USER_OBJECT", loggedInUser);
                        //startActivity(intent);
                        //finish(); // Close MainActivity so user can't go back to login
                    }
                    else if(!inputUsername.isEmpty()){
                        //intent = new Intent(MainActivity.this, Dashboard_Activity.class);
                        intent.putExtra("USER_OBJECT", loggedInUser);
                        //startActivity(intent);
                        //finish(); // Close MainActivity so user can't go back to login
                    }

                    else {
                        //Toast.makeText(MainActivity.this, "Please enter username and password", Toast.LENGTH_SHORT).show();
                        //intent = new Intent(MainActivity.this, Dashboard_Activity.class);
                        intent.putExtra("USER_OBJECT", loggedDefault);
                        //startActivity(intent);
                        //finish(); // Close MainActivity so user can't go back to login

                    }
                    startActivity(intent);
                    finish();


                });
            }).start();
        });




        // cancel button only for test purpose
        btnCancel.setOnClickListener(v -> {
            username.setText("");
            password.setText("");
            Intent intent = new Intent(MainActivity.this, Dashboard_Activity.class);
            //intent.putExtra("USER_OBJECT", loggedInUser);
            startActivity(intent);

        });

        // create account link
        //createAccount.setOnClickListener(v -> Toast.makeText(MainActivity.this, "Registration feature coming soon!", Toast.LENGTH_SHORT).show());
        createAccount.setOnClickListener(v -> {
            username.setText("");
            password.setText("");
            Intent intent = new Intent(MainActivity.this, UserProfileCreateActivity.class);
            //intent.putExtra("USER_OBJECT", loggedInUser);
            startActivity(intent);

        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase database = AppDatabase.getDatabase(MainActivity.this);
                UserData existingUser = null;
                existingUser = database.userDao().getUserByUsername("Jenny");

                if (existingUser == null) {
                    UserData dummyUser = new UserData(0, "Jenny Doe", "5/12/1998", "Jenny","", "jenny@xyz.de", "Female", 165.0, 58.5);
                    long insertedUserId = database.userDao().insertUser(dummyUser);

                    LifestyleData dummyLifestyle = new LifestyleData(0, 1, 0, 3);
                    database.userDao().insertLifestyle(dummyLifestyle);

                    android.util.Log.d("ROOM_DATABASE", "New User created!");
                } else {
                    android.util.Log.d("ROOM_DATABASE", "This user already exists in Database!");
                }
            }
        }).start();

            }

    // Test line

}