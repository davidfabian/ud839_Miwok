package com.example.android.miwok;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.net.URI;
import java.util.ArrayList;


public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_list);


        final ArrayList<Word> words = new ArrayList<Word>() {
            {
                add(new Word("one", "lutti", R.drawable.number_one, R.raw.number_one));
                add(new Word("two", "otiiko", R.drawable.number_two, R.raw.number_two));
                add(new Word("three", "tolookosu", R.drawable.number_three, R.raw.number_three));
                add(new Word("four", "oyyisa", R.drawable.number_four, R.raw.number_four));
                add(new Word("five", "massokka", R.drawable.number_five, R.raw.number_five));
                add(new Word("six", "temmokka", R.drawable.number_six, R.raw.number_six));
                add(new Word("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
                add(new Word("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
                add(new Word("nine", "wo’e", R.drawable.number_nine, R.raw.number_nine));
                add(new Word("ten", "na’aacha", R.drawable.number_ten, R.raw.number_ten));
            }
        };

        WordAdapter wordAdapter = new WordAdapter(this, words, R.color.category_numbers);

        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(wordAdapter);
        //set up listener for any item clicked in the generated listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int arg2, long arg3) {
                //when item clicked, this creates a mediaplayer instance. The context is this activity, the source sound is the clicked item's sound retrieved with getmSound() method.
                mMediaPlayer = MediaPlayer.create(getApplicationContext(), words.get(arg2).getmSound());
                //starts mediaplayer
                mMediaPlayer.start();
            }
        });
    }
}
