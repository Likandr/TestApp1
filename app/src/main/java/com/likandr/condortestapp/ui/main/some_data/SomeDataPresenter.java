package com.likandr.condortestapp.ui.main.some_data;

import com.likandr.condortestapp._common.base.presenter.BasePresenter;
import com.likandr.condortestapp._common.misc.Params;
import com.likandr.condortestapp._common.misc.RxUtils;
import com.likandr.condortestapp._common.pref.PrefUtil;
import com.likandr.condortestapp.data.somedata.interactors.SomeDataUseCase;
import com.likandr.condortestapp.data.somedata.responses.SomeDataResponse;

import javax.inject.Inject;

import static com.likandr.condortestapp.data.somedata.interactors.SomeDataUseCase.PARAMS_CODE;
import static com.likandr.condortestapp.data.somedata.interactors.SomeDataUseCase.PARAMS_PAGE;

public class SomeDataPresenter extends BasePresenter<SomeDataMVP.View> implements SomeDataMVP.Presenter {
    private SomeDataUseCase useCase;

    @Inject public SomeDataPresenter(SomeDataUseCase useCase) {
        this.useCase = useCase;
    }

    @Override public void attachView(SomeDataMVP.View view) {
        super.attachView(view);
    }

    @Override public void detachView() {
        super.detachView();
    }

    @Override public void getSomeData(String code) {
        getSomeData(code, 1);
    }

    @Override public void getSomeData(String code, int page) {
        view.showLoading();

        Params params = Params.create();
        params.putString(PARAMS_CODE, code);
        params.putInt(PARAMS_PAGE, page);

        addDisposable(RxUtils.performRequestSingle(
                useCase.getData(params),
                this::onSuccess,
                this::onFailure));
    }

    @Override public void onSuccess(SomeDataResponse response) {
        view.hideLoading();
        view.showSomeDataList(response.getList());
    }

    @Override public void onFailure(Throwable e) {
        view.hideLoading();
        view.onFailure(e);
    }
}
