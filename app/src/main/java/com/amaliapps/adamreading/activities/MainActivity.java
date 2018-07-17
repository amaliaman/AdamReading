package com.amaliapps.adamreading.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.amaliapps.adamreading.R;
import com.amaliapps.adamreading.controllers.LettersRecyclerViewAdapter;
import com.amaliapps.adamreading.helper.Initializer;
import com.amaliapps.adamreading.helper.Utils;
import com.amaliapps.adamreading.controllers.VarColumnGridLayoutManager;
import com.amaliapps.adamreading.model.Letter;

public class MainActivity extends AppCompatActivity {

    /**
     * Width of a single letter card in grid
     */
    private static final int MIN_ITEM_WIDTH = 360;

    /**
     * Key for letter position extra
     */
    public static final String LETTER_POSITION_EXTRA = MainActivity.class.getPackage().getName() +
            "." + MainActivity.class.getSimpleName() + ".LETTER_POSITION_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set Hebrew locale
        Utils.setHebrewLocale(this);

        // Populate alphabet list
//        if (!Initializer.isInitialized) {
            Initializer.initialize(getResources());
//        }

        // Get a reference to the recycler view
        RecyclerView recycler = findViewById(R.id.recycler_view);
        recycler.setHasFixedSize(true);

        // Setup a layout manager
        GridLayoutManager layoutManager = new VarColumnGridLayoutManager(this, MIN_ITEM_WIDTH);
        recycler.setLayoutManager(layoutManager);

        // Attach adapter to recycler view
        LettersRecyclerViewAdapter adapter = new LettersRecyclerViewAdapter(this, Letter.alphabet);
        recycler.setAdapter(adapter);
    }
}
