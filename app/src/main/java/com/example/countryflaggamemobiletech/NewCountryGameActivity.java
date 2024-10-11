package com.example.countryflaggamemobiletech;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class NewCountryGameActivity extends AppCompatActivity {
    private Button btnGuessCountry, btnGuessHints, btnGuessFlag, btnAdvancedLevel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_country_game);

        btnGuessCountry = findViewById(R.id.btnGuessCountry);
        btnGuessHints = findViewById(R.id.btnGuessHints);
        btnGuessFlag = findViewById(R.id.btnGuessFlag);
        btnAdvancedLevel = findViewById(R.id.btnAdvancedLevel);

        btnGuessCountry.setOnClickListener(v -> {
            Intent intent = new Intent(NewCountryGameActivity.this, GuessCountryActivity.class);
            startActivity(intent);
        });

        btnGuessHints.setOnClickListener(v -> {
            Intent intent = new Intent(NewCountryGameActivity.this, GuessHintsActivity.class);
            startActivity(intent);
        });

        btnGuessFlag.setOnClickListener(v -> {
            Toast.makeText(NewCountryGameActivity.this, "Guess the Flag Selected", Toast.LENGTH_SHORT).show();
        });

        btnAdvancedLevel.setOnClickListener(v -> {
            Toast.makeText(NewCountryGameActivity.this, "Advanced Level Selected", Toast.LENGTH_SHORT).show();
        });
    }
}