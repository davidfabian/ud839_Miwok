package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class FamilyActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_list);
        final ArrayList<Word> words = new ArrayList<Word>() {
            {
                add(new Word("father", "әpә", R.drawable.family_father, R.raw.family_father));
                add(new Word("mother", "әṭa", R.drawable.family_mother, R.raw.family_mother));
                add(new Word("son", "angsi", R.drawable.family_son, R.raw.family_son));
                add(new Word("daughter", "tune", R.drawable.family_daughter, R.raw.family_daughter));
                add(new Word("older brother", "taachi", R.drawable.family_older_brother, R.raw.family_older_brother));
                add(new Word("younger brother", "chalitti", R.drawable.family_younger_brother, R.raw.family_younger_brother));
                add(new Word("older sister", "teṭe", R.drawable.family_older_sister, R.raw.family_older_sister));
                add(new Word("younger sister", "kolliti", R.drawable.family_younger_sister, R.raw.family_younger_sister));
                add(new Word("grandmother", "ama", R.drawable.family_grandmother, R.raw.family_grandmother));
                add(new Word("grandfather", "paapa", R.drawable.family_grandfather, R.raw.family_grandfather));

            }
        };

        WordAdapter wordAdapter = new WordAdapter(this, words, R.color.category_family);

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
