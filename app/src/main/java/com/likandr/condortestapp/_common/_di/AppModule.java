package com.likandr.condortestapp._common._di;

import android.content.Context;

import com.likandr.condortestapp._common.App;

import dagger.Module;
import dagger.Provides;

@Module
public final class AppModule {
    private App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides @ApplicationScope
    public App provideApplication() {
        return app;
    }

    @Provides @ApplicationScope
    public Context provideApplicationContext() {
        return app.getApplicationContext();
    }
}
