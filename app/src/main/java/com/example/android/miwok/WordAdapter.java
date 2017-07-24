package com.example.android.miwok;

import android.app.Activity;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by d on 20/07/2017.
 */

public class WordAdapter extends ArrayAdapter<Word> {
    public WordAdapter(Activity context, ArrayList<Word> words) {

        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, words);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        Word currentWord = getItem(position);


        // Find the TextView in the list_item.xml layout with the ID miwok_translation
        ImageView image = (ImageView) listItemView.findViewById(R.id.image);
        if (currentWord.getImageREsourceId() != 0) {
            // Get the miwok translation of the current word object
            // set this text on the number TextView
            image.setImageResource(currentWord.getImageREsourceId());
            image.setVisibility(View.VISIBLE);
        } else {
            image.setVisibility(View.INVISIBLE);
        }

        // Find the TextView in the list_item.xml layout with the ID default_translation
        TextView defaultTranslationTextView = (TextView) listItemView.findViewById(R.id.default_translation);
        // Get the default translation of the current word object
        // set this text on the name TextView
        defaultTranslationTextView.setText(currentWord.getDefaultTranslation());

        // Find the TextView in the list_item.xml layout with the ID miwok_translation
        TextView miwokTranslationTextView = (TextView) listItemView.findViewById(R.id.miwok_translation);
        // Get the miwok translation of the current word object
        // set this text on the number TextView
        miwokTranslationTextView.setText(currentWord.getMiwokTranslation());

        // Return the whole list item layout (containing 2 TextViews )
        // so that it can be shown in the ListView
        return listItemView;
    }

}
