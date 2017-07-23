package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;


public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_list);
        ArrayList<Word> words = new ArrayList<Word>() {
            {
                add(new Word("red", "weṭeṭṭi", R.drawable.color_red));
                add(new Word("green", "chokokki", R.drawable.color_green));
                add(new Word("brown", "ṭakaakki", R.drawable.color_brown));
                add(new Word("gray", "ṭopoppi", R.drawable.color_gray));
                add(new Word("black", "kululli", R.drawable.color_black));
                add(new Word("white", "kelelli", R.drawable.color_white));

                add(new Word("dusty yellow", "ṭopiisә", R.drawable.color_dusty_yellow));

                add(new Word("mustard yellow", "chiwiiṭә", R.drawable.color_mustard_yellow));

            }
        };

        WordAdapter wordAdapter = new WordAdapter(this, words);

        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(wordAdapter);
    }
}
