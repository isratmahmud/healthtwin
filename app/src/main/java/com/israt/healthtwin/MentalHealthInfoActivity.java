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

public class MentalHealthInfoActivity extends AppCompatActivity {
    //
    TextView mentalHealth_link1;
    TextView mentalHealth_link2;
    TextView mentalHealth_link3;
    TextView mentalHealth_link4;
    TextView mentalHealth_link5;

    private UserData loggedInUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mental_health_info);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mentalXML), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(v.getPaddingStart() + systemBars.left, v.getPaddingTop()
                    + systemBars.top, v.getPaddingEnd()
                    + systemBars.right, v.getPaddingBottom() + systemBars.bottom);
            return insets;
        });

        //tab buttons
        TextView btnSleep = findViewById(R.id.btn_sleep);
        TextView btnFitness = findViewById(R.id.btn_fitness);
        TextView btnNutrition = findViewById(R.id.btn_nutrition);
        TextView btnSmoking = findViewById(R.id.btn_smoking);
        TextView btnAlcohol = findViewById(R.id.btn_alcohol);

        loggedInUser = (UserData) getIntent().getSerializableExtra("USER_OBJECT");

        // links
        mentalHealth_link1 = findViewById(R.id.link1);
        mentalHealth_link2 = findViewById(R.id.link2);
        mentalHealth_link3 = findViewById(R.id.link3);
        mentalHealth_link4 = findViewById(R.id.link4);
        mentalHealth_link5 = findViewById(R.id.link5);


        // onClick Methods for the tab buttons
        btnSleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MentalHealthInfoActivity.this, SleepInfoActivity.class);
                intent.putExtra("USER_OBJECT", loggedInUser);
                startActivity(intent);
            }
        });

        btnFitness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MentalHealthInfoActivity.this, FitnessInfoActivity.class);
                intent.putExtra("USER_OBJECT", loggedInUser);
                startActivity(intent);
            }
        });

        btnNutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MentalHealthInfoActivity.this, NutritionInfoActivity.class);
                intent.putExtra("USER_OBJECT", loggedInUser);
                startActivity(intent);
            }
        });

        btnSmoking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MentalHealthInfoActivity.this, SmokingInfoActivity.class);
                intent.putExtra("USER_OBJECT", loggedInUser);
                startActivity(intent);
            }
        });

        btnAlcohol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MentalHealthInfoActivity.this, AlcoholInfoActivity.class);
                intent.putExtra("USER_OBJECT", loggedInUser);
                startActivity(intent);
            }
        });


        // onClick Methods for the links
        mentalHealth_link1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.nimh.nih.gov/health/topics/caring-for-your-mental-health";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        mentalHealth_link2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.rethink.org/news-and-stories/commonly-asked-mental-health-questions/what/10-tips-to-maintain-your-mental-health/";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        mentalHealth_link3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.psychologytoday.com/us/blog/stonewall-strong/202504/nine-ways-to-support-and-strengthen-your-mental-health";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        mentalHealth_link4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.cdc.gov/mental-health/living-with/index.html";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        mentalHealth_link5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.psych-doctor.com/post/how-can-i-manage-anxiety-in-a-high-paced-world-in-2026";
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
                Intent intent = new Intent(MentalHealthInfoActivity.this, SimulationActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("USER_OBJECT", loggedInUser);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnHome).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MentalHealthInfoActivity.this, Dashboard_Activity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("USER_OBJECT", loggedInUser);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnProfile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MentalHealthInfoActivity.this, UserProfileUpdateActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("USER_OBJECT", loggedInUser);
                startActivity(intent);
            }
        });

    }
}