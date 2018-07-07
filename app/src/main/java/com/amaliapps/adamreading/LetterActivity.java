package com.amaliapps.adamreading;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LetterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter);

        String a = getIntent().getStringExtra("ggg");
        TextView ch = findViewById(R.id.character);
        ch.setText(a);
    }
}
