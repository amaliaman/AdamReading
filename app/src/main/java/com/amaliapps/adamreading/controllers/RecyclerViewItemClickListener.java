package com.amaliapps.adamreading.controllers;

/**
 * An Interface that enables setting {@link android.widget.AdapterView.OnItemClickListener}s in
 * {@link android.support.v7.widget.CardView}
 */
public interface RecyclerViewItemClickListener {
    void onItemClick(int position);
}
