package com.likandr.condortestapp._common.pref;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

public class PreferencesManager {
    private static final String PREF_NAME = "com.likandr.condortestapp.PREF_MAIN";

    private static PreferencesManager sInstance;
    private final SharedPreferences mPref;

    private PreferencesManager(Context context) {
        mPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized void initializeInstance(Context context) {
        if (sInstance == null) {
            sInstance = new PreferencesManager(context);
        }
    }

    public static synchronized PreferencesManager getInstance() {
        if (sInstance == null) {
            throw new IllegalStateException(PreferencesManager.class.getSimpleName() +
                    " is not initialized, call initializeInstance(..) method first.");
        }
        return sInstance;
    }

    public void putLong(@NonNull String key, long value) {
        mPref.edit()
                .putLong(key, value)
                .apply();
    }

    public long getLong(@NonNull String key, long defaultValue) {
        return mPref.getLong(key, defaultValue);
    }

    public void putInteger(@NonNull String key, int value) {
        mPref.edit().putInt(key, value).apply();
    }

    public int getInteger(@NonNull String key, int defaultValue) {
        return mPref.getInt(key, defaultValue);
    }

    public void putString(@NonNull String key, String value) {
        mPref.edit().putString(key, value).apply();
    }

    public String getString(@NonNull String key) {
        return mPref.getString(key, null);
    }

    public void putFloat(@NonNull String key, float value) {
        mPref.edit().putFloat(key, value).apply();
    }

    public float getFloat(@NonNull String key) {
        return mPref.getFloat(key, 0);
    }

    public void putBoolean(@NonNull String key, boolean value) {
        mPref.edit().putBoolean(key, value).apply();
    }

    public boolean getBoolean(@NonNull String key) {
        return mPref.getBoolean(key, false);
    }

    public boolean contains(@NonNull String key) {
        return mPref.contains(key);
    }

    public void removePreference(@NonNull String key) {
        mPref.edit().remove(key).apply();
    }

    public void removeAll() {
        mPref.edit().clear().apply();
    }
}
