package com.israt.healthtwin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView; // Wichtig für das ImageView

import androidx.appcompat.app.AppCompatActivity; // Nur noch einmal importiert


public class Dashboard_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 1. Hier wird dein Dashboard-Layout geladen
        setContentView(R.layout.dashboard);

        // 2. Name aus dem Intent holen und anzeigen
        String username = getIntent().getStringExtra("USERNAME");
        TextView greetingText = findViewById(R.id.textView);
        if (username != null && !username.isEmpty()) {
            greetingText.setText(getString(R.string.greeting, username));
        }

        // 3. Finde das Diagramm-Element auf deinem Dashboard
        View dashboardChart = findViewById(R.id.bmi_dashboard);

        // Klick-Event für das Diagramm
        dashboardChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard_Activity.this, bmi_data.class);
                startActivity(intent);
            }
        });

        // 4. "Edit" Buttons
        findViewById(R.id.edit_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard_Activity.this, ExerciseActivity.class));
            }
        });

        findViewById(R.id.edit_smoking).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard_Activity.this, SmokingActivity.class));
            }
        });

        findViewById(R.id.edit_drinking).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard_Activity.this, DrinkingActivity.class));
            }
        });

        // 5. Navigation Bar Icons
        findViewById(R.id.btnIcon1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Back
            }
        });

        findViewById(R.id.btnIcon2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Already on Dashboard/Home
            }
        });

        ImageView btnIcon3 = findViewById(R.id.btnIcon3);
        btnIcon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Öffnet die Simulation-Klasse aus dem Dashboard heraus
                android.content.Intent intent = new android.content.Intent(Dashboard_Activity.this, Simulation.class);
                startActivity(intent);
            }
        });
    }
}