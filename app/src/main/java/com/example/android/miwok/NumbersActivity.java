package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);
        ArrayList<String> words = new ArrayList<String>() {
            {
                add("one");
                add("two");
                add("three");
                add("four");
                add("five");
                add("six");
                add("seven");
                add("eight");
                add("nine");
                add("ten");
            }
        };
        //get the rootview
        LinearLayout rootView = (LinearLayout) findViewById(R.id.rootView);
        //loop from index 0 to 9,

        for (int index = 0; index < words.size(); index++) {
            //create a new textView for each index
            TextView numberView = new TextView(this);
            //and add the corresponding number text to the textViews
            numberView.setText(words.get(index));
            numberView.setId(index);
            //add current TextView to the end of rootview as a childview
            rootView.addView(numberView);
        }
    }
}
