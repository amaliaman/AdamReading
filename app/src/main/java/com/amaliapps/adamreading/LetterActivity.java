package com.amaliapps.adamreading;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Objects;

public class LetterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter);

        // Get letter from intent
        Letter letter = getIntent().getParcelableExtra(MainActivity.LETTER_EXTRA);

        // Set activity's colors
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(
                new ColorDrawable(getResources().getColor(letter.getColorResourceId())));
        Utils.darkenStatusBar(this, letter.getColorResourceId());

        TextView ch = findViewById(R.id.character);
        ch.setText(String.valueOf(letter.getCharacter()));

        ViewGroup wrapper = findViewById(R.id.wrapper);
        String[] arr = Letter.sWords.get(letter.getCharacter());

        for (String word : arr) {
            TextView tv = new TextView(this);
            tv.setText(word);
            wrapper.addView(tv);
        }

        TextView n = findViewById(R.id.name);
        n.setText(letter.getName());
    }
}
