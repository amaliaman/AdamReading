package com.amaliapps.adamreading;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.Map;

public class Letter implements Comparable<Letter>, Parcelable {

    // todo: explain in comments
    private char mCharacter;
    private String mName;
    private int mColorResourceId;
    // todo: category/type

    public static Map<Character, String[]> sWords;

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

    @Override
    public String toString() {
        return mName;
    }

    // todo: needed?
    // Implementation of Comparable interface
    @Override
    public int compareTo(@NonNull Letter letter) {
        return Character.compare(this.mCharacter, letter.mCharacter);
    }

    // Implementation of Parcelable interface
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

    @Override
    public int describeContents() {
        return 0;
    }

    private Letter(Parcel in) {
        mCharacter = (char) in.readInt();
        mName = in.readString();
        mColorResourceId = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt((int) mCharacter);
        parcel.writeString(mName);
        parcel.writeInt((int) mColorResourceId);
    }
}
