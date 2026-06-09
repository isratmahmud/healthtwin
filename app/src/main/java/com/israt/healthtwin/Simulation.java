package com.israt.healthtwin;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class Simulation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hier wird dein Simulations-Layout geladen
        setContentView(R.layout.life_simulation);

        // Falls du IN der Simulation auch die Navigationsleiste hast
        // und dort Klicks abfangen willst, käme das HIER hin.
    }
}