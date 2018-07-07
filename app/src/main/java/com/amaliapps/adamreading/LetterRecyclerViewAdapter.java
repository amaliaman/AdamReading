package com.amaliapps.adamreading;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class LetterRecyclerViewAdapter extends RecyclerView.Adapter<LetterRecyclerViewAdapter.LetterViewHolder> {

    static class LetterViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView letterCharacter;
        TextView letterName;

        LetterViewHolder(View itemView) {
            super(itemView);
            // Get a reference to the CardView
            cardView = itemView.findViewById(R.id.card_view);
            letterCharacter = itemView.findViewById(R.id.character);
            letterName = itemView.findViewById(R.id.name);
        }
    }

    private List<Letter> letters;

    LetterRecyclerViewAdapter(List<Letter> letters) {
        this.letters = letters;
    }

    @NonNull
    @Override
    public LetterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new LetterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull LetterViewHolder holder, int position) {
//        holder.letterCharacter.setText("aaaaaaa");
        holder.letterCharacter.setText(String.valueOf(letters.get(position).getCharacter()));
        holder.letterName.setText(String.valueOf(letters.get(position).getName()));
    }

    @Override
    public int getItemCount() {
        return letters.size();
    }
}
