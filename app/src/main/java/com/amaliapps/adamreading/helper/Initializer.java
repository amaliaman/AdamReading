package com.amaliapps.adamreading.helper;

import android.content.res.Resources;

import com.amaliapps.adamreading.R;
import com.amaliapps.adamreading.model.Letter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Initializer {

    public static void createAlphabet() {
        Letter.alphabet = new ArrayList<>();
        Letter.alphabet.add(new Letter('א', "אלף", R.color.letterAleph, R.drawable.aleph));
        Letter.alphabet.add(new Letter('ב', "בית", R.color.letterBet, R.drawable.bet));
        Letter.alphabet.add(new Letter('ג', "גימל", R.color.letterGimel, R.drawable.gimel));
        Letter.alphabet.add(new Letter('ד', "דלת", R.color.letterDalet,  R.drawable.dalet));
    }

    public static void createWordList(Resources res) {
        Map<Character, String[]> wordList = new HashMap<>();
        wordList.put('א', res.getStringArray(R.array.aleph));
        wordList.put('ב', res.getStringArray(R.array.bet));
        wordList.put('ג', res.getStringArray(R.array.gimel));
        wordList.put('ד', res.getStringArray(R.array.dalet));
        Letter.exampleWords = wordList;
    }
}
