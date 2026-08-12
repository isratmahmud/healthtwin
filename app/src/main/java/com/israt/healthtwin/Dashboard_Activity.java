package com.israt.healthtwin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;
import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Dashboard_Activity extends AppCompatActivity {

    private UserData loggedInUser;
    String username = "";
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dashboard);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.dashboardXML), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(v.getPaddingStart() + systemBars.left, v.getPaddingTop()
                    + systemBars.top, v.getPaddingEnd()
                    + systemBars.right, v.getPaddingBottom() + systemBars.bottom);
            return insets;
        });

        // loggoed User
        new Thread(new Runnable() {
            @Override
            public void run() {

        loggedInUser = (UserData) getIntent().getSerializableExtra("USER_OBJECT");
        if (loggedInUser == null)
        {
            username = "Jenny";
            AppDatabase db = AppDatabase.getDatabase(Dashboard_Activity.this);
            loggedInUser = db.userDao().getUserByUsername("Jenny");
            username = loggedInUser.username;
            id = loggedInUser.id;

        }

        else {
            username = loggedInUser.username;
            id = loggedInUser.id;

        }
            }
        }).start();
        int i = 0;

        if (username == null || username.isEmpty()) {
            username = "Jenny"; // Fallback to existing database user
        }
        TextView greetingText = findViewById(R.id.textView);
        greetingText.setText(getString(R.string.greeting) + " " +username+ "!");

        // BMI aus der Datenbank laden und berechnen
        final String finalUsername = username;
        final TextView bmiValueText = findViewById(R.id.tv_bmi_value);
        final View bmiIndicator = findViewById(R.id.bmi_indicator);
        final View bmiBar = findViewById(R.id.bmi_bar);

        final TextView activityBox = findViewById(R.id.tv_activity_box);
        final TextView smokingBox = findViewById(R.id.tv_smoking_box);
        final TextView drinkingBox = findViewById(R.id.tv_drinking_box);
        final ImageView activityWarning = findViewById(R.id.iv_activity_warning);
        final ImageView smokingWarning = findViewById(R.id.iv_smoking_warning);
        final ImageView drinkingWarning = findViewById(R.id.iv_drinking_warning);

        new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase db = AppDatabase.getDatabase(Dashboard_Activity.this);
                
                // Fetch lifestyle data (assuming userId 1 for now as in ExerciseActivity)
                LifestyleData lifestyle = db.userDao().getLifestyleByUserId(id);
                //final LifestyleData lifestyle = (lifestyleList != null && !lifestyleList.isEmpty()) ? lifestyleList.get(0) : null;

                if (loggedInUser != null) {
                    double heightM = loggedInUser.height / 100.0;
                    final double bmi = loggedInUser.weight / (heightM * heightM);
                    final String bmiStr = String.format(java.util.Locale.US, "%.1f", bmi);
                    
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            bmiValueText.setText(bmiStr);
                            // Position des Indikators berechnen, sobald die Breite bekannt ist
                            bmiBar.post(new Runnable() {
                                @Override
                                public void run() {
                                    float positionPercent = calculateBmiPosition(bmi);
                                    float translationX = (bmiBar.getWidth() * positionPercent) - (bmiIndicator.getWidth() / 2f);
                                    bmiIndicator.setTranslationX(translationX);
                                    bmiIndicator.setVisibility(View.VISIBLE);
                                }
                            });

                            if (lifestyle != null) {
                                // Fitness: 0 (Never) is bad, 5 (180+) is good
                                updateCategoryBox(activityBox, lifestyle.physicalActivity, "ACTIVITY");
                                // Smoking/Drinking: 0 (Never) is good, 4+ is bad
                                updateCategoryBox(smokingBox, lifestyle.smoke, "SMOKING");
                                updateCategoryBox(drinkingBox, lifestyle.drink, "DRINKING");

                                // Activity Warning (Level 0 or 1 is low activity)
                                if (lifestyle.physicalActivity <= 1) {
                                    activityWarning.setVisibility(View.VISIBLE);
                                    activityWarning.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent intent = new Intent(Dashboard_Activity.this, HealthAttentionActivity.class);
                                            intent.putExtra("WARNING_TYPE", "EXERCISE");
                                            intent.putExtra("USER_OBJECT", loggedInUser);

                                            startActivity(intent);
                                        }
                                    });
                                } else {
                                    activityWarning.setVisibility(View.GONE);
                                }

                                // Smoking Warning (Level 4 or 5 is high)
                                if (lifestyle.smoke >= 4) {
                                    smokingWarning.setVisibility(View.VISIBLE);
                                    smokingWarning.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent intent = new Intent(Dashboard_Activity.this, HealthAttentionActivity.class);
                                            intent.putExtra("WARNING_TYPE", "SMOKING");
                                            intent.putExtra("USER_OBJECT", loggedInUser);
                                            startActivity(intent);
                                        }
                                    });
                                } else {
                                    smokingWarning.setVisibility(View.GONE);
                                }

                                // Drinking Warning (Level 4 or 5 is high)
                                if (lifestyle.drink >= 4) {
                                    drinkingWarning.setVisibility(View.VISIBLE);
                                    drinkingWarning.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent intent = new Intent(Dashboard_Activity.this, HealthAttentionActivity.class);
                                            intent.putExtra("WARNING_TYPE", "DRINKING");
                                            intent.putExtra("USER_OBJECT", loggedInUser);
                                            startActivity(intent);
                                        }
                                    });
                                } else {
                                    drinkingWarning.setVisibility(View.GONE);
                                }
                            }
                        }
                    });
                }
            }
        }).start();

        // 3. Finde das Diagramm-Element auf deinem Dashboard
        View dashboardChart = findViewById(R.id.bmi_dashboard);

        // Klick-Event für das Diagramm
        dashboardChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard_Activity.this, bmi_dataActivity.class);
                startActivity(intent);
            }
        });

        //Logout button

        findViewById(R.id.btnLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard_Activity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("USER_OBJECT", loggedInUser);
                startActivity(intent);
            }
        });


        // 4. "Edit" Buttons
        findViewById(R.id.edit_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(Dashboard_Activity.this, ExerciseActivity.class));
                Intent intent = new Intent(Dashboard_Activity.this, ExerciseActivity.class);
                intent.putExtra("USER_OBJECT", loggedInUser);
                startActivity(intent);

            }
        });

        findViewById(R.id.edit_smoking).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(Dashboard_Activity.this, SmokingActivity.class));
                Intent intent = new Intent(Dashboard_Activity.this, SmokingActivity.class);
                intent.putExtra("USER_OBJECT", loggedInUser);
                startActivity(intent);
            }
        });

        findViewById(R.id.edit_drinking).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(Dashboard_Activity.this, DrinkingActivity.class));
                Intent intent = new Intent(Dashboard_Activity.this, DrinkingActivity.class);
                intent.putExtra("USER_OBJECT", loggedInUser);
                startActivity(intent);

            }
        });

        // 5. Navigation Bar Icons
        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOnBackPressedDispatcher().onBackPressed();
            }
        });

        findViewById(R.id.btnHeart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard_Activity.this, SimulationActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("USER_OBJECT", loggedInUser);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnInfo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard_Activity.this, SleepInfoActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("USER_OBJECT", loggedInUser);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnProfile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard_Activity.this, UserProfileUpdateActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("USER_OBJECT", loggedInUser);
                startActivity(intent);
            }
        });

        // 6. Language Toggle
        android.widget.Button btnLanguage = findViewById(R.id.btnLanguage);
        updateLanguageButtonText(btnLanguage);
        btnLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleLanguage();
            }
        });
    }

    private void updateLanguageButtonText(android.widget.Button btn) {
        String text = getString(R.string.language_toggle);
        android.text.SpannableString spannableString = new android.text.SpannableString(text);

        String currentLang = getResources().getConfiguration().getLocales().get(0).getLanguage();

        if (currentLang.equals("de")) {
            int start = text.indexOf("DE");
            if (start != -1) {
                spannableString.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), start, start + 2, 0);
                spannableString.setSpan(new android.text.style.ForegroundColorSpan(Color.BLACK), start, start + 2, 0);
                spannableString.setSpan(new android.text.style.UnderlineSpan(), start, start + 2, 0);
            }
        } else {
            int start = text.indexOf("EN");
            if (start != -1) {
                spannableString.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), start, start + 2, 0);
                spannableString.setSpan(new android.text.style.ForegroundColorSpan(Color.BLACK), start, start + 2, 0);
                spannableString.setSpan(new android.text.style.UnderlineSpan(), start, start + 2, 0);
            }
        }
        btn.setText(spannableString);
    }

    private void toggleLanguage() {
        String currentLang = getResources().getConfiguration().getLocales().get(0).getLanguage();
        String newLang = currentLang.equals("de") ? "en" : "de";
        
        java.util.Locale locale = new java.util.Locale(newLang);
        java.util.Locale.setDefault(locale);
        
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.setLocale(locale);
        
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
        
        // Restart activity to apply changes
        android.content.Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    private void updateCategoryBox(TextView view, int level, String type) {
        int color;
        int effectiveLevel = level;
        boolean invert = !type.equals("ACTIVITY");

        if (invert) {
            // For Smoking/Drinking: 0 -> 5 (Green), 4 -> 0 (Red)
            switch (level) {
                case 0: effectiveLevel = 4; break;
                case 1: effectiveLevel = 3; break;
                case 2: effectiveLevel = 2; break;
                case 3: effectiveLevel = 1; break;
                case 4: effectiveLevel = 0; break;
                default: effectiveLevel = 0; break;
            }
        }

        switch (effectiveLevel) {
            case 0: color = Color.argb(77, 139, 0, 0); break;   // Dunkelrot
           // case 1: color = Color.argb(77, 255, 102, 102); break; // Hellrot
            case 1: color = Color.argb(77, 255, 165, 0); break;   // Orange
            case 2: color = Color.argb(77, 255, 255, 0); break;   // Gelb
            case 3: color = Color.argb(77, 144, 238, 144); break; // Hellgrün
            case 4: color = Color.argb(77, 0, 100, 0); break;     // Dunkelgrün
            default: color = Color.argb(77, 200, 200, 200); break;
        }
        view.setBackgroundColor(color);

        // Update Text
        int textResId = 0;
        if (type.equals("ACTIVITY")) {
            switch (level) {
                case 0: textResId = R.string.label_never; break;
                case 1: textResId = R.string.occasionally; break;
                case 2: textResId = R.string.M30_60; break;
                case 3: textResId = R.string.M61_120; break;
                case 4: textResId = R.string.M120; break;
            }
        } else if (type.equals("SMOKING")) {
            switch (level) {
                case 0: textResId = R.string.label_never; break;
                case 1: textResId = R.string.occasionally; break;
                case 2: textResId = R.string.C1_5; break;
                case 3: textResId = R.string.C6_10; break;
                case 4: textResId = R.string.C10; break;
            }
        } else if (type.equals("DRINKING")) {
            switch (level) {
                case 0: textResId = R.string.label_never; break;
                case 1: textResId = R.string.occasionally; break;
                case 2: textResId = R.string.D1_5; break;
                case 3: textResId = R.string.D6_10; break;
                case 4: textResId = R.string.D10; break;
            }
        }
        
        if (textResId != 0) {
            view.setText(getString(textResId));
        }
    }

    private float calculateBmiPosition(double bmi) {
        if (bmi < 18.5) {
            float pos = (float) (bmi / 18.5);
            return pos * 0.1667f;
        } else if (bmi < 25) {
            float pos = (float) ((bmi - 18.5) / (25 - 18.5));
            return 0.1667f + (pos * 0.1667f);
        } else if (bmi < 30) {
            float pos = (float) ((bmi - 25) / (30 - 25));
            return 0.3333f + (pos * 0.1667f);
        } else if (bmi < 35) {
            float pos = (float) ((bmi - 30) / (35 - 30));
            return 0.5f + (pos * 0.1667f);
        } else if (bmi < 40) {
            float pos = (float) ((bmi - 35) / (40 - 35));
            return 0.6667f + (pos * 0.1667f);
        } else {
            float pos = (float) ((bmi - 40) / 10.0); // Bis BMI 50
            if (pos > 1.0f) pos = 1.0f;
            return 0.8333f + (pos * 0.1667f);
        }
    }
}
