package com.amaliapps.adamreading.activities;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.amaliapps.adamreading.R;
import com.amaliapps.adamreading.controllers.LettersRecyclerViewAdapter;
import com.amaliapps.adamreading.helper.Utils;
import com.amaliapps.adamreading.helper.VarColumnGridLayoutManager;
import com.amaliapps.adamreading.model.Letter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        createAlphabet();

        // Create word list
        createWordList();

        // Get a reference to the recycler view
        RecyclerView recycler = findViewById(R.id.recycler_view);
        recycler.setHasFixedSize(true);

        // todo: dynamically set span count according to available space
        // Setup a layout manager
        GridLayoutManager layoutManager = new VarColumnGridLayoutManager(this, MIN_ITEM_WIDTH);
        recycler.setLayoutManager(layoutManager);

        // Attach adapter to recycler view
        LettersRecyclerViewAdapter adapter = new LettersRecyclerViewAdapter(this, Letter.alphabet);
        recycler.setAdapter(adapter);
    }

    private void createAlphabet() {
        Letter.alphabet = new ArrayList<>();
        Letter.alphabet.add(new Letter('א', "אלף", R.color.letterAleph));
        Letter.alphabet.add(new Letter('ב', "בית", R.color.letterBet));
        Letter.alphabet.add(new Letter('ג', "גימל", R.color.letterGimel));
        Letter.alphabet.add(new Letter('ד', "דלת", R.color.letterDalet));
    }

    private void createWordList() {
        Resources res = getResources();
        Map<Character, String[]> wordList = new HashMap<>();
        wordList.put('א', res.getStringArray(R.array.aleph));
        wordList.put('ב', res.getStringArray(R.array.bet));
        wordList.put('ג', res.getStringArray(R.array.gimel));
        wordList.put('ד', res.getStringArray(R.array.dalet));
        Letter.exampleWords = wordList;
    }
}
