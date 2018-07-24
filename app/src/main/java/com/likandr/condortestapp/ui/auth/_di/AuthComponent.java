package com.likandr.condortestapp.ui.auth._di;

import com.likandr.condortestapp._common._di.ActivityScope;
import com.likandr.condortestapp._common._di.AppComponent;
import com.likandr.condortestapp.ui.auth.login.LoginFragment;
import com.likandr.condortestapp.ui.auth.login.LoginPresenter;

import dagger.Component;

@ActivityScope
@Component(
        dependencies = AppComponent.class,
        modules = AuthModule.class
)
public interface AuthComponent {
    //Fragments
    void inject(LoginFragment view);

    // Presenters
    void inject(LoginPresenter presenter);
}
