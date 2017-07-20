package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class FamilyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);
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
