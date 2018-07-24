package com.likandr.condortestapp._common.base.api;

import com.likandr.condortestapp.data.auth.AuthResponse;
import com.likandr.condortestapp.data.somedata.responses.SomeDataResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET(NetConst.GET_AUTH_CODE)
    Single<AuthResponse> getCode(
            @Query("username") String code,
            @Query("password") String clientId);

    @GET(NetConst.GET_DATA_LIST)
    Single<SomeDataResponse> getSomeData(
            @Query("code") String code,
            @Query("p") String page);
}
