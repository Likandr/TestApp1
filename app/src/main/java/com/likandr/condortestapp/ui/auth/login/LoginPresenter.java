package com.likandr.condortestapp.ui.auth.login;

import com.likandr.condortestapp._common.base.presenter.BasePresenter;
import com.likandr.condortestapp._common.misc.Params;
import com.likandr.condortestapp._common.misc.RxUtils;
import com.likandr.condortestapp._common.misc.Utils;
import com.likandr.condortestapp._common.pref.PrefUtil;
import com.likandr.condortestapp.data.auth.AuthResponse;
import com.likandr.condortestapp.data.auth.LoginUserUseCase;

import javax.inject.Inject;

public class LoginPresenter extends BasePresenter<LoginMVP.View> implements LoginMVP.Presenter {
    private LoginUserUseCase loginUserUseCase;

    @Inject public LoginPresenter(LoginUserUseCase useCase) {
        loginUserUseCase = useCase;
    }

    @Override public void attachView(LoginMVP.View view) {
        super.attachView(view);
    }

    @Override public void detachView() {
        super.detachView();
    }

    @Override public void login(String username, String password) {
        username = username.trim();
        password = password.trim();

        if (username.isEmpty()) {
            view.setErrorUsernameFieldEmpty();
            return;
        }
        if (password.isEmpty()) {
            view. setErrorPasswordFieldEmpty();
            return;
        }

        view.showLoading();

        Params params = Params.create();
        params.putString(LoginUserUseCase.PARAMS_USERNAME, username);
        params.putString(LoginUserUseCase.PARAMS_PASSWORD, password);

        addDisposable(RxUtils.performRequestSingle(
                loginUserUseCase.getSingle(params),
                this::onSuccess,
                this::onFailure));
    }

    @Override public void onSuccess(AuthResponse response) {

        if (response.getStatus().equals("ok")) {
            PrefUtil.setCode(response.getCode());
            view.hideLoading();
            view.onSuccess();
        } else {
            view.hideLoading();
            view.setErrorWrongAuthPair();
        }
    }

    @Override public void onFailure(Throwable e) {
        view.hideLoading();
        view.onFailure(e);
    }
}
