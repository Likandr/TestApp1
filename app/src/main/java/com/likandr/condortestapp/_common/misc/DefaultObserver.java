package com.likandr.condortestapp._common.misc;

import io.reactivex.observers.DisposableObserver;

public abstract class DefaultObserver<T> extends DisposableObserver<T> {

    protected abstract void onSuccess(T t);
    public abstract void onError(Throwable e);


    @Override public void onNext(T t) {
        onSuccess(t);
        // Intentionally empty.
    }

    @Override public void onComplete() {
        // Intentionally empty.
    }

//    @Override public void onError(Throwable exception) {
//        // Intentionally empty.
//    }
}
