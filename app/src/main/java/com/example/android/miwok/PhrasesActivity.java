package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class PhrasesActivity extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_list);
        final ArrayList<Word> words = new ArrayList<Word>() {
            {
                add(new Word("Where are you going?", "minto wuksus", R.raw.phrase_where_are_you_going));
                add(new Word("What is your name?", "tinnә oyaase'nә", R.raw.phrase_what_is_your_name));
                add(new Word("My name is...", "oyaaset...", R.raw.phrase_my_name_is));
                add(new Word("How are you feeling?", "michәksәs?", R.raw.phrase_how_are_you_feeling));
                add(new Word("I’m feeling good.", "kuchi achit", R.raw.phrase_im_feeling_good));
                add(new Word("Are you coming?", "әәnәs'aa?", R.raw.phrase_are_you_coming));
                add(new Word("Yes, I’m coming.", "hәә’ әәnәm", R.raw.phrase_yes_im_coming));
                add(new Word("I’m coming.", "әәnәm", R.raw.phrase_im_coming));
                add(new Word("Let’s go.", "yoowutis", R.raw.phrase_lets_go));
                add(new Word("Come here.", "әnni'nem", R.raw.phrase_come_here));
            }
        };

        WordAdapter wordAdapter = new WordAdapter(this, words, R.color.category_phrases);

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
