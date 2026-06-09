package com.israt.healthtwin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class FitnessInfoActivity extends AppCompatActivity {
    //
    TextView fitness_link1;
    TextView fitness_link2;
    TextView fitness_link3;
    TextView fitness_link4;
    TextView fitness_link5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fitness_info);

        //tab buttons
        TextView btnSleep = findViewById(R.id.btn_sleep);
        TextView btnNutrition = findViewById(R.id.btn_nutrition);
        TextView btnMentalHealth = findViewById(R.id.btn_mental_health);
        TextView btnSmoking = findViewById(R.id.btn_smoking);
        TextView btnAlcohol = findViewById(R.id.btn_alcohol);

        // links
        fitness_link1 = findViewById(R.id.link1);
        fitness_link2 = findViewById(R.id.link2);
        fitness_link3 = findViewById(R.id.link3);
        fitness_link4 = findViewById(R.id.link4);
        fitness_link5 = findViewById(R.id.link5);


        // onClick Methods for the tab buttons
        btnSleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FitnessInfoActivity.this, SleepInfoActivity.class);
                startActivity(intent);
            }
        });

        btnNutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FitnessInfoActivity.this, NutritionInfoActivity.class);
                startActivity(intent);
            }
        });

        btnMentalHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FitnessInfoActivity.this, MentalHealthInfoActivity.class);
                startActivity(intent);
            }
        });

        btnSmoking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FitnessInfoActivity.this, SmokingInfoActivity.class);
                startActivity(intent);
            }
        });

        btnAlcohol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FitnessInfoActivity.this, AlcoholInfoActivity.class);
                startActivity(intent);
            }
        });


        // onClick Methods for the links
        fitness_link1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.mayoclinic.org/healthy-lifestyle/fitness/in-depth/fitness-training/art-20044792";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        fitness_link2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.heart.org/en/healthy-living/exercise-and-physical-activity/fitness-basics/aha-recs-for-physical-activity-in-adults";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        fitness_link3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.center4research.org/beginners-guide-developing-exercise-routine/";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        fitness_link4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.asphaltgreen.org/blog/8-exercises-to-add-to-your-2026-fitness-routine/";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        fitness_link5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.verywellfit.com/complete-beginners-guide-to-strength-training-1229585";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

    }
}