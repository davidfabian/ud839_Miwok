package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class FamilyActivity extends AppCompatActivity {

    //create mediaplayer
    private MediaPlayer mMediaPlayer;

    //create audiomanager
    private AudioManager mAudioManager;

    //set up audiofocuschangelistener.
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.

                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                releaseMediaPlayer();
            }
        }
    };
    private MediaPlayer.OnCompletionListener mPlayerListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_list);
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
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
            public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {

                //reset mediaplayer before
                releaseMediaPlayer();
                //when clicked, prepares current word.
                Word currentWord = words.get(position);

                //request audiofocus transient (it's short audio)
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                //when focus granted, proceed with playing sound
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    //get the correct audiofile
                    mMediaPlayer = MediaPlayer.create(getApplicationContext(), currentWord.getmSound());
                    //starts mediaplayer
                    mMediaPlayer.start();

                    Log.i("wordcontent", "words: " + currentWord);

                    mMediaPlayer.setOnCompletionListener(mPlayerListener);
                }
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("app stopped", "app stopped");
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        Log.i("cleaner called", "before release");

        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {

            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
            Log.i("memory cleaned", "memory cleaner run, mediaplayer = null");

            //release audiofocus regardless of we had it or not.
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }


}
