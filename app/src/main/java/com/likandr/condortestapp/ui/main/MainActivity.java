package com.likandr.condortestapp.ui.main;

import android.content.Context;
import android.content.Intent;

import com.likandr.condortestapp.R;
import com.likandr.condortestapp._common.base.BaseFragment;
import com.likandr.condortestapp._common.base.FragmentParams;
import com.likandr.condortestapp._common.base.activity.BaseActivity;
import com.likandr.condortestapp._common.misc.Layout;
import com.likandr.condortestapp.ui.main.some_data.view.SomeDataFragment;
import com.likandr.condortestapp.ui.main.some_data_detail.SomeDataDetailFragment;

@Layout(id = R.layout.activity_main)
public class MainActivity extends BaseActivity {

    public static final String ARGUMENT_ID = "key_id";
    public static final String ARGUMENT_NAME = "key_name";
    public static final String ARGUMENT_COUNTRY = "key_country";
    public static final String ARGUMENT_LAT = "key_lat";
    public static final String ARGUMENT_LON = "key_lon";

    public static void startMe(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override public void getExtras(Intent _intent) { }

    @Override public void beginWith() {
        initFragment(new FragmentParams(SomeDataFragment.TAG, null, null));
    }

    @Override public BaseFragment getFragment(FragmentParams fragmentParams) {
        if (fragmentParams.getFragmentTag().equals(SomeDataDetailFragment.TAG)) {
            return SomeDataDetailFragment.newInstance(fragmentParams.getArgs());
        }

        return SomeDataFragment.newInstance();
    }

    @Override public void onBackPressed() {
        if (currentFragmentTag.equals(SomeDataDetailFragment.TAG)) {
            initFragment(new FragmentParams(SomeDataFragment.TAG, null, null));
        } else {
            finish();
        }
    }
}
