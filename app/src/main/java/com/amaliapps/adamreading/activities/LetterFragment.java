package com.amaliapps.adamreading.activities;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.amaliapps.adamreading.R;
import com.amaliapps.adamreading.helpers.Constants;
import com.amaliapps.adamreading.helpers.Utils;
import com.amaliapps.adamreading.model.Letter;

import java.util.Objects;

public class LetterFragment extends Fragment {
    public static final String BUNDLE_LETTER_POSITION = MainActivity.class.getPackage().getName() +
            "." + MainActivity.class.getSimpleName() + ".BUNDLE_LETTER_POSITION";
    Letter mLetter;

    public LetterFragment() {
        // Required empty public constructor
    }

    private int mPosition;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (getArguments() != null) {
            mPosition = getArguments().getInt(BUNDLE_LETTER_POSITION);
        }
        mLetter = Letter.alphabet.get(mPosition);
        if(getUserVisibleHint()){
            changeActivityColors();
        }
    }

    public void changeActivityColors() {
        AppCompatActivity aca = (AppCompatActivity) getActivity();
        if (aca != null) {
            Objects.requireNonNull(aca.getSupportActionBar()).setBackgroundDrawable(
                    new ColorDrawable(getResources().getColor(mLetter.getColorResourceId())));
        }
        Utils.darkenStatusBar(getActivity(), mLetter.getColorResourceId());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_letter, container, false);
        rootView.setRotationY(Constants.ROTATION);

        // Set TextViews contents according to current letter
        TextView characterTextView = rootView.findViewById(R.id.character);
        characterTextView.setText(String.valueOf(mLetter.getCharacter()));

        TextView nameTextView = rootView.findViewById(R.id.name);
        nameTextView.setText(mLetter.getName());

        // Populate word list with contents according to current letter
        ViewGroup wrapper = rootView.findViewById(R.id.wrapper);
        String[] words = Letter.exampleWords.get(mLetter.getCharacter());
        for (String word : words) {
            TextView wordTextView = new TextView(rootView.getContext());
            wordTextView.setTextAppearance(rootView.getContext(), R.style.WordList);
            wordTextView.setText(word);
            wrapper.addView(wordTextView);
        }

        // Setup navigation buttons
        ImageButton prev = rootView.findViewById(R.id.prev);
        ImageButton next = rootView.findViewById(R.id.next);
        // Set to visible if it's not the first/last letter
        if (mLetter.getPrevious() != null) {
            prev.setVisibility(View.VISIBLE);
        }
        if (mLetter.getNext() != null) {
            next.setVisibility(View.VISIBLE);
        }
//        // Attach listener
//        prev.setOnClickListener(navigationListener);
//        next.setOnClickListener(navigationListener);


        return rootView;
    }

//    View.OnClickListener navigationListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            Intent intent = new Intent(view.getContext(), LetterActivity.class);
//            // Pass the mPosition of the letter in the alphabet
//            Letter target = null;
//
//            switch (view.getId()) {
//                case R.id.prev:
//                    target = letter.getPrevious();
//                    break;
//                case R.id.next:
//                    target = letter.getNext();
//                    break;
//            }
//
//            intent.putExtra(EXTRA_LETTER_POSITION, Letter.alphabet.indexOf(target));
//            startActivity(intent);
//        }
//    };
}
