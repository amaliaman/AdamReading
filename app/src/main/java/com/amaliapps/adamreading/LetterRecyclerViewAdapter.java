package com.amaliapps.adamreading;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class LetterRecyclerViewAdapter extends RecyclerView.Adapter<LetterRecyclerViewAdapter.LetterViewHolder> {
    private Context mContext;
    private List<Letter> mLetters;

    LetterRecyclerViewAdapter(Context context, List<Letter> letters) {
        this.mLetters = letters;
        this.mContext = context;
    }

    static class LetterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cardView;
        TextView letterCharacter;
        TextView letterName;
        RecyclerViewItemClickListener itemClickListener;

        LetterViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            // Get a reference to the CardView
            cardView = itemView.findViewById(R.id.card_view);
            // Get references to views inside the CardView
            letterCharacter = itemView.findViewById(R.id.character);
            letterName = itemView.findViewById(R.id.name);
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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new LetterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull LetterViewHolder holder, int position) {
        holder.letterCharacter.setText(String.valueOf(mLetters.get(position).getCharacter()));
        holder.letterName.setText(String.valueOf(mLetters.get(position).getName()));

        holder.setItemClickListener(new RecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(mContext, LetterActivity.class);
                intent.putExtra("ggg", mLetters.get(position));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mLetters.size();
    }
}
