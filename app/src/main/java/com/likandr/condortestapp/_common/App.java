package com.likandr.condortestapp._common;

import android.app.Application;

import com.likandr.condortestapp._common._di.AppComponent;
import com.likandr.condortestapp._common._di.AppModule;
import com.likandr.condortestapp._common._di.DaggerAppComponent;
import com.likandr.condortestapp._common.base.api.NetModule;
import com.likandr.condortestapp._common.pref.PreferencesManager;

public class App extends Application {

    private static App sInstance;
    private AppComponent component;

    public App() {
        sInstance = this;
    }

    public static App getContext() {
        return sInstance;
    }

    public AppComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        this.setAppComponent();

        PreferencesManager.initializeInstance(sInstance);
    }

    private void setAppComponent() {
        component = DaggerAppComponent.builder()
                .appModule(new AppModule(sInstance))
                .netModule(new NetModule())
                .build();
    }
}
