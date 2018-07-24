package com.likandr.condortestapp.ui.main._di;

import android.support.v4.app.Fragment;

import com.likandr.condortestapp.data.somedata.interactors.SomeDataUseCase;
import com.likandr.condortestapp.ui.main.some_data.SomeDataPresenter;
import com.likandr.condortestapp.ui.main.some_data_detail.SomeDataDetailPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {
    private Fragment fragment;

    public MainModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    Fragment provideFragment() {
        return fragment;
    }

    //region presenters
    @Provides SomeDataPresenter provideSomeDataPresenter(SomeDataUseCase someDataUseCase) {
        return new SomeDataPresenter(someDataUseCase);
    }

    @Provides SomeDataDetailPresenter provideSomeDataDetailPresenter() {
        return new SomeDataDetailPresenter();
    }
    //endregion
}
