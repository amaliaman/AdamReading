package com.amaliapps.adamreading.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.amaliapps.adamreading.R;
import com.amaliapps.adamreading.controllers.LetterPagerAdapter;
import com.amaliapps.adamreading.helpers.Constants;
import com.amaliapps.adamreading.helpers.Utils;
import com.amaliapps.adamreading.model.Letter;

import java.util.Objects;

public class LetterActivity extends AppCompatActivity {
    ViewPager pager;
    LetterPagerAdapter adapter;
    private int position = 0;
    Letter letter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter);

        // Set Hebrew locale
        Utils.setHebrewLocale(this);

        // Get current letter by its position in alphabet list
        if (Objects.requireNonNull(getIntent().hasExtra(MainActivity.EXTRA_LETTER_POSITION))) {
            position = Objects.requireNonNull(getIntent().getIntExtra(MainActivity.EXTRA_LETTER_POSITION, 0));
        }
        letter = Letter.alphabet.get(position);

        // Setup the view pager that will allow the user to swipe between fragments
        adapter = new LetterPagerAdapter(getSupportFragmentManager());
        pager = findViewById(R.id.view_pager);
        pager.setRotationY(Constants.ROTATION);
        pager.setAdapter(adapter);
        pager.setCurrentItem(position);

        // Change activity's colors when new fragment is paged in
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // Get current fragment
                String pageTag = Constants.PAGE_TAG_PREFIX + R.id.view_pager + ":" + pager.getCurrentItem();
                Fragment page = getSupportFragmentManager().findFragmentByTag(pageTag);
                LetterFragment currentFragment = (LetterFragment) page;
                currentFragment.changeActivityColors();
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
