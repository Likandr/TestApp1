package com.likandr.condortestapp.ui.auth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.view.View;

import com.likandr.condortestapp.R;
import com.likandr.condortestapp._common.base.activity.BaseActivity;
import com.likandr.condortestapp._common.base.BaseFragment;
import com.likandr.condortestapp._common.misc.Layout;
import com.likandr.condortestapp.ui.auth.login.LoginFragment;

@Layout(id = R.layout.activity_auth)
public class AuthActivity extends BaseActivity implements BaseFragment.OnChangeFragment {
    private String currentFragmentTag;

    public static void startMe(Context context) {
        Intent intent = new Intent(context, AuthActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        context.startActivity(intent);
    }

    @Override public void getExtras(Intent _intent) { }

    @Override public void beginWith() {
        initFragment(LoginFragment.TAG);
    }

    private void initFragment(String fragmentTag) {
        currentFragmentTag = fragmentTag;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, getFragment(fragmentTag)).commit();
    }

    private Fragment getFragment(String fragmentTag) {
        return LoginFragment.newInstance();
    }

    @Override public Fragment getAttachedFragment(int id) {
        return getSupportFragmentManager().findFragmentById(id);
    }

    @Override public Fragment getAttachedFragment(String tag) {
        return getSupportFragmentManager().findFragmentByTag(tag);
    }

    @Override public void onChangeFragment(String fragmentTag, Bundle args, @Nullable Pair<View, String> sharedElement) {
        initFragment(fragmentTag);
    }
}
