package com.example.android.miwok;

/**
 * Created by d on 20/07/2017.
 * custom class for storing english(default)-miwok word pairs.
 */

public class Word {
    private String mDefaultTranslation;
    private String mMiwokTranslation;

    public Word(String defaultTranslation, String miwokTranslation) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
    }

    /**
     * get default translation
     *
     * @return default translation
     */
    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    /**
     * get miwok translation
     * @return miwok translation
     */
    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }
}
