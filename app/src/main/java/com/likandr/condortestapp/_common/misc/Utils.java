package com.likandr.condortestapp._common.misc;

import android.os.Bundle;

public class Utils {

    public static String getString(Bundle _bundle, String key) {
        return _bundle.containsKey(key) ?
                _bundle.getString(key) :
                null;
    }

    public static Double getDouble(Bundle _bundle, String key) {
        return _bundle.containsKey(key) ?
                _bundle.getDouble(key) :
                null;
    }
}
