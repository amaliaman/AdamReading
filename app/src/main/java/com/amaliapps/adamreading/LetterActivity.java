package com.amaliapps.adamreading;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Arrays;

public class LetterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter);

        Letter a = getIntent().getParcelableExtra("ggg");

        TextView ch = findViewById(R.id.character);
        ch.setText(a.getName());

        String[] arr = Letter.sWords.get(a.getCharacter());

        TextView n = findViewById(R.id.name);
        n.setText(Arrays.toString(arr));
    }
}
