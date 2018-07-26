package com.likandr.condortestapp._common.base;

import android.os.Bundle;
import android.support.v4.util.Pair;
import android.view.View;

public class FragmentParams {
    private String fragmentTag;
    private Bundle args;
    private Pair<View, String> sharedElement;

    public FragmentParams(String fragmentTag, Bundle args, Pair<View, String> sharedElement) {
        this.fragmentTag = fragmentTag;
        this.args = args;
        this.sharedElement = sharedElement;
    }

    public String getFragmentTag() {
        return fragmentTag;
    }

    public void setFragmentTag(String fragmentTag) {
        this.fragmentTag = fragmentTag;
    }

    public Bundle getArgs() {
        return args;
    }

    public void setArgs(Bundle args) {
        this.args = args;
    }

    public Pair<View, String> getSharedElement() {
        return sharedElement;
    }

    public void setSharedElement(Pair<View, String> sharedElement) {
        this.sharedElement = sharedElement;
    }
}
