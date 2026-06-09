package com.israt.healthtwin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class NutritionInfoActivity extends AppCompatActivity {
    //
    TextView nutrition_link1;
    TextView nutrition_link2;
    TextView nutrition_link3;
    TextView nutrition_link4;
    TextView nutrition_link5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nutrition_info);

        //tab buttons
        TextView btnSleep = findViewById(R.id.btn_sleep);
        TextView btnFitness = findViewById(R.id.btn_fitness);
        TextView btnMentalHealth = findViewById(R.id.btn_mental_health);
        TextView btnSmoking = findViewById(R.id.btn_smoking);
        TextView btnAlcohol = findViewById(R.id.btn_alcohol);

        // links
        nutrition_link1 = findViewById(R.id.link1);
        nutrition_link2 = findViewById(R.id.link2);
        nutrition_link3 = findViewById(R.id.link3);
        nutrition_link4 = findViewById(R.id.link4);
        nutrition_link5 = findViewById(R.id.link5);


        // onClick Methods for the tab buttons
        btnSleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NutritionInfoActivity.this, SleepInfoActivity.class);
                startActivity(intent);
            }
        });

        btnFitness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NutritionInfoActivity.this, FitnessInfoActivity.class);
                startActivity(intent);
            }
        });

        btnMentalHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NutritionInfoActivity.this, MentalHealthInfoActivity.class);
                startActivity(intent);
            }
        });

        btnSmoking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NutritionInfoActivity.this, SmokingInfoActivity.class);
                startActivity(intent);
            }
        });

        btnAlcohol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NutritionInfoActivity.this, AlcoholInfoActivity.class);
                startActivity(intent);
            }
        });


        // onClick Methods for the links
        nutrition_link1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://nutritionsource.hsph.harvard.edu/healthy-eating-plate/";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        nutrition_link2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.dietaryguidelines.gov/";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        nutrition_link3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.nhs.uk/live-well/eat-well/how-to-eat-a-balanced-diet/eight-tips-for-healthy-eating/";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        nutrition_link4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.health.harvard.edu/diet-and-nutrition/8-simple-ways-to-reduce-ultra-processed-foods-in-your-diet";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        nutrition_link5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.heart.org/en/healthy-living/healthy-eating/eat-smart/nutrition-basics/understanding-food-nutrition-labels";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

    }
}