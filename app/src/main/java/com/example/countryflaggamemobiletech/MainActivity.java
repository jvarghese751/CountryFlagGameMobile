package com.example.countryflaggamemobiletech;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button btnAbout, btnNewCountryGame;
    private FrameLayout fragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        btnAbout = findViewById(R.id.btnAbout);
        btnNewCountryGame = findViewById(R.id.btnNewCountryGame);
        fragmentContainer = findViewById(R.id.fragment_container);

        btnNewCountryGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When the button is clicked, start a new activity for the country game.
                Intent intent = new Intent(MainActivity.this, NewCountryGameActivity.class);
                startActivity(intent);
            }
        });

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When the button is clicked, show the About fragment.
                showAboutFragment();
            }
        });
    }

    private void showAboutFragment() {
        // Check if the fragment is already added
        AboutFragment aboutFragment = (AboutFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (aboutFragment == null) {
            aboutFragment = new AboutFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragment_container, aboutFragment);
            transaction.commit();
        }
        // Show the fragment container
        fragmentContainer.setVisibility(View.VISIBLE);
    }
}