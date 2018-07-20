package com.amaliapps.adamreading.helper;

import android.content.res.Resources;

import com.amaliapps.adamreading.R;
import com.amaliapps.adamreading.model.Letter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A helper class that creates the alphabet list and example words for each {@link Letter}
 */
public class Initializer {

    public static void initialize(Resources res) {
        createAlphabet(res);
        createWordList(res);
    }

    private static void createAlphabet(Resources res) {
        String[] letterNames = res.getStringArray(R.array.letterNames);
        Letter.alphabet = new ArrayList<>();
        Letter.alphabet.add(new Letter('א', letterNames[Letter.alphabet.size()], R.color.aleph, R.drawable.aleph));
        Letter.alphabet.add(new Letter('ב', letterNames[Letter.alphabet.size()], R.color.bet, R.drawable.bet));
        Letter.alphabet.add(new Letter('ג', letterNames[Letter.alphabet.size()], R.color.gimel, R.drawable.gimel));
        Letter.alphabet.add(new Letter('ד', letterNames[Letter.alphabet.size()], R.color.dalet, R.drawable.dalet));
        Letter.alphabet.add(new Letter('ה', letterNames[Letter.alphabet.size()], R.color.he, R.drawable.he));
        Letter.alphabet.add(new Letter('ו', letterNames[Letter.alphabet.size()], R.color.vav, R.drawable.vav));
        Letter.alphabet.add(new Letter('ז', letterNames[Letter.alphabet.size()], R.color.zayin, R.drawable.zayin));
        Letter.alphabet.add(new Letter('ח', letterNames[Letter.alphabet.size()], R.color.het, R.drawable.het));
        Letter.alphabet.add(new Letter('ט', letterNames[Letter.alphabet.size()], R.color.tet, R.drawable.tet));
        Letter.alphabet.add(new Letter('י', letterNames[Letter.alphabet.size()], R.color.yod, R.drawable.yod));
        Letter.alphabet.add(new Letter('כ', letterNames[Letter.alphabet.size()], R.color.kaf, R.drawable.kaf));
        Letter.alphabet.add(new Letter('ל', letterNames[Letter.alphabet.size()], R.color.lamed, R.drawable.lamed));
        Letter.alphabet.add(new Letter('מ', letterNames[Letter.alphabet.size()], R.color.mem, R.drawable.mem));
        Letter.alphabet.add(new Letter('נ', letterNames[Letter.alphabet.size()], R.color.nun, R.drawable.nun));
        Letter.alphabet.add(new Letter('ס', letterNames[Letter.alphabet.size()], R.color.samekh, R.drawable.samekh));
        Letter.alphabet.add(new Letter('ע', letterNames[Letter.alphabet.size()], R.color.ayin, R.drawable.ayin));
        Letter.alphabet.add(new Letter('פ', letterNames[Letter.alphabet.size()], R.color.pe, R.drawable.pe));
        Letter.alphabet.add(new Letter('צ', letterNames[Letter.alphabet.size()], R.color.tsadi, R.drawable.tsadi));
        Letter.alphabet.add(new Letter('ק', letterNames[Letter.alphabet.size()], R.color.qof, R.drawable.qof));
        Letter.alphabet.add(new Letter('ר', letterNames[Letter.alphabet.size()], R.color.resh, R.drawable.resh));
        Letter.alphabet.add(new Letter('ש', letterNames[Letter.alphabet.size()], R.color.shin, R.drawable.shin));
        Letter.alphabet.add(new Letter('ת', letterNames[Letter.alphabet.size()], R.color.tav, R.drawable.tav));
    }

    private static void createWordList(Resources res) {
        Map<Character, String[]> wordHashMap = new HashMap<>();
        wordHashMap.put('א', res.getStringArray(R.array.aleph));
        wordHashMap.put('ב', res.getStringArray(R.array.bet));
        wordHashMap.put('ג', res.getStringArray(R.array.gimel));
        wordHashMap.put('ד', res.getStringArray(R.array.dalet));
        wordHashMap.put('ה', res.getStringArray(R.array.he));
        wordHashMap.put('ו', res.getStringArray(R.array.vav));
        wordHashMap.put('ז', res.getStringArray(R.array.zayin));
        wordHashMap.put('ח', res.getStringArray(R.array.het));
        wordHashMap.put('ט', res.getStringArray(R.array.tet));
        wordHashMap.put('י', res.getStringArray(R.array.yod));
        wordHashMap.put('כ', res.getStringArray(R.array.kaf));
        wordHashMap.put('ל', res.getStringArray(R.array.lamed));
        wordHashMap.put('מ', res.getStringArray(R.array.mem));
        wordHashMap.put('נ', res.getStringArray(R.array.nun));
        wordHashMap.put('ס', res.getStringArray(R.array.samekh));
        wordHashMap.put('ע', res.getStringArray(R.array.ayin));
        wordHashMap.put('פ', res.getStringArray(R.array.pe));
        wordHashMap.put('צ', res.getStringArray(R.array.tsadi));
        wordHashMap.put('ק', res.getStringArray(R.array.qof));
        wordHashMap.put('ר', res.getStringArray(R.array.resh));
        wordHashMap.put('ש', res.getStringArray(R.array.shin));
        wordHashMap.put('ת', res.getStringArray(R.array.tav));
        Letter.exampleWords = wordHashMap;
    }
}
