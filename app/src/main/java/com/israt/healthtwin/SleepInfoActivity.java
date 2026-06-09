package com.israt.healthtwin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class SleepInfoActivity extends AppCompatActivity {
//
    TextView sleep_link1;
    TextView sleep_link2;
    TextView sleep_link3;
    TextView sleep_link4;
    TextView sleep_link5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sleep_info);

        //tab buttons
        TextView btnFitness = findViewById(R.id.btn_fitness);
        TextView btnNutrition = findViewById(R.id.btn_nutrition);
        TextView btnMentalHealth = findViewById(R.id.btn_mental_health);
        TextView btnSmoking = findViewById(R.id.btn_smoking);
        TextView btnAlcohol = findViewById(R.id.btn_alcohol);

        // links
        sleep_link1 = findViewById(R.id.link1);
        sleep_link2 = findViewById(R.id.link2);
        sleep_link3 = findViewById(R.id.link3);
        sleep_link4 = findViewById(R.id.link4);
        sleep_link5 = findViewById(R.id.link5);


        // onClick Methods for the tab buttons
        btnFitness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SleepInfoActivity.this, FitnessInfoActivity.class);
                startActivity(intent);
            }
        });

        btnNutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SleepInfoActivity.this, NutritionInfoActivity.class);
                startActivity(intent);
            }
        });

        btnMentalHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SleepInfoActivity.this, MentalHealthInfoActivity.class);
                startActivity(intent);
            }
        });

        btnSmoking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SleepInfoActivity.this, SmokingInfoActivity.class);
                startActivity(intent);
            }
        });

        btnAlcohol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SleepInfoActivity.this, AlcoholInfoActivity.class);
                startActivity(intent);
            }
        });


        // onClick Methods for the links
        sleep_link1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.mayoclinic.org/healthy-lifestyle/adult-health/in-depth/sleep/art-20048379";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        sleep_link2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.sleepfoundation.org/sleep-hygiene";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        sleep_link3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.nhs.uk/every-mind-matters/mental-wellbeing-tips/how-to-fall-asleep-faster-and-sleep-better/";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        sleep_link4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.webmd.com/sleep-disorders/ss/slideshow-sleep-tips";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        sleep_link5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://timesofindia.indiatimes.com/life-style/health-fitness/health-news/harvard-sleep-trick-why-socks-help-you-fall-asleep-faster-and-improve-your-sleep-quality/articleshow/123991760.cms";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

    }
}