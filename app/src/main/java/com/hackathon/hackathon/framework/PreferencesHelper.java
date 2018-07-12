package com.hackathon.hackathon.framework;

import android.content.Context;
import android.content.SharedPreferences;


public class PreferencesHelper {

    public static final String PREF_FILE_NAME = "WASTE_PREF";

    private static SharedPreferences mPref;

    public static SharedPreferences getInstance(Context context) {
        if (mPref == null) {
            mPref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        }
        return mPref;
    }

    public void clear() {
        mPref.edit().clear().apply();
    }

    public SharedPreferences getPreferences() {

        return mPref;
    }
}
