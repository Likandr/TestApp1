package com.likandr.condortestapp._common._di;

import android.content.Context;

import com.google.gson.Gson;
import com.likandr.condortestapp._common.App;
import com.likandr.condortestapp._common.base.api.ApiService;
import com.likandr.condortestapp._common.base.api.NetModule;

import dagger.Component;

@ApplicationScope
@Component(
        modules = {AppModule.class, NetModule.class}
)
public interface AppComponent {

    void inject(App app);

    Context context();
    App app();
    Gson gson();
    ApiService apiService();
}
