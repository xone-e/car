package com.example.mycar.storage;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefrencesManager {

    public static final String PRFS_FILE = "com.example.mycar.storage.preferences";
    public static final String USER_ID = "user_id";
    public static final String LOGIN = "login";
    private static SharedPrefrencesManager mInstance;
    private Context context;

    private SharedPrefrencesManager(Context context) {
        this.context = context;
    }

    public static synchronized SharedPrefrencesManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefrencesManager(context);
        }
        return mInstance;
    }

    public void setUserId(int userId) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PRFS_FILE, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(USER_ID, userId);
        editor.commit();
    }

    public int getUserId() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PRFS_FILE, context.MODE_PRIVATE);
        return sharedPreferences.getInt(USER_ID, -1);
    }

    public void setIsLoggedIn() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PRFS_FILE, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(LOGIN, true);
        editor.commit();
    }

    public boolean getIsLoggedIn() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PRFS_FILE, context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(LOGIN, false);
    }

    public void clear() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PRFS_FILE, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
