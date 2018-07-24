package com.likandr.condortestapp.data.somedata.interactors;

import com.likandr.condortestapp._common.base.api.ApiService;
import com.likandr.condortestapp._common.base.interactor.BaseUseCase;
import com.likandr.condortestapp._common.misc.Params;
import com.likandr.condortestapp.data.somedata.responses.SomeDataResponse;

import javax.inject.Inject;

import io.reactivex.Single;

public class SomeDataUseCase extends BaseUseCase {

    public final static String PARAMS_CODE = "param_code";
    public final static String PARAMS_PAGE = "param_page";

    private ApiService api;

    @Inject public SomeDataUseCase(ApiService apiService) {
        api = apiService;
    }

    public Single<SomeDataResponse> getData(Params params) {
        return api.getSomeData(
                params.getString(PARAMS_CODE, null),
                params.getString(PARAMS_PAGE, null)
        );
    }
}
