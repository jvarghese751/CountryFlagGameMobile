package com.example.countryflaggamemobiletech;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class AdvancedLevelActivity extends AppCompatActivity {
    private ImageView flagImageViewAdvanced1, flagImageViewAdvanced2, flagImageViewAdvanced3;
    private EditText countryInput1, countryInput2, countryInput3;
    private Button btnSubmit;
    private HashMap<String, Integer> countryFlagMap;
    private List<String> countries;
    private String[] correctCountries;
    private TextView resultTextView1, resultTextView2, resultTextView3, messageTextView, scoreTextView;
    private int incorrectAttempts;
    private int score;
    private boolean[] correctlyGuessed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_level);

        flagImageViewAdvanced1 = findViewById(R.id.flagImageViewAdvanced1);
        flagImageViewAdvanced2 = findViewById(R.id.flagImageViewAdvanced2);
        flagImageViewAdvanced3 = findViewById(R.id.flagImageViewAdvanced3);
        countryInput1 = findViewById(R.id.countryInput1);
        countryInput2 = findViewById(R.id.countryInput2);
        countryInput3 = findViewById(R.id.countryInput3);
        btnSubmit = findViewById(R.id.btnSubmit);
        resultTextView1 = findViewById(R.id.resultTextView1);
        resultTextView2 = findViewById(R.id.resultTextView2);
        resultTextView3 = findViewById(R.id.resultTextView3);
        messageTextView = findViewById(R.id.messageTextView);
        scoreTextView = findViewById(R.id.scoreTextView);

        // Initialize the country and flag map
        initializeCountryFlagMap();
        loadRandomFlags();

        incorrectAttempts = 0;
        score = 0;
        correctlyGuessed = new boolean[3];

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnSubmit.getText().equals("Submit")) {
                    checkAnswers();
                } else {
                    loadRandomFlags();
                    btnSubmit.setText("Submit");
                    messageTextView.setVisibility(View.GONE);
                }
            }
        });
    }

    private void initializeCountryFlagMap() {
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

        countries = new ArrayList<>(countryFlagMap.keySet());
    }

    private void loadRandomFlags() {
        Random random = new Random();
        correctCountries = new String[3];
        correctlyGuessed = new boolean[3];

        for (int i = 0; i < 3; i++) {
            int index;
            // Ensure no duplicate flags are picked
            do {
                index = random.nextInt(countries.size());
            } while (contains(correctCountries, countries.get(index)));
            correctCountries[i] = countries.get(index);
            switch (i) {
                case 0:
                    flagImageViewAdvanced1.setImageResource(countryFlagMap.get(correctCountries[i]));
                    break;
                case 1:
                    flagImageViewAdvanced2.setImageResource(countryFlagMap.get(correctCountries[i]));
                    break;
                case 2:
                    flagImageViewAdvanced3.setImageResource(countryFlagMap.get(correctCountries[i]));
                    break;
            }
        }

        resetTextboxes();
    }

    private boolean contains(String[] array, String value) {
        for (String s : array) {
            if (s != null && s.equals(value)) {
                return true;
            }
        }
        return false;
    }

    private void checkAnswers() {
        boolean allCorrect = true;
        int correctCountThisAttempt = 0;

        // Check answers for each textbox
        String[] userAnswers = {
                countryInput1.getText().toString().trim(),
                countryInput2.getText().toString().trim(),
                countryInput3.getText().toString().trim()
        };

        for (int i = 0; i < 3; i++) {
            if (TextUtils.isEmpty(userAnswers[i])) {
                continue; // Skip empty inputs
            }

            if (userAnswers[i].equalsIgnoreCase(correctCountries[i])) {
                if (!correctlyGuessed[i]) {
                    setTextboxCorrect(i);
                    correctlyGuessed[i] = true;
                    correctCountThisAttempt++;
                }
            } else {
                setTextboxIncorrect(i);
                allCorrect = false;
            }
        }

        // Update score based on correct guesses
        score += correctCountThisAttempt;
        updateScoreDisplay();

        if (allCorrect) {
            showMessage("CORRECT!", Color.GREEN);
            btnSubmit.setText("Next");
        } else {
            incorrectAttempts++;
            if (incorrectAttempts >= 3) {
                displayCorrectAnswers();
                showMessage("WRONG!", Color.RED);
                btnSubmit.setText("Next");
            }
        }
    }

    private void setTextboxCorrect(int index) {
        switch (index) {
            case 0:
                countryInput1.setBackgroundColor(Color.GREEN);
                countryInput1.setEnabled(false);
                break;
            case 1:
                countryInput2.setBackgroundColor(Color.GREEN);
                countryInput2.setEnabled(false);
                break;
            case 2:
                countryInput3.setBackgroundColor(Color.GREEN);
                countryInput3.setEnabled(false);
                break;
        }
    }

    private void setTextboxIncorrect(int index) {
        switch (index) {
            case 0:
                countryInput1.setBackgroundColor(Color.RED);
                break;
            case 1:
                countryInput2.setBackgroundColor(Color.RED);
                break;
            case 2:
                countryInput3.setBackgroundColor(Color.RED);
                break;
        }
    }

    private void displayCorrectAnswers() {
        // Show the correct answers for incorrect attempts
        if (countryInput1.isEnabled()) {
            resultTextView1.setText(correctCountries[0]);
            resultTextView1.setTextColor(Color.BLUE);
        }
        if (countryInput2.isEnabled()) {
            resultTextView2.setText(correctCountries[1]);
            resultTextView2.setTextColor(Color.BLUE);
        }
        if (countryInput3.isEnabled()) {
            resultTextView3.setText(correctCountries[2]);
            resultTextView3.setTextColor(Color.BLUE);
        }
    }

    private void showMessage(String message, int color) {
        messageTextView.setText(message);
        messageTextView.setTextColor(color);
        messageTextView.setVisibility(View.VISIBLE);
    }

    private void resetTextboxes() {
        countryInput1.setText("");
        countryInput2.setText("");
        countryInput3.setText("");
        countryInput1.setEnabled(true);
        countryInput2.setEnabled(true);
        countryInput3.setEnabled(true);
        countryInput1.setBackgroundColor(Color.WHITE);
        countryInput2.setBackgroundColor(Color.WHITE);
        countryInput3.setBackgroundColor(Color.WHITE);

        // Clear result TextViews
        resultTextView1.setText("");
        resultTextView2.setText("");
        resultTextView3.setText("");

        messageTextView.setVisibility(View.GONE);
    }

    private void updateScoreDisplay() {
        scoreTextView.setText("Score: " + score);
    }
}