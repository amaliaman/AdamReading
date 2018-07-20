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
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amaliapps.adamreading.R;
import com.amaliapps.adamreading.helper.Utils;
import com.amaliapps.adamreading.model.Letter;

import java.util.LinkedList;
import java.util.List;

/**
 * An adapter that manages current letter details in LetterActivity
 */
public class LetterDetailsRecyclerViewAdapter
        extends RecyclerView.Adapter<LetterDetailsRecyclerViewAdapter.LetterDetailsViewHolder> {

    /**
     * The relative size of the highlighted first letter in the example word
     */
    private final static float HIGHLIGHT_RATIO = 1.5F;

    /**
     * The amount of HUE (in hsv) to change in every iteration of highlighting the example word
     */
    private final static int HUE_DELTA = 45;


    /**
     * The {@link Context} passed from the activity
     */
    private Context mContext;

    /**
     * The {@link Context} cast to {@link AppCompatActivity}
     */
    private AppCompatActivity mActivity;

    /**
     * The list of {@link Letter}s
     */
    private List<Letter> mLetters;

    /**
     * The {@link Letter}
     */
    private Letter mAttached;

    /**
     * A fifo list to save last 2 attached {@link Letter}s,
     * used to revert to the last {@link Letter} in case swiping was cancelled
     */
    private LinkedList<Letter> mAttachedFifo;

    /**
     * Constructor with {@link Context} and list of {@link Letter}s
     *
     * @param context the activity
     * @param letters the list of {@link Letter}s
     */
    public LetterDetailsRecyclerViewAdapter(Context context, List<Letter> letters) {
        this.mLetters = letters;
        this.mContext = context;
    }

    /**
     * The view holder that saves references to relevant views
     */
    static class LetterDetailsViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        ViewGroup wrapper;
        ViewGroup letterPager;
        ImageButton icon;
        ImageView prev;
        ImageView next;

        LetterDetailsViewHolder(View itemView) {
            super(itemView);

            // Get references to views inside the CardView
            nameTextView = itemView.findViewById(R.id.name);
            wrapper = itemView.findViewById(R.id.wrapper);
            letterPager = itemView.findViewById(R.id.letter_pager);
            icon = itemView.findViewById(R.id.letter_icon);
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
    public void onViewDetachedFromWindow(@NonNull LetterDetailsViewHolder holder) {
        final int currentPosition = holder.getAdapterPosition();
        final Letter currentLetter = mLetters.get(currentPosition);
        Letter previous = mAttachedFifo.removeFirst();

        // In case swiping a letter was cancelled, revert to the previous letter
        if (mAttached.getName().equals(currentLetter.getName())) {
            Utils.changeActivityTheme(mActivity, previous);
            Utils.setActivitySubtitle(mActivity, previous);
        }
        super.onViewDetachedFromWindow(holder);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        // Initiate members once
        mAttachedFifo = new LinkedList<>();
        mActivity = (AppCompatActivity) mContext;
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onViewAttachedToWindow(@NonNull final LetterDetailsViewHolder holder) {
        final int currentPosition = holder.getAdapterPosition();
        final Letter currentLetter = mLetters.get(currentPosition);

        // Save attached letter in fifo list
        mAttachedFifo.add(currentLetter);

        // Change action bar colour and subtitle
        Utils.changeActivityTheme(mActivity, currentLetter);
        Utils.setActivitySubtitle(mActivity, currentLetter);

        // Handle icon clicks
        holder.icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = mContext.getResources().getStringArray(R.array.iconDescriptions)[currentPosition];
                Toast.makeText(mContext, title, Toast.LENGTH_SHORT).show();
            }
        });

        // Save currently attached letter
        mAttached = currentLetter;
        super.onViewAttachedToWindow(holder);
    }

    @Override
    public void onBindViewHolder(@NonNull LetterDetailsViewHolder holder, int position) {
        Letter mLetter = mLetters.get(position);
        int letterColour = mContext.getResources().getColor(mLetter.getColorResourceId());

        // Bind current values to views
        holder.icon.setImageResource(mLetter.getIconResourceId());
        holder.icon.setContentDescription(mLetter.getName());

        holder.nameTextView.setText(String.valueOf(mLetter.getName()));

        holder.letterPager.setBackgroundColor(letterColour);

        // Populate word list with contents according to current letter
        String[] words = Letter.exampleWords.get(mLetter.getCharacter());
        holder.wrapper.removeAllViewsInLayout();
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

        // Setup scroll indicators - set to visible if it's not the first/last letter
        if (position > 0) {
            holder.prev.setVisibility(View.VISIBLE);
        } else {
            holder.prev.setVisibility(View.INVISIBLE);
        }
        if (position < Letter.alphabet.size() - 1) {
            holder.next.setVisibility(View.VISIBLE);
        } else {
            holder.next.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return mLetters.size();
    }
}
