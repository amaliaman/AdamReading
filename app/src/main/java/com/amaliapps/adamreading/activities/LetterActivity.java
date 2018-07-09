package com.amaliapps.adamreading.activities;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.amaliapps.adamreading.R;
import com.amaliapps.adamreading.helper.Utils;
import com.amaliapps.adamreading.model.Letter;

import java.util.Objects;

import static com.amaliapps.adamreading.activities.MainActivity.LETTER_POSITION_EXTRA;

public class LetterActivity extends AppCompatActivity {

    Letter letter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter);

        // Set Hebrew locale
        Utils.setHebrewLocale(this);

        // Get letter position in alphabet from intent, then get letter
        int position = getIntent().getIntExtra(MainActivity.LETTER_POSITION_EXTRA, 0);
        letter = Letter.alphabet.get(position);

        // Set activity's colors
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(
                new ColorDrawable(getResources().getColor(letter.getColorResourceId())));
        Utils.darkenStatusBar(this, letter.getColorResourceId());

        // Set TextViews contents according to current letter
        TextView characterTextView = findViewById(R.id.character);
        characterTextView.setText(String.valueOf(letter.getCharacter()));

        TextView nameTextView = findViewById(R.id.name);
        nameTextView.setText(letter.getName());

        // Populate word list with contents according to current letter
        ViewGroup wrapper = findViewById(R.id.wrapper);
        String[] words = Letter.exampleWords.get(letter.getCharacter());
        for (String word : words) {
            TextView wordTextView = new TextView(this);
            wordTextView.setTextAppearance(this, R.style.WordList);
            wordTextView.setText(word);
            wrapper.addView(wordTextView);
        }

        // Setup navigation buttons
        ImageButton prev = findViewById(R.id.prev);
        ImageButton next = findViewById(R.id.next);
        // Set to visible if it's not the first/last letter
        if (letter.getPrevious() != null) {
            prev.setVisibility(View.VISIBLE);
        }
        if (letter.getNext() != null) {
            next.setVisibility(View.VISIBLE);
        }
        // Attach listener
        prev.setOnClickListener(navigationListener);
        next.setOnClickListener(navigationListener);
    }

    View.OnClickListener navigationListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), LetterActivity.class);
            // Pass the position of the letter in the alphabet
            Letter target = null;

            switch (view.getId()) {
                case R.id.prev:
                    target = letter.getPrevious();
                    break;
                case R.id.next:
                    target = letter.getNext();
                    break;
            }

            intent.putExtra(LETTER_POSITION_EXTRA, Letter.alphabet.indexOf(target));
            startActivity(intent);
        }
    };
}
