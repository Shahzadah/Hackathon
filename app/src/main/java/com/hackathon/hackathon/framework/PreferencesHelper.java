package com.hackathon.hackathon.framework;

import android.content.Context;
import android.content.SharedPreferences;


public class PreferencesHelper {

    public static final String PREF_FILE_NAME = "WASTE_PREF";
    private Context mContext;
    private static SharedPreferences mPref;

    public PreferencesHelper(Context context) {
        this.mContext = context;
    }

    public void clear() {
        mPref.edit().clear().apply();
    }

    public SharedPreferences getPreferences() {
        if (mPref == null) {
            mPref = mContext.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        }
        return mPref;
    }
}
