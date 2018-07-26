package com.likandr.condortestapp._common.base.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.likandr.condortestapp.R;
import com.likandr.condortestapp._common.base.BaseFragment;
import com.likandr.condortestapp._common.base.FragmentParams;
import com.likandr.condortestapp._common.misc.Layout;

import java.lang.annotation.Annotation;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.Disposable;

public abstract class BaseActivity extends AppCompatActivity implements IBaseActivity {
    public String currentFragmentTag;
    private ProgressDialog progressDialog;
    Unbinder mUnbinder;
    private Disposable d;

    @Inject SharedPreferences mSharedPreferences;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Class cls = getClass();
        if (!cls.isAnnotationPresent(Layout.class)) return;
        Annotation annotation = cls.getAnnotation(Layout.class);
        Layout layout = (Layout) annotation;
        setContentView(layout.id());
        mUnbinder = ButterKnife.bind(this);

        this.getExtras(getIntent());
        beginWith();
    }

    @Override public abstract void getExtras(Intent _intent);

    @Override public abstract void beginWith();

    @Override protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        if (d != null) d.dispose();
    }

    public void closeActivity() {
        this.finish();
    }

    public Context getContext() {
        return this;
    }

    public void showProgress(String msg) {
        if (msg == null) msg = getString(R.string.loading_msg);

        if (!isFinishing()) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage(msg);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
    }

    public void hideProgress() {
        if (progressDialog != null && !isFinishing() && progressDialog.isShowing())
            progressDialog.dismiss();
    }

    public void initFragment(FragmentParams fragmentParams) {
        currentFragmentTag = fragmentParams.getFragmentTag();
        BaseFragment baseFragment = getFragment(fragmentParams);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, baseFragment).commit();

        d = baseFragment.changeFragment().subscribe(this::initFragment);
    }

    public BaseFragment getFragment(FragmentParams fragmentParams) {
        return null;
    }
}
