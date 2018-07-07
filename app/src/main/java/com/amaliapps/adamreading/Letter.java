package com.amaliapps.adamreading;

import android.support.annotation.NonNull;

public class Letter implements Comparable<Letter> {
    private char mCharacter;
    private String mName;
    // todo: category/type
    // todo: color

    Letter(char character, String name) {
        this.mCharacter = character;
        this.mName = name;
    }

    public char getCharacter() {
        return mCharacter;
    }

    public String getName() {
        return mName;
    }

    @Override
    public int compareTo(@NonNull Letter letter) {
        return Character.compare(this.mCharacter, letter.mCharacter);
    }

    @Override
    public String toString() {
        return (mCharacter + " " + mName);
    }
}
