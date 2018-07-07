package com.amaliapps.adamreading;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final int SPAN_COUNT = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set Hebrew locale
        setLocale(getString(R.string.hebrew_locale_code));

        // Populate alphabet list
        List<Letter> letters = createLetterList();

        // Get a reference to the recycler view
        RecyclerView recycler = findViewById(R.id.recycler_view);
        recycler.setHasFixedSize(true);

        // Setup a layout manager
        GridLayoutManager layoutManager = new GridLayoutManager(this, SPAN_COUNT);
        recycler.setLayoutManager(layoutManager);

        // Attach adapter to recycler view
        LetterRecyclerViewAdapter adapter = new LetterRecyclerViewAdapter(this, letters);
        recycler.setAdapter(adapter);
    }

    private void setLocale(String localeCode) {
        // Create a new Locale object
        Locale locale = new Locale(localeCode);
        Locale.setDefault(locale);
        // Create a new configuration object
        Configuration config = new Configuration();
        // Set the locale of the new configuration
        config.locale = locale;
        // Update the configuration of the Application context
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
    }

    private List<Letter> createLetterList() {
        List<Letter> lettersList = new ArrayList<>();
        lettersList.add(new Letter('א', "אלף"));
        lettersList.add(new Letter('ב', "בית"));
        return lettersList;
    }
}
