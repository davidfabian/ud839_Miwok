package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;


public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_list);
        ArrayList<Word> words = new ArrayList<Word>() {
            {
                add(new Word("one", "lutti"));
                add(new Word("two", "otiiko"));
                add(new Word("three", "tolookosu"));
                add(new Word("four", "oyyisa"));
                add(new Word("five", "massokka"));
                add(new Word("six", "temmokka"));
                add(new Word("seven", "kenekaku"));
                add(new Word("eight", "kawinta"));
                add(new Word("nine", "wo’e"));
                add(new Word("ten", "na’aacha"));
            }
        };

        WordAdapter wordAdapter = new WordAdapter(this, words);

        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(wordAdapter);
    }
}
