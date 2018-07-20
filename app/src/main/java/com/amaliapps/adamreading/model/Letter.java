package com.amaliapps.adamreading.model;

import java.util.List;
import java.util.Map;

public class Letter {

    /**
     * The character of the letter
     */
    private char mCharacter;

    /**
     * The name of the letter
     */
    private String mName;

    /**
     * The id of the letter's colour, each letter is rendered in a different colour
     * defined in colors.xml
     */
    private int mColorResourceId;

    /**
     * The id of the icon of the letter in drawable folder, each letter has a unique icon
     */
    private int mIconResourceId;

    /**
     * The list of {@link Letter}s
     */
    public static List<Letter> alphabet;

    /**
     * The list of example words
     */
    public static Map<Character, String[]> exampleWords;

    /**
     * Constructor
     *
     * @param character       is The character of the letter
     * @param name            is the name of the letter
     * @param colorResourceId is the id of the icon of the letter in drawable folder
     * @param iconResourceId  is the id of the icon of the letter in drawable folder
     */
    public Letter(char character, String name, int colorResourceId, int iconResourceId) {
        this.mCharacter = character;
        this.mName = name;
        this.mColorResourceId = colorResourceId;
        this.mIconResourceId = iconResourceId;
    }

    // Getters
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
