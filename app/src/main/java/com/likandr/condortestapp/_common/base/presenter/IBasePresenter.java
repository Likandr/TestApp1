package com.likandr.condortestapp._common.base.presenter;

import com.likandr.condortestapp._common.base.BaseMvpView;

import io.reactivex.disposables.Disposable;

public interface IBasePresenter<V extends BaseMvpView> {

    void attachView(V view);
    void detachView();
    boolean isViewAttached();
    void checkViewAttached();
    void addDisposable(Disposable disposable);
    void disposeCD();
}
