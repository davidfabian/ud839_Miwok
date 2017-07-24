package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;


public class ColorsActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_list);
        final ArrayList<Word> words = new ArrayList<Word>() {
            {
                add(new Word("red", "weṭeṭṭi", R.drawable.color_red, R.raw.color_red));
                add(new Word("green", "chokokki", R.drawable.color_green, R.raw.color_green));
                add(new Word("brown", "ṭakaakki", R.drawable.color_brown, R.raw.color_brown));
                add(new Word("gray", "ṭopoppi", R.drawable.color_gray, R.raw.color_gray));
                add(new Word("black", "kululli", R.drawable.color_black, R.raw.color_black));
                add(new Word("white", "kelelli", R.drawable.color_white, R.raw.color_white));
                add(new Word("dusty yellow", "ṭopiisә", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
                add(new Word("mustard yellow", "chiwiiṭә", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));

            }
        };

        WordAdapter wordAdapter = new WordAdapter(this, words, R.color.category_colors);

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
