package com.amaliapps.adamreading.controllers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amaliapps.adamreading.R;
import com.amaliapps.adamreading.helper.Utils;
import com.amaliapps.adamreading.model.Letter;

import java.util.List;

public class LetterDetailsRecyclerViewAdapter
        extends RecyclerView.Adapter<LetterDetailsRecyclerViewAdapter.LetterDetailsViewHolder> {

    private Context mContext;
    private List<Letter> mLetters;
    private Letter mLetter;

    public LetterDetailsRecyclerViewAdapter(Context context, List<Letter> letters) {
        this.mLetters = letters;
        this.mContext = context;
    }

    static class LetterDetailsViewHolder extends RecyclerView.ViewHolder {
        TextView characterTextView;
        TextView nameTextView;
        ViewGroup wrapper;
        ImageView prev;
        ImageView next;

        LetterDetailsViewHolder(View itemView) {
            super(itemView);

            // Get references to views inside the CardView
            characterTextView = itemView.findViewById(R.id.character);
            nameTextView = itemView.findViewById(R.id.name);
            wrapper = itemView.findViewById(R.id.wrapper);
            prev = itemView.findViewById(R.id.prev);
            next = itemView.findViewById(R.id.next);
        }
    }

    @NonNull
    @Override
    public LetterDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.letter_details, parent, false);
        return new LetterDetailsViewHolder(v);
    }

    @Override
    public void onViewAttachedToWindow(@NonNull LetterDetailsViewHolder holder) {
        AppCompatActivity activity = (AppCompatActivity) mContext;
        Letter currentLetter = mLetters.get(holder.getAdapterPosition());

        Utils.changeActivityTheme(activity, currentLetter);
        Utils.setActivityTitle(activity, currentLetter);
    }

    @Override
    public void onBindViewHolder(@NonNull LetterDetailsViewHolder holder, int position) {
        mLetter = mLetters.get(position);

        holder.characterTextView.setText(String.valueOf(mLetter.getCharacter()));
        holder.nameTextView.setText(String.valueOf(mLetter.getName()));

        // Populate word list with contents according to current letter
        String[] words = Letter.exampleWords.get(mLetter.getCharacter());
        for (String word : words) {
            TextView wordTextView = new TextView(mContext);
            wordTextView.setTextAppearance(mContext, R.style.WordList);
            wordTextView.setText(word);
            holder.wrapper.addView(wordTextView);
        }

        // Setup scroll indicators
        // Set to visible if it's not the first/last letter
        if (mLetter.getPrevious() != null) {
            holder.prev.setVisibility(View.VISIBLE);
        }
        if (mLetter.getNext() != null) {
            holder.next.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return mLetters.size();
    }
}
