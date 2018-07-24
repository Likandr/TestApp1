package com.likandr.condortestapp._common.base.interactor;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseUseCase {

    private final CompositeDisposable disposables;

    public BaseUseCase() {
        this.disposables = new CompositeDisposable();
    }

    public void dispose() {
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
    }

    private void addDisposable(Disposable disposable) {
        if (disposable != null) {
            disposables.add(disposable);
        }
    }
}
