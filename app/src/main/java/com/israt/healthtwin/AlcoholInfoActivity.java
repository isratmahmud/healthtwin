package com.israt.healthtwin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class AlcoholInfoActivity extends AppCompatActivity {
    //
    TextView alcohol_link1;
    TextView alcohol_link2;
    TextView alcohol_link3;
    TextView alcohol_link4;
    TextView alcohol_link5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_alcohol_info);

        //tab buttons
        TextView btnSleep = findViewById(R.id.btn_sleep);
        TextView btnFitness = findViewById(R.id.btn_fitness);
        TextView btnNutrition = findViewById(R.id.btn_nutrition);
        TextView btnMentalHealth = findViewById(R.id.btn_mental_health);
        TextView btnSmoking = findViewById(R.id.btn_smoking);

        // links
        alcohol_link1 = findViewById(R.id.link1);
        alcohol_link2 = findViewById(R.id.link2);
        alcohol_link3 = findViewById(R.id.link3);
        alcohol_link4 = findViewById(R.id.link4);
        alcohol_link5 = findViewById(R.id.link5);


        // onClick Methods for the tab buttons
        btnSleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlcoholInfoActivity.this, SleepInfoActivity.class);
                startActivity(intent);
            }
        });

        btnFitness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlcoholInfoActivity.this, FitnessInfoActivity.class);
                startActivity(intent);
            }
        });

        btnNutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlcoholInfoActivity.this, NutritionInfoActivity.class);
                startActivity(intent);
            }
        });

        btnMentalHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlcoholInfoActivity.this, MentalHealthInfoActivity.class);
                startActivity(intent);
            }
        });

        btnSmoking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlcoholInfoActivity.this, SmokingInfoActivity.class);
                startActivity(intent);
            }
        });


        // onClick Methods for the links
        alcohol_link1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.mayoclinic.org/healthy-lifestyle/adult-health/in-depth/sleep/art-20048379";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        alcohol_link2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.sleepfoundation.org/sleep-hygiene";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        alcohol_link3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.nhs.uk/every-mind-matters/mental-wellbeing-tips/how-to-fall-asleep-faster-and-sleep-better/";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        alcohol_link4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.webmd.com/sleep-disorders/ss/slideshow-sleep-tips";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        alcohol_link5.setOnClickListener(new View.OnClickListener() {
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