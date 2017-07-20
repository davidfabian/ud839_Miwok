package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;


public class PhrasesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_list);
        ArrayList<Word> words = new ArrayList<Word>() {
            {
                add(new Word("Where are you going?", "minto wuksus"));
                add(new Word("What is your name?", "tinnә oyaase'nә"));
                add(new Word("My name is...", "oyaaset..."));
                add(new Word("How are you feeling?", "michәksәs?"));
                add(new Word("I’m feeling good.", "kuchi achit"));
                add(new Word("Are you coming?", "әәnәs'aa?"));
                add(new Word("Yes, I’m coming.", "hәә’ әәnәm"));
                add(new Word("I’m coming.", "әәnәm"));
                add(new Word("Let’s go.", "yoowutis"));
                add(new Word("Come here.", "әnni'nem"));
            }
        };

        WordAdapter wordAdapter = new WordAdapter(this, words);

        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(wordAdapter);
    }
}
