package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class PhrasesFragment extends Fragment {

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

        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

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

        WordAdapter wordAdapter = new WordAdapter(getActivity(), words, R.color.category_phrases);

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