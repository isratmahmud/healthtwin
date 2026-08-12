package com.israt.healthtwin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SleepInfoActivity extends AppCompatActivity {
//
    TextView sleep_link1;
    TextView sleep_link2;
    TextView sleep_link3;
    TextView sleep_link4;
    TextView sleep_link5;

    private UserData loggedInUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sleep_info);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.sleepXML), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(v.getPaddingStart() + systemBars.left, v.getPaddingTop()
                    + systemBars.top, v.getPaddingEnd()
                    + systemBars.right, v.getPaddingBottom() + systemBars.bottom);
            return insets;
        });

        //tab buttons
        TextView btnFitness = findViewById(R.id.btn_fitness);
        TextView btnNutrition = findViewById(R.id.btn_nutrition);
        TextView btnMentalHealth = findViewById(R.id.btn_mental_health);
        TextView btnSmoking = findViewById(R.id.btn_smoking);
        TextView btnAlcohol = findViewById(R.id.btn_alcohol);

        loggedInUser = (UserData) getIntent().getSerializableExtra("USER_OBJECT");

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
                intent.putExtra("USER_OBJECT", loggedInUser);
                startActivity(intent);
            }
        });

        btnNutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SleepInfoActivity.this, NutritionInfoActivity.class);
                intent.putExtra("USER_OBJECT", loggedInUser);
                startActivity(intent);
            }
        });

        btnMentalHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SleepInfoActivity.this, MentalHealthInfoActivity.class);
                intent.putExtra("USER_OBJECT", loggedInUser);
                startActivity(intent);
            }
        });

        btnSmoking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SleepInfoActivity.this, SmokingInfoActivity.class);
                intent.putExtra("USER_OBJECT", loggedInUser);
                startActivity(intent);
            }
        });

        btnAlcohol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SleepInfoActivity.this, AlcoholInfoActivity.class);
                intent.putExtra("USER_OBJECT", loggedInUser);
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

        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOnBackPressedDispatcher().onBackPressed();
            }
        });

        findViewById(R.id.btnHeart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SleepInfoActivity.this, SimulationActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("USER_OBJECT", loggedInUser);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnHome).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SleepInfoActivity.this, Dashboard_Activity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("USER_OBJECT", loggedInUser);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnProfile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SleepInfoActivity.this, UserProfileUpdateActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("USER_OBJECT", loggedInUser);
                startActivity(intent);
            }
        });

    }
}