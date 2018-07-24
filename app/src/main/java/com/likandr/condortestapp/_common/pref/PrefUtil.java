package com.likandr.condortestapp._common.pref;

public class PrefUtil {

    public static final String KEY_CODE = "code";

    public static void clearUserData() {
        PreferencesManager.getInstance().removeAll();
    }

    public static void setCode(String code) {
        PreferencesManager.getInstance().putString(KEY_CODE, code);
    }

    public static String getCode(){
        return PreferencesManager.getInstance().getString(KEY_CODE);
    }

    public static boolean isCode() {
        return PreferencesManager.getInstance().contains(KEY_CODE);
    }
}
