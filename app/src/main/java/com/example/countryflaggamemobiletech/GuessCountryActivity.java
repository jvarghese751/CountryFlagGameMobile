package com.example.countryflaggamemobiletech;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class GuessCountryActivity extends AppCompatActivity {

    private ImageView flagImageView;
    private Spinner countrySpinner;
    private Button submitGuessButton;

    private HashMap<String, Integer> countryFlagMap;
    private String correctCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_country);

        flagImageView = findViewById(R.id.flagImageView);
        countrySpinner = findViewById(R.id.countrySpinner);
        submitGuessButton = findViewById(R.id.submitGuessButton);

        countryFlagMap = new HashMap<>();
        countryFlagMap.put("Algeria", R.drawable.algeria);
        countryFlagMap.put("Australia", R.drawable.australia);
        countryFlagMap.put("Belgium", R.drawable.belgium);
        countryFlagMap.put("Brazil", R.drawable.brazil);
        countryFlagMap.put("Canada", R.drawable.canada);
        countryFlagMap.put("Colombia", R.drawable.colombia);
        countryFlagMap.put("Croatia", R.drawable.croatia);
        countryFlagMap.put("Denmark", R.drawable.denmark);
        countryFlagMap.put("Egypt", R.drawable.egypt);
        countryFlagMap.put("Finland", R.drawable.finland);
        countryFlagMap.put("France", R.drawable.france);
        countryFlagMap.put("Germany", R.drawable.germany);
        countryFlagMap.put("Greece", R.drawable.greece);
        countryFlagMap.put("Portugal", R.drawable.portugal);
        countryFlagMap.put("Singapore", R.drawable.singapore);
        countryFlagMap.put("Spain", R.drawable.spain);
        countryFlagMap.put("Sweden", R.drawable.sweden);
        countryFlagMap.put("Switzerland", R.drawable.switzerland);
        countryFlagMap.put("Turkey", R.drawable.turkey);
        countryFlagMap.put("Ukraine", R.drawable.ukraine);
        countryFlagMap.put("Zimbabwe", R.drawable.zimbabwe);

        getRandomFlag();

        List<String> countryList = new ArrayList<>(countryFlagMap.keySet());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, countryList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countrySpinner.setAdapter(adapter);

        submitGuessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedCountry = countrySpinner.getSelectedItem().toString();

                if (selectedCountry.equals(correctCountry)) {
                    Toast.makeText(GuessCountryActivity.this, "Correct! Well done.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(GuessCountryActivity.this, "Wrong! The correct answer is " + correctCountry, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getRandomFlag() {
        List<String> countries = new ArrayList<>(countryFlagMap.keySet());
        Random random = new Random();
        correctCountry = countries.get(random.nextInt(countries.size()));

        flagImageView.setImageResource(countryFlagMap.get(correctCountry));
    }
}