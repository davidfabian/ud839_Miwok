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
                add(new Word("one", "lutti", R.drawable.number_one));
                add(new Word("two", "otiiko", R.drawable.number_two));
                add(new Word("three", "tolookosu", R.drawable.number_three));
                add(new Word("four", "oyyisa", R.drawable.number_four));
                add(new Word("five", "massokka", R.drawable.number_five));
                add(new Word("six", "temmokka", R.drawable.number_six));
                add(new Word("seven", "kenekaku", R.drawable.number_seven));
                add(new Word("eight", "kawinta", R.drawable.number_eight));
                add(new Word("nine", "wo’e", R.drawable.number_nine));
                add(new Word("ten", "na’aacha", R.drawable.number_ten));
            }
        };

        WordAdapter wordAdapter = new WordAdapter(this, words, R.color.category_numbers);

        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(wordAdapter);
    }
}
