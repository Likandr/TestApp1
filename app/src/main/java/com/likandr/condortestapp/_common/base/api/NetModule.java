package com.likandr.condortestapp._common.base.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.likandr.condortestapp._common.App;
import com.likandr.condortestapp._common._di.ApplicationScope;

import java.io.File;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

@Module
public class NetModule {
    //  OkHttp client request time out.
    private static final int CLIENT_TIME_OUT = 10;

    //  OkHttp cache size.
    private static final int CLIENT_CACHE_SIZE = 10 * 1024 * 1024;

    //  OkHttp request date format. Eg. 2016-06-19T13:07:45.139Z
    private static final String CLIENT_DATE_FORMAT = "yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'";

    //  OkHttp cache directory.
    private static final String CLIENT_CACHE_DIRECTORY = "http";

    public NetModule() {
        //---
    }

    @Provides @ApplicationScope
    Cache provideCache(App application) {
        return new Cache(new File(application.getCacheDir(), CLIENT_CACHE_DIRECTORY), CLIENT_CACHE_SIZE);
    }

    @Provides @ApplicationScope
    Gson provideGson() {
        return new GsonBuilder()
                .setDateFormat(CLIENT_DATE_FORMAT)
                .create();
    }

    @Provides @ApplicationScope
    OkHttpClient provideOkHttpClient(Cache cache) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        //logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(logging)
                .addNetworkInterceptor(logging)
                .connectTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
                .cache(cache)
                .build();
    }

    @Provides @ApplicationScope
    public ApiService provideRetrofit(OkHttpClient okHttpClient, Gson gson) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NetConst.API_BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit.create(ApiService.class);
    }
}
