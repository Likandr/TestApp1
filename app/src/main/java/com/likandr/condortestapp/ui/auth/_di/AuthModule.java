package com.likandr.condortestapp.ui.auth._di;

import android.support.v4.app.Fragment;

import com.likandr.condortestapp.data.auth.LoginUserUseCase;
import com.likandr.condortestapp.ui.auth.login.LoginPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class AuthModule {
    private Fragment fragment;

    public AuthModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides Fragment provideFragment() {
        return fragment;
    }

    @Provides LoginPresenter provideLoginPresenter(LoginUserUseCase useCase) {
        return new LoginPresenter(useCase);
    }
}
