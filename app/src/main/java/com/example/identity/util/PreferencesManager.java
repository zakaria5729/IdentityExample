package com.example.identity.util;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesManager {
    private static SharedPreferences.Editor editor;
    private static SharedPreferences sharedPreferences;
    private static PreferencesManager preferencesManager;

    private PreferencesManager(Context context) {
        sharedPreferences = context.getSharedPreferences(Utils.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.apply();
    }

    public static PreferencesManager getInstance(Context context) {
        if (preferencesManager == null) {
            preferencesManager = new PreferencesManager(context);
        }
        return preferencesManager;
    }

    public void setNidAndDob(String nid, String dob) {
        editor.putString(Utils.NID_NUMBER, nid);
        editor.putString(Utils.DOB, dob);
        editor.apply();
    }

    public void clearNidAndDob() {
        editor.clear();
    }

    public String getNid() {
        return sharedPreferences.getString(Utils.NID_NUMBER, null);
    }

    public String getDob() {
        return sharedPreferences.getString(Utils.DOB, null);
    }
}
