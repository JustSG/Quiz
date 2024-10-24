package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class PodpowiedzActivity extends AppCompatActivity {
private TextView textViewPodpowiedz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podpowiedz);
        String podpowiedz = getIntent().getStringExtra("PODPOWIEDZ");
        textViewPodpowiedz = findViewById(R.id.textViewPodpowiedz);
        textViewPodpowiedz.setText(podpowiedz);

    }
}