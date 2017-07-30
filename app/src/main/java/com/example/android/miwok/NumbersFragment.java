package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class NumbersFragment extends Fragment {

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

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.words_list, container, false);

        /** TODO: Insert all the code from the NumberActivity’s onCreate() method after the setContentView method call */
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

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

        WordAdapter wordAdapter = new WordAdapter(getActivity(), words, R.color.category_numbers);

        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = (ListView) rootView.findViewById(R.id.word_list);
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
                    mMediaPlayer = MediaPlayer.create(getActivity(), currentWord.getmSound());
                    //starts mediaplayer
                    mMediaPlayer.start();

                    Log.i("wordcontent", "words: " + currentWord);

                    mMediaPlayer.setOnCompletionListener(mPlayerListener);
                }
            }
        });
        return rootView;
    }


}