package com.example.countryflaggamemobiletech;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class GuessFlagActivity extends AppCompatActivity {
    private ImageView flagImageView1, flagImageView2, flagImageView3;
    private TextView countryNameTextView, resultTextViewGF;
    private Button nextButton;

    private HashMap<String, Integer> countryFlagMap;
    private List<String> countryList;
    private String correctCountry;
    private int correctImageViewId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_flag);

        flagImageView1 = findViewById(R.id.flagImageView1);
        flagImageView2 = findViewById(R.id.flagImageView2);
        flagImageView3 = findViewById(R.id.flagImageView3);
        countryNameTextView = findViewById(R.id.countryNameTextView);
        resultTextViewGF = findViewById(R.id.resultTextViewGF);
        nextButton = findViewById(R.id.nextButton);

        countryFlagMap = new HashMap<>();
        countryFlagMap.put("Algeria", R.drawable.algeria);
        countryFlagMap.put("Belgium", R.drawable.belgium);
        countryFlagMap.put("Australia", R.drawable.australia);
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

        countryList = new ArrayList<>(countryFlagMap.keySet());

        startNewFlagGame();

        flagImageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkFlagSelection(R.id.flagImageView1);
            }
        });

        flagImageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkFlagSelection(R.id.flagImageView2);
            }
        });

        flagImageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkFlagSelection(R.id.flagImageView3);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewFlagGame();
            }
        });
    }

    private void startNewFlagGame() {
        Random random = new Random();
        List<String> chosenCountries = new ArrayList<>();
        // Randomly select 3 unique countries for the current round
        while (chosenCountries.size() <3) {
            String country = countryList.get(random.nextInt(countryList.size()));
            if (!chosenCountries.contains(country)) {
                chosenCountries.add(country);
            }
        }

        // randomly select the correct country from the three chosen countries
        correctCountry = chosenCountries.get(random.nextInt(3));
        countryNameTextView.setText(correctCountry);

        // set flag images for each ImageView
        flagImageView1.setImageResource(countryFlagMap.get(chosenCountries.get(0)));
        flagImageView2.setImageResource(countryFlagMap.get(chosenCountries.get(1)));
        flagImageView3.setImageResource(countryFlagMap.get(chosenCountries.get(2)));

        // show which ImageView contains the correct flag and store its ID
        if (chosenCountries.get(0).equals(correctCountry)) {
            correctImageViewId = R.id.flagImageView1;
        } else if (chosenCountries.get(1).equals(correctCountry)) {
            correctImageViewId = R.id.flagImageView2;
        } else {
            correctImageViewId = R.id.flagImageView3;
        }

        // Reset the result TextView and disable the "Next" button until the user selects a flag
        resultTextViewGF.setText("");
        nextButton.setEnabled(false);
    }

    // Method to check if the selected flag matches the correct flag
    private void checkFlagSelection(int selectedImageViewId) {
        if (selectedImageViewId == correctImageViewId) {
            // If the selected flag matches the correct flag
            resultTextViewGF.setText("CORRECT!");
            resultTextViewGF.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
        } else {
            // If the selected flag is incorrect
            resultTextViewGF.setText("WRONG!");
            resultTextViewGF.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        }

        // Enable the "Next" button to allow the user to start a new game
        nextButton.setEnabled(true);
    }
}