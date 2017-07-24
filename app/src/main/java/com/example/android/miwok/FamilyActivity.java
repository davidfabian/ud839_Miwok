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
                add(new Word("father", "әpә", R.drawable.family_father));
                add(new Word("mother", "әṭa", R.drawable.family_mother));
                add(new Word("son", "angsi", R.drawable.family_son));
                add(new Word("daughter", "tune", R.drawable.family_daughter));
                add(new Word("older brother", "taachi", R.drawable.family_older_brother));
                add(new Word("younger brother", "chalitti", R.drawable.family_younger_brother));
                add(new Word("older sister", "teṭe", R.drawable.family_older_sister));
                add(new Word("younger sister", "kolliti", R.drawable.family_younger_sister));
                add(new Word("grandmother", "ama", R.drawable.family_grandmother));
                add(new Word("grandfather", "paapa", R.drawable.family_grandfather));

            }
        };

        WordAdapter wordAdapter = new WordAdapter(this, words, R.color.category_family);

        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(wordAdapter);
    }
}
