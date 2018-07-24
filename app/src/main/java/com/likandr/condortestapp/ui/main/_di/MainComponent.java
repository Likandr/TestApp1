package com.likandr.condortestapp.ui.main._di;

import com.likandr.condortestapp._common._di.ActivityScope;
import com.likandr.condortestapp._common._di.AppComponent;
import com.likandr.condortestapp.ui.main.some_data.view.SomeDataFragment;
import com.likandr.condortestapp.ui.main.some_data.SomeDataPresenter;
import com.likandr.condortestapp.ui.main.some_data_detail.SomeDataDetailFragment;
import com.likandr.condortestapp.ui.main.some_data_detail.SomeDataDetailPresenter;

import dagger.Component;

@ActivityScope
@Component(
        dependencies = AppComponent.class,
        modules = MainModule.class
)
public interface MainComponent {
    //Fragments
    void inject(SomeDataFragment view);
    void inject(SomeDataDetailFragment view);

    // Presenters
    void inject(SomeDataPresenter presenter);
    void inject(SomeDataDetailPresenter presenter);
}
