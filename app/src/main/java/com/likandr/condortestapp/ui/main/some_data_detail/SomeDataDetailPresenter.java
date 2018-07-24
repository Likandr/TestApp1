package com.likandr.condortestapp.ui.main.some_data_detail;

import com.likandr.condortestapp._common.base.presenter.BasePresenter;

import javax.inject.Inject;

public class SomeDataDetailPresenter extends BasePresenter<SomeDataDetailMVP.View>
        implements SomeDataDetailMVP.Presenter {

    @Inject public SomeDataDetailPresenter() { }

    @Override public void attachView(SomeDataDetailMVP.View view) {
        super.attachView(view);
    }

    @Override public void detachView() {
        super.detachView();
    }
}
