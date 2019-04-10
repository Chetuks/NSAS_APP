package com.example.nsas_app.activities;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencemanager {
    SharedPreferences sharedPreferences;
    Context mcontext;
    int PRIVATE_MODE = 0;
    String PREF_NAME = "shared_prefe";
    SharedPreferences.Editor editor;

    public SharedPreferencemanager(Context context) {
        mcontext = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void saveIsLoggedIn(Context context, Boolean isLoggedIn) {
        mcontext = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("IS_LOGGED_IN", isLoggedIn);
        editor.commit();

    }
    public boolean getISLogged_IN() {
        //mContext = context;
        sharedPreferences = mcontext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        return sharedPreferences.getBoolean("IS_LOGGED_IN", false);
    }

}
