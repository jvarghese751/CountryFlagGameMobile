package com.example.countryflaggamemobiletech;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
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

public class GuessHintsActivity extends AppCompatActivity {
    private ImageView hintFlagImageView;
    private TextView dashesTextView;
    private EditText charInputEditText;
    private Button submitHintButton;

    private HashMap<String, Integer> countryFlagMap; // Map of country names and their flags
    private String correctCountry;  // The correct country for the current flag
    private StringBuilder currentDashes;  // Dashes corresponding to the country name
    private int incorrectGuesses;
    private boolean gameEnded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_hints);

        hintFlagImageView = findViewById(R.id.hintFlagImageView);
        dashesTextView = findViewById(R.id.dashesTextView);
        charInputEditText = findViewById(R.id.charInputEditText);
        submitHintButton = findViewById(R.id.submitHintButton);

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

        startNewHintGame();

        submitHintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buttonText = submitHintButton.getText().toString();
                if (buttonText.equals("Next")) {
                    startNewHintGame();
                } else {
                    String inputChar = charInputEditText.getText().toString().trim();
                    if (TextUtils.isEmpty(inputChar)) {
                        Toast.makeText(GuessHintsActivity.this, "Please enter a character", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    charInputEditText.setText("");
                    checkCharacter(inputChar.toLowerCase());
                }
            }
        });
    }

    private void startNewHintGame() {
        List<String> countries = new ArrayList<>(countryFlagMap.keySet());
        Random random = new Random();
        correctCountry = countries.get(random.nextInt(countries.size()));

        hintFlagImageView.setImageResource(countryFlagMap.get(correctCountry));

        currentDashes = new StringBuilder();
        for (int i = 0; i < correctCountry.length(); i++) {
            if (correctCountry.charAt(i) == ' ') {
                currentDashes.append(" ");
            } else {
                currentDashes.append("-");
            }
        }

        dashesTextView.setText((currentDashes.toString()));

        // Reset incorrect guesses
        incorrectGuesses = 0;

        // Clear any previous result message
        TextView resultTextViewGH = findViewById(R.id.resultTextViewGH);
        resultTextViewGH.setText("");

        // Reset the button text to "Submit"
        submitHintButton.setText("Submit");
        submitHintButton.setEnabled(true);
    }

    private void checkCharacter(String inputChar) {
        boolean charFound = false;
        char guessedChar = inputChar.charAt(0);

        for (int i = 0; i < correctCountry.length(); i++) {
            if (Character.toLowerCase(correctCountry.charAt(i)) == guessedChar) {
                currentDashes.setCharAt(i, correctCountry.charAt(i));
                charFound = true;
            }
        }

        dashesTextView.setText(currentDashes.toString());

        TextView resultTextViewGH = findViewById(R.id.resultTextViewGH);

        if (!charFound) {
            incorrectGuesses++;
            if (incorrectGuesses >= 3) {
                // Create a spannable string to style "WRONG!" and the country name separately
                String message = "WRONG! The correct country is: " + correctCountry;
                SpannableString spannableMessage = new SpannableString(message);

                // Apply red color to the "WRONG!" part
                spannableMessage.setSpan(
                        new ForegroundColorSpan(getResources().getColor(android.R.color.holo_red_dark)),
                        0, 6,  // Range covering "WRONG!"
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                );

                // Apply blue color to the country name part
                spannableMessage.setSpan(
                        new ForegroundColorSpan(getResources().getColor(android.R.color.holo_blue_dark)),
                        29, message.length(),  // Range covering the country name
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                );

                // Set the styled text to the TextView
                resultTextViewGH.setText(spannableMessage);

                submitHintButton.setText("Next");
                gameEnded = true;
            } else {
                Toast.makeText(this, "Character not found! Attempts left: " + (3 - incorrectGuesses), Toast.LENGTH_SHORT).show();
            }
        } else {
            // Check if the user has guessed the entire country name
            if (currentDashes.toString().equals(correctCountry)) {
                resultTextViewGH.setText("CORRECT!");
                resultTextViewGH.setTextColor(getResources().getColor(android.R.color.holo_green_dark));

                submitHintButton.setText("Next");
                gameEnded = true;
            }
        }
    }
}