package com.example.countryflaggamemobiletech;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button btnCountryGame, btnAbout, btnNewCountryGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        btnCountryGame = findViewById(R.id.btnCountryGame);
        btnAbout = findViewById(R.id.btnAbout);
        btnNewCountryGame = findViewById(R.id.btnNewCountryGame);

        btnCountryGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Country Game Started!", Toast.LENGTH_SHORT).show();
            }
        });

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Placeholder code for About
                Toast.makeText(MainActivity.this, "About Information Displayed", Toast.LENGTH_SHORT).show();
            }
        });

        btnNewCountryGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Placeholder code for New Country Game
                Toast.makeText(MainActivity.this, "New Country Game Started!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}