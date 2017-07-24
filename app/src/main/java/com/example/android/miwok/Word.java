package com.example.android.miwok;

/**
 * Created by d on 20/07/2017.
 * custom class for storing english(default)-miwok word pairs.
 */

public class Word {
    private static int NO_IMAGE = -1;
    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int mImage = NO_IMAGE;


    public Word(String defaultTranslation, String miwokTranslation, int image) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImage = image;
    }

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
     *
     * @return miwok translation
     */
    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    /**
     * returns the image address. default value is out of range
     *
     * @return image address
     */

    public int getImageREsourceId() {
        return mImage;
    }

    /**
     * returns if the instance has an image
     *
     * @return TRUE if image is present, FALSE if not.
     */
    public boolean hasImage() {
        return mImage != NO_IMAGE;
    }
}
