package com.amaliapps.adamreading.model;

import java.util.List;
import java.util.Map;

public class Letter {

    private char mCharacter;
    private String mName;
    private int mColorResourceId;
    private int mIconResourceId;

    public static List<Letter> alphabet;
    public static Map<Character, String[]> exampleWords;

    public Letter(char character, String name, int colorResourceId, int iconResourceId) {
        this.mCharacter = character;
        this.mName = name;
        this.mColorResourceId = colorResourceId;
        this.mIconResourceId = iconResourceId;
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
    public int getIconResourceId() {
        return mIconResourceId;
    }

    @Override
    public String toString() {
        return mName;
    }
}
