package com.amaliapps.adamreading.activities;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.amaliapps.adamreading.BuildConfig;
import com.amaliapps.adamreading.R;
import com.amaliapps.adamreading.controllers.LettersRecyclerViewAdapter;
import com.amaliapps.adamreading.controllers.VarColumnGridLayoutManager;
import com.amaliapps.adamreading.helper.Initializer;
import com.amaliapps.adamreading.helper.Utils;
import com.amaliapps.adamreading.model.Letter;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    /**
     * Width of a single letter card in grid
     */
    private static final int MIN_ITEM_WIDTH = 360;

    /**
     * Amount of elevation of 'about' {@link PopupWindow}
     */
    private static final float POPUP_ELEVATION = 5f;

    /**
     * Key for letter position extra
     */
    public static final String LETTER_POSITION_EXTRA = MainActivity.class.getPackage().getName() +
            "." + MainActivity.class.getSimpleName() + ".LETTER_POSITION_EXTRA";

    /**
     * A reference to the {@link RecyclerView} that handles the grid of all {@link Letter}s
     */
    RecyclerView mRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set Hebrew locale
        Utils.setHebrewLocale(this);

        // Populate alphabet list
        Initializer.initialize(getResources());

        // Get a reference to the recycler view
        mRecycler = findViewById(R.id.recycler_view);
        mRecycler.setHasFixedSize(true);

        // Setup a layout manager
        GridLayoutManager layoutManager = new VarColumnGridLayoutManager(this, MIN_ITEM_WIDTH);
        mRecycler.setLayoutManager(layoutManager);

        // Attach adapter to recycler view
        LettersRecyclerViewAdapter adapter = new LettersRecyclerViewAdapter(this, Letter.alphabet);
        mRecycler.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // 'About' option chosen
            case R.id.about:
                displayAboutPopup();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void displayAboutPopup() {
        // Instantiate the layout file
        LayoutInflater layoutInflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewGroup popupRoot = findViewById(R.id.popup_root);
        View customView = Objects.requireNonNull(layoutInflater).inflate(R.layout.popup_about, popupRoot);
        ImageButton closePopupBtn = customView.findViewById(R.id.closePopupBtn);

        // Instantiate popup window
        final PopupWindow popupWindow = new PopupWindow(customView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            popupWindow.setElevation(POPUP_ELEVATION);
        }

        //display the popup window
        popupWindow.showAtLocation(mRecycler, Gravity.CENTER, 0, 0);

        // Set the current version
        TextView version = popupWindow.getContentView().findViewById(R.id.about_version);
        version.setText(BuildConfig.VERSION_NAME);

        // Dim the background
        Utils.dimBehind(popupWindow);

        // Close the popup window on button click
        closePopupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }
}
