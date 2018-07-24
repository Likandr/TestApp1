package com.likandr.condortestapp.data.auth;

import com.likandr.condortestapp._common.base.api.ApiService;
import com.likandr.condortestapp._common.base.interactor.BaseUseCase;
import com.likandr.condortestapp._common.misc.Params;

import javax.inject.Inject;

import io.reactivex.Single;

public class LoginUserUseCase extends BaseUseCase {

    public final static String PARAMS_USERNAME = "param_username";
    public final static String PARAMS_PASSWORD = "param_password";

    private ApiService api;

    @Inject public LoginUserUseCase(ApiService apiService) {
        api = apiService;
    }

    public Single<AuthResponse> getSingle(Params params) {
        return api.getCode(
                params.getString(PARAMS_USERNAME, null),
                params.getString(PARAMS_PASSWORD, null)
        );
    }
}
