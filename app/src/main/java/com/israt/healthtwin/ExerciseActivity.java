package com.israt.healthtwin;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ExerciseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_exercise);

        findViewById(R.id.btnIcon1).setOnClickListener(v -> finish());

        findViewById(R.id.btnIcon2).setOnClickListener(v -> {
            Intent intent = new Intent(ExerciseActivity.this, Dashboard_Activity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }
}