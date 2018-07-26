package com.likandr.condortestapp.ui.auth;

import android.content.Intent;

import com.likandr.condortestapp.R;
import com.likandr.condortestapp._common.base.BaseFragment;
import com.likandr.condortestapp._common.base.FragmentParams;
import com.likandr.condortestapp._common.base.activity.BaseActivity;
import com.likandr.condortestapp._common.misc.Layout;
import com.likandr.condortestapp.ui.auth.login.LoginFragment;

@Layout(id = R.layout.activity_auth)
public class AuthActivity extends BaseActivity {

    @Override public void getExtras(Intent _intent) { }

    @Override public void beginWith() {
        initFragment(new FragmentParams(LoginFragment.TAG, null, null));
    }

    @Override public BaseFragment getFragment(FragmentParams fragmentParams) {
        return LoginFragment.newInstance();
    }
}
