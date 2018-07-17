package com.amaliapps.adamreading.helper;

import android.content.res.Resources;

import com.amaliapps.adamreading.R;
import com.amaliapps.adamreading.model.Letter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Initializer {
//    public static boolean isInitialized = false;

    public static void initialize(Resources res) {
        createAlphabet(res);
        createWordList(res);
//        isInitialized = true;
    }

    private static void createAlphabet(Resources res) {
        String[] letterNames = res.getStringArray(R.array.letterNames);
        Letter.alphabet = new ArrayList<>();
        Letter.alphabet.add(new Letter('א', letterNames[Letter.alphabet.size()], R.color.letterAleph, R.drawable.aleph));
        Letter.alphabet.add(new Letter('ב', letterNames[Letter.alphabet.size()], R.color.letterBet, R.drawable.bet));
        Letter.alphabet.add(new Letter('ג', letterNames[Letter.alphabet.size()], R.color.letterGimel, R.drawable.gimel));
        Letter.alphabet.add(new Letter('ד', letterNames[Letter.alphabet.size()], R.color.letterDalet, R.drawable.dalet));
        Letter.alphabet.add(new Letter('ה', letterNames[Letter.alphabet.size()], R.color.letterHe, R.drawable.he));
        Letter.alphabet.add(new Letter('ו', letterNames[Letter.alphabet.size()], R.color.letterVav, R.drawable.vav));
//        Log.d("===", Arrays.toString(Letter.alphabet.toArray()));
    }

    private static void createWordList(Resources res) {
        Map<Character, String[]> wordHashMap = new HashMap<>();
        wordHashMap.put('א', res.getStringArray(R.array.aleph));
        wordHashMap.put('ב', res.getStringArray(R.array.bet));
        wordHashMap.put('ג', res.getStringArray(R.array.gimel));
        wordHashMap.put('ד', res.getStringArray(R.array.dalet));
        wordHashMap.put('ה', res.getStringArray(R.array.he));
        wordHashMap.put('ו', res.getStringArray(R.array.vav));
        Letter.exampleWords = wordHashMap;
//        Log.d("===", Arrays.toString(wordHashMap.values().toArray()));
    }
}
