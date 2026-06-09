package com.israt.healthtwin;

import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class bmi_data extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bmi_diagramm);

        // 1. Chart aus dem XML-Layout heraussuchen
        LineChart lineChart = findViewById(R.id.lineChart);

        // 2. Datenpunkte für die 12 Monate erstellen (X = Monat als Index, Y = BMI-Wert)
        ArrayList<Entry> bmiEntries = new ArrayList<>();
        bmiEntries.add(new Entry(0f, 24.8f));  // Jan
        bmiEntries.add(new Entry(1f, 24.6f));  // Feb
        bmiEntries.add(new Entry(2f, 24.5f));  // Mär
        bmiEntries.add(new Entry(3f, 24.6f));  // Apr
        bmiEntries.add(new Entry(4f, 24.3f));  // Mai
        bmiEntries.add(new Entry(5f, 24.1f));  // Jun
        bmiEntries.add(new Entry(6f, 24.2f));  // Jul
        bmiEntries.add(new Entry(7f, 23.9f));  // Aug
        bmiEntries.add(new Entry(8f, 23.8f));  // Sep
        bmiEntries.add(new Entry(9f, 23.7f));  // Okt
        bmiEntries.add(new Entry(10f, 23.6f)); // Nov
        bmiEntries.add(new Entry(11f, 23.7f)); // Dez

        // 3. Styling für die Linie festlegen
        LineDataSet dataSet = new LineDataSet(bmiEntries, "BMI Verlauf");
        dataSet.setColor(Color.parseColor("#1A73E8"));       // Blaue Linie
        dataSet.setCircleColor(Color.parseColor("#1A73E8")); // Blaue Punkte
        dataSet.setLineWidth(3f);                             // Dicke der Linie
        dataSet.setCircleRadius(5f);                          // Größe der Punkte
        dataSet.setDrawCircleHole(false);                     // Punkte voll ausgefüllt
        dataSet.setValueTextSize(10f);                        // Textgröße der Werte
        dataSet.setDrawValues(false);                         // Werte direkt an den Punkten ausblenden

        // 4. Daten an das Chart übergeben
        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);

        // 5. Feinheiten für das Aussehen des Charts (Achsen & Legenden anpassen)
        lineChart.getDescription().setEnabled(false);         // Beschreibungstext unten rechts ausblenden
        lineChart.getLegend().setEnabled(false);              // Die kleine Legende ausblenden
        lineChart.getXAxis().setDrawGridLines(false);         // Vertikale Gitternetzlinien abschalten
        lineChart.getAxisRight().setEnabled(false);           // Rechte Y-Achse ausschalten

        // Ein sanfter Lade-Effekt beim Öffnen (1000 Millisekunden)
        lineChart.animateX(1000);

        // 6. Chart neu zeichnen lassen
        lineChart.invalidate();
    }
}