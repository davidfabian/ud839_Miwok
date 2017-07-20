package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;


public class FamilyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_list);
        ArrayList<Word> words = new ArrayList<Word>() {
            {
                add(new Word("father", "әpә"));
                add(new Word("mother", "әṭa"));
                add(new Word("son", "angsi"));
                add(new Word("daughter", "tune"));
                add(new Word("older brother", "taachi"));
                add(new Word("younger brother", "chalitti"));
                add(new Word("older sister", "teṭe"));
                add(new Word("younger sister", "kolliti"));
                add(new Word("grandmother", "ama"));
                add(new Word("grandfather", "paapa"));

            }
        };

        WordAdapter wordAdapter = new WordAdapter(this, words);

        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(wordAdapter);
    }
}
