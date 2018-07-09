package com.amaliapps.adamreading.controllers;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amaliapps.adamreading.R;
import com.amaliapps.adamreading.activities.LetterActivity;
import com.amaliapps.adamreading.helper.RecyclerViewItemClickListener;
import com.amaliapps.adamreading.model.Letter;

import java.util.List;

import static com.amaliapps.adamreading.activities.MainActivity.LETTER_POSITION_EXTRA;

public class LettersRecyclerViewAdapter extends RecyclerView.Adapter<LettersRecyclerViewAdapter.LetterViewHolder> {
    private Context mContext;
    private List<Letter> mLetters;

    public LettersRecyclerViewAdapter(Context context, List<Letter> letters) {
        this.mLetters = letters;
        this.mContext = context;
    }

    static class LetterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cardView;
        TextView letterCharacter;
        RecyclerViewItemClickListener itemClickListener;

        LetterViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            // Get a reference to the CardView
            cardView = itemView.findViewById(R.id.card_view);
            // Get references to views inside the CardView
            letterCharacter = itemView.findViewById(R.id.character);
        }

        @Override
        public void onClick(View view) {
            this.itemClickListener.onItemClick(this.getLayoutPosition());
        }

        void setItemClickListener(RecyclerViewItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }
    }

    @NonNull
    @Override
    public LetterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_card, parent, false);
        return new LetterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull LetterViewHolder holder, int position) {
        holder.letterCharacter.setText(String.valueOf(mLetters.get(position).getCharacter()));
        holder.letterCharacter.setTextColor(mContext.getResources().getColor(mLetters.get(position).getColorResourceId()));

        holder.setItemClickListener(new RecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(mContext, LetterActivity.class);
                // Pass the position of the letter in the alphabet
                intent.putExtra(LETTER_POSITION_EXTRA, Letter.alphabet.indexOf(mLetters.get(position)));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mLetters.size();
    }
}
