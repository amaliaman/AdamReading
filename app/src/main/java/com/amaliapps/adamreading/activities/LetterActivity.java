package com.amaliapps.adamreading.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;

import com.amaliapps.adamreading.R;
import com.amaliapps.adamreading.controllers.LetterDetailsRecyclerViewAdapter;
import com.amaliapps.adamreading.helper.Utils;
import com.amaliapps.adamreading.model.Letter;

public class LetterActivity extends AppCompatActivity {
    AppCompatActivity aca;
    Letter l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter);

        // Set Hebrew locale
        Utils.setHebrewLocale(this);

        // Get letter position in alphabet from intent
        final int position = getIntent().getIntExtra(MainActivity.LETTER_POSITION_EXTRA, 0);

        // Get a reference to the recycler view
        final RecyclerView recycler = findViewById(R.id.details_recycler_view);
        recycler.setHasFixedSize(true);

        // Setup a layout manager
        final LinearLayoutManager layoutManager = new LinearLayoutManager(
                this, LinearLayoutManager.HORIZONTAL, false);
        recycler.setLayoutManager(layoutManager);

        // Attach adapter to recycler view
        final LetterDetailsRecyclerViewAdapter adapter =
                new LetterDetailsRecyclerViewAdapter(this, Letter.alphabet);
        recycler.setAdapter(adapter);
        recycler.scrollToPosition(position);

        // add pager behavior
        final PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recycler);

/*
        aca = this;
        l = Letter.alphabet.get(layoutManager.findFirstVisibleItemPosition());
        recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    Log.d("===", "onScrollStateChanged: " + newState);
                    Utils.changeActivityTheme(aca, l);
                    Utils.setActivityTitle(aca, l);
                }
            }
        });
*/
    }
}
