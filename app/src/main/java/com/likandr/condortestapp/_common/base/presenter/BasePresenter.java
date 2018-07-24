package com.likandr.condortestapp._common.base.presenter;

import com.likandr.condortestapp._common.base.BaseMvpView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BasePresenter<T extends BaseMvpView> implements IBasePresenter<T> {

    protected T view;

    private final CompositeDisposable mDisposable = new CompositeDisposable();

    @Override
    public void attachView(T view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        disposeCD();
        this.view = null;
    }

    @Override
    public boolean isViewAttached() {
        return view != null;
    }

    @Override
    public void checkViewAttached() throws ViewNotAttachedException {
        if (!isViewAttached()) throw new ViewNotAttachedException();
    }

    private static class ViewNotAttachedException extends RuntimeException {
        private ViewNotAttachedException() {
            super("Call Presenter.attachView(BaseView) before asking for data");
        }
    }

    @Override
    public void addDisposable(Disposable disposable) {
        mDisposable.add(disposable);
    }

    @Override
    public void disposeCD() {
        mDisposable.dispose();
    }
}
