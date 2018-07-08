package com.amaliapps.adamreading;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.Map;

public class Letter implements Comparable<Letter>, Parcelable {

    private char mCharacter;
    private String mName;
    // todo: category/type
    // todo: color
    public static Map<Character, String[]> sWords;

    Letter(char character, String name) {
        this.mCharacter = character;
        this.mName = name;
    }

    private Letter(Parcel in) {
        mCharacter = (char) in.readInt();
        mName = in.readString();
    }

    public static final Creator<Letter> CREATOR = new Creator<Letter>() {
        @Override
        public Letter createFromParcel(Parcel in) {
            return new Letter(in);
        }

        @Override
        public Letter[] newArray(int size) {
            return new Letter[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt((int) mCharacter);
        parcel.writeString(mName);
    }
}
