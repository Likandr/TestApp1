package com.likandr.condortestapp._common.misc;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RxUtils {
    public static <T> Disposable performRequestFlowable(Flowable<T> flowable,
                                                        Consumer<? super T> onSuccess,
                                                        Consumer<Throwable> onError) {
        return flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onSuccess, onError);
    }

    public static <T> Disposable performRequestObservable(Observable<T> observable,
                                                        Consumer<? super T> onSuccess,
                                                        Consumer<Throwable> onError) {
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onSuccess, onError);
    }

    public static <T> Disposable performRequestSingle(Single<T> single,
                                                      Consumer<? super T> onSuccess,
                                                      Consumer<Throwable> onError) {
        return single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onSuccess, onError);
    }
}
