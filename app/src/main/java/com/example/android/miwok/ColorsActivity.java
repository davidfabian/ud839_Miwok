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
                add(new Word("red", "weṭeṭṭi"));
                add(new Word("green", "chokokki"));
                add(new Word("brown", "ṭakaakki"));
                add(new Word("gray", "ṭopoppi"));
                add(new Word("black", "kululli"));
                add(new Word("white", "kelelli"));
                add(new Word("older sister", "teṭe"));
                add(new Word("dusty yellow", "ṭopiisә"));
                add(new Word("grandmother", "ama"));
                add(new Word("mustard yellow", "chiwiiṭә"));

            }
        };

        WordAdapter wordAdapter = new WordAdapter(this, words);

        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(wordAdapter);
    }
}
