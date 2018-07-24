package com.likandr.condortestapp._common.base;

import android.content.Context;

public interface BaseView {

    void injectDependencies();

    void attachToPresenter();
    void detachFromPresenter();

    void beginWith();

    void showLoading();
    void hideLoading();

    Context getContext();
}
