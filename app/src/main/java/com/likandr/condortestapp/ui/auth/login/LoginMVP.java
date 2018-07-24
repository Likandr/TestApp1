package com.likandr.condortestapp.ui.auth.login;

import com.likandr.condortestapp._common.base.BaseMvpView;
import com.likandr.condortestapp.data.auth.AuthResponse;

public class LoginMVP {
    public interface View extends BaseMvpView {
        void setErrorUsernameFieldEmpty();
        void setErrorPasswordFieldEmpty();
        void setErrorWrongAuthPair();
        void onSuccess();
        void onFailure(Throwable e);
    }

    interface Presenter {
        void login(String email, String password);
        void onSuccess(AuthResponse response);
        void onFailure(Throwable e);
    }
}
