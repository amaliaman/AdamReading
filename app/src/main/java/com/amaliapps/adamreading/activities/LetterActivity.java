package com.amaliapps.adamreading.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.amaliapps.adamreading.R;
import com.amaliapps.adamreading.controllers.LetterDetailsRecyclerViewAdapter;
import com.amaliapps.adamreading.helper.Utils;
import com.amaliapps.adamreading.model.Letter;

import static com.amaliapps.adamreading.activities.MainActivity.LETTER_POSITION_EXTRA;

public class LetterActivity extends AppCompatActivity {

    private Letter mLetter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter);

        // Set Hebrew locale
        Utils.setHebrewLocale(this);

        // Get letter position in alphabet from intent, then get letter
        int position = getIntent().getIntExtra(MainActivity.LETTER_POSITION_EXTRA, 0);
        mLetter = Letter.alphabet.get(position);


        // Get a reference to the recycler view
        final RecyclerView recycler = findViewById(R.id.details_recycler_view);
//        recycler.setHasFixedSize(true);

        // todo: dynamically set span count according to available space
        // Setup a layout manager
        final LinearLayoutManager layoutManager = new LinearLayoutManager(
                this, LinearLayoutManager.HORIZONTAL, false);
        recycler.setLayoutManager(layoutManager);

        // Attach adapter to recycler view
        final LetterDetailsRecyclerViewAdapter adapter = new LetterDetailsRecyclerViewAdapter(this, Letter.alphabet);
        recycler.setAdapter(adapter);
        recycler.scrollToPosition(position);


        // add pager behavior
        final PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recycler);

//
//        // Setup navigation buttons
//        ImageButton prev = findViewById(R.id.prev);
//        ImageButton next = findViewById(R.id.next);
//        // Set to visible if it's not the first/last letter
//        if (letter.getPrevious() != null) {
//            prev.setVisibility(View.VISIBLE);
//        }
//        if (letter.getNext() != null) {
//            next.setVisibility(View.VISIBLE);
//        }
        // Attach listener
//        prev.setOnClickListener(navigationListener);
//        next.setOnClickListener(navigationListener);
    }



    View.OnClickListener navigationListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), LetterActivity.class);
            // Pass the position of the letter in the alphabet
            Letter target = null;

            switch (view.getId()) {
                case R.id.prev:
                    target = mLetter.getPrevious();
                    break;
                case R.id.next:
                    target = mLetter.getNext();
                    break;
            }

            intent.putExtra(LETTER_POSITION_EXTRA, Letter.alphabet.indexOf(target));
            startActivity(intent);
        }
    };
}
