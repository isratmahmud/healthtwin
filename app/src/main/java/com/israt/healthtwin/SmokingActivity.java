package com.israt.healthtwin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SmokingActivity extends AppCompatActivity {

    private UserData loggedInUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_smoking);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.smokingXML), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(v.getPaddingStart() + systemBars.left, v.getPaddingTop()
                    + systemBars.top, v.getPaddingEnd()
                    + systemBars.right, v.getPaddingBottom() + systemBars.bottom);
            return insets;
        });

        Button btnUpdate = findViewById(R.id.btnUpdate);
        RadioGroup rgHabits = findViewById(R.id.rgHabits);
        loggedInUser = (UserData) getIntent().getSerializableExtra("USER_OBJECT");

        new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase db = AppDatabase.getDatabase(SmokingActivity.this);
                int userid = loggedInUser.id;
                LifestyleData dataList = db.userDao().getLifestyleByUserId(userid);

                if (dataList != null) {
                    int savedValue = dataList.smoke;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            updateRadioButtons(rgHabits, savedValue);
                        }
                    });
                }
            }
        }).start();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = rgHabits.getCheckedRadioButtonId();
                int smokingValue = getSelectedValue(selectedId);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        AppDatabase db = AppDatabase.getDatabase(SmokingActivity.this);
                        int userid = loggedInUser.id;
                        db.userDao().updateSmokingHabit(userid, smokingValue);

                        Intent intent = new Intent(SmokingActivity.this, Dashboard_Activity.class);
                        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("USER_OBJECT", loggedInUser);
                        startActivity(intent);
                    }
                }).start();
            }
        });

        findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SmokingActivity.this, Dashboard_Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOnBackPressedDispatcher().onBackPressed();
            }
        });

        findViewById(R.id.btnHeart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SmokingActivity.this, SimulationActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnInfo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SmokingActivity.this, SleepInfoActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnProfile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SmokingActivity.this, UserProfileUpdateActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private int getSelectedValue(int id) {
        if (id == R.id.rbNever) return 0;
        if (id == R.id.rbOccasionally) return 1;
        if (id == R.id.rb1_5C) return 2;
        if (id == R.id.rb6_10C) return 3;
        if (id == R.id.rb10_plusC) return 4;
        return 0;
    }

    private void updateRadioButtons(RadioGroup rg, int value) {
        switch (value) {
            case 0: rg.check(R.id.rbNever); break;
            case 1: rg.check(R.id.rbOccasionally); break;
            case 2: rg.check(R.id.rb1_5C); break;
            case 3: rg.check(R.id.rb6_10C); break;
            case 4: rg.check(R.id.rb10_plusC); break;
        }
    }
}