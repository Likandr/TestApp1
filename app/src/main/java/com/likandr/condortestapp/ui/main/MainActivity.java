package com.likandr.condortestapp.ui.main;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.os.Bundle;
import android.view.View;

import com.likandr.condortestapp.R;
import com.likandr.condortestapp._common.base.BaseFragment;
import com.likandr.condortestapp._common.base.activity.BaseActivity;
import com.likandr.condortestapp._common.misc.Layout;
import com.likandr.condortestapp.ui.main.some_data.view.SomeDataFragment;
import com.likandr.condortestapp.ui.main.some_data_detail.SomeDataDetailFragment;

@Layout(id = R.layout.activity_main)
public class MainActivity extends BaseActivity implements BaseFragment.OnChangeFragment {

    public static final String ARGUMENT_ID = "key_id";
    public static final String ARGUMENT_NAME = "key_name";
    public static final String ARGUMENT_COUNTRY = "key_country";
    public static final String ARGUMENT_LAT = "key_lat";
    public static final String ARGUMENT_LON = "key_lon";

    private String currentFragmentTag;

    public static void startMe(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override public void getExtras(Intent _intent) { }

    @Override public void beginWith() {
        initFragment(SomeDataFragment.TAG, null);
    }

    private void initFragment(String fragmentTag, Bundle args) {
        currentFragmentTag = fragmentTag;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, getFragment(fragmentTag, args)).commit();
    }

    private Fragment getFragment(String fragmentTag, Bundle args) {
        if (fragmentTag.equals(SomeDataDetailFragment.TAG)) {
            return SomeDataDetailFragment.newInstance(args);
        }

        return SomeDataFragment.newInstance();
    }

    @Override public Fragment getAttachedFragment(int id) {
        return getSupportFragmentManager().findFragmentById(id);
    }

    @Override public Fragment getAttachedFragment(String tag) {
        return getSupportFragmentManager().findFragmentByTag(tag);
    }

    @Override public void onChangeFragment(String fragmentTag, Bundle args, @Nullable Pair<View, String> sharedElement) {
        initFragment(fragmentTag, args);
    }

    @Override public void onBackPressed() {
        if (currentFragmentTag.equals(SomeDataDetailFragment.TAG)) {
            initFragment(SomeDataFragment.TAG, null);
        } else {
            finish();
        }
    }
}
