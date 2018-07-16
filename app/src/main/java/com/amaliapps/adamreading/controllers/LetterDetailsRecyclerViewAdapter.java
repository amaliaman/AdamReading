package com.amaliapps.adamreading.controllers;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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

    private final static float HIGHLIGHT_RATIO = 1.75F;
    private final static int HUE_DELTA = 45;


    private Context mContext;
    private List<Letter> mLetters;
    private Letter mLetter;

    public LetterDetailsRecyclerViewAdapter(Context context, List<Letter> letters) {
        this.mLetters = letters;
        this.mContext = context;
    }

    static class LetterDetailsViewHolder extends RecyclerView.ViewHolder
            implements View.OnTouchListener {
        TextView characterTextView;
        TextView nameTextView;
        ViewGroup wrapper;
        ViewGroup letterPager;
        ImageView icon;
        ImageView prev;
        ImageView next;
        RecyclerViewOnItemTouchListener onItemTouchListener;

        LetterDetailsViewHolder(View itemView) {
            super(itemView);
            itemView.setOnTouchListener(this);

            // Get references to views inside the CardView
            characterTextView = itemView.findViewById(R.id.character);
            nameTextView = itemView.findViewById(R.id.name);
            wrapper = itemView.findViewById(R.id.wrapper);
            letterPager = itemView.findViewById(R.id.letter_pager);
            icon = itemView.findViewById(R.id.letter_icon);
            prev = itemView.findViewById(R.id.prev);
            next = itemView.findViewById(R.id.next);
        }

        void setOnItemTouchListener(RecyclerViewOnItemTouchListener onItemTouchListener) {
            this.onItemTouchListener = onItemTouchListener;
        }

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            this.onItemTouchListener.onItemTouch(this.getLayoutPosition(), motionEvent);
            return true;
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
//        Utils.setActivityTitle(activity, currentLetter);
    }

    @Override
    public void onBindViewHolder(@NonNull LetterDetailsViewHolder holder, int position) {
        mLetter = mLetters.get(position);
        int letterColour = mContext.getResources().getColor(mLetter.getColorResourceId());

        holder.icon.setImageResource(mLetter.getIconResourceId());
        holder.icon.setContentDescription(mLetter.getName());

        holder.characterTextView.setText(String.valueOf(mLetter.getCharacter()));
        holder.nameTextView.setText(String.valueOf(mLetter.getName()));
//        holder.nameTextView.setTextColor(letterColour);

        // Populate word list with contents according to current letter
        String[] words = Letter.exampleWords.get(mLetter.getCharacter());
        for (int i = 0; i < words.length; i++) {
            // Format the first letter of the word by using Spannable
            Spannable highlight = new SpannableString(words[i]);
            int newHue = Utils.changeColor(letterColour, ((float) i * HUE_DELTA));
            highlight.setSpan(new ForegroundColorSpan(newHue),
                    0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            highlight.setSpan(new RelativeSizeSpan(HIGHLIGHT_RATIO),
                    0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            highlight.setSpan(new StyleSpan(Typeface.BOLD),
                    0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            TextView wordTextView = new TextView(mContext);
            wordTextView.setTextAppearance(mContext, R.style.WordListItem);
            wordTextView.setText(highlight);
            holder.wrapper.addView(wordTextView);
        }

        // Setup scroll indicators
        // Set to visible if it's not the first/last letter
        if (position > 0) {
            holder.prev.setVisibility(View.VISIBLE);
        }
        if (position < Letter.alphabet.size() - 1) {
            holder.next.setVisibility(View.VISIBLE);
        }

        holder.letterPager.setBackgroundColor(letterColour);

        holder.setOnItemTouchListener(new RecyclerViewOnItemTouchListener() {
            @Override
            public void onItemTouch(int position, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_BUTTON_RELEASE) {
                    AppCompatActivity activity = (AppCompatActivity) mContext;
                    Utils.changeActivityTheme(activity, mLetter);
                    Utils.setActivityTitle(activity, mLetter);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mLetters.size();
    }
}
