package com.amaliapps.adamreading.model;

import java.util.List;
import java.util.Map;

public class Letter {

    private char mCharacter;
    private String mName;
    private int mColorResourceId;

    public static List<Letter> alphabet;
    public static Map<Character, String[]> exampleWords;

    public Letter(char character, String name, int colorResourceId) {
        this.mCharacter = character;
        this.mName = name;
        this.mColorResourceId = colorResourceId;
    }

    public char getCharacter() {
        return mCharacter;
    }

    public String getName() {
        return mName;
    }

    public int getColorResourceId() {
        return mColorResourceId;
    }

    public Letter getNext() {
        int currentIndex = alphabet.indexOf(this);
        if (currentIndex < alphabet.size() - 1) {
            return alphabet.get(currentIndex + 1);
        } else return null;
    }

    public Letter getPrevious() {
        int currentIndex = alphabet.indexOf(this);
        if (currentIndex > 0) {
            return alphabet.get(currentIndex - 1);
        } else return null;
    }

    @Override
    public String toString() {
        return mName;
    }
}
