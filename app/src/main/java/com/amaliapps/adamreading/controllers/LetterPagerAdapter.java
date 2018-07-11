package com.amaliapps.adamreading.controllers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.amaliapps.adamreading.activities.LetterFragment;
import com.amaliapps.adamreading.model.Letter;

import java.util.ArrayList;
import java.util.List;

import static com.amaliapps.adamreading.activities.LetterFragment.BUNDLE_LETTER_POSITION;

public class LetterPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    public LetterPagerAdapter(FragmentManager fm) {
        super(fm);
        this.fragments = new ArrayList<>();
        for (int i = 0; i < Letter.alphabet.size(); i++) {
            this.fragments.add(newInstance(i));
        }
    }

    private static LetterFragment newInstance(int currentPosition) {
        LetterFragment slider = new LetterFragment();
        Bundle b = new Bundle();
        b.putInt(BUNDLE_LETTER_POSITION, currentPosition);
        slider.setArguments(b);
        return slider;
    }

    @Override
    public Fragment getItem(int position) {
        return this.fragments.get(position);

    }

    @Override
    public int getCount() {
        return this.fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return Letter.alphabet.get(position).getName();
    }
}
