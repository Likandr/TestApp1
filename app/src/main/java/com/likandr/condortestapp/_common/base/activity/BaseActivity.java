package com.likandr.condortestapp._common.base.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.likandr.condortestapp.R;
import com.likandr.condortestapp._common.misc.Layout;

import java.lang.annotation.Annotation;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity implements IBaseActivity {

    private ProgressDialog progressDialog;
    Unbinder mUnbinder;

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    public void closeActivity() {
        this.finish();
    }

    public Fragment getAttachedFragment(int id) {
        return getSupportFragmentManager().findFragmentById(id);
    }

    public Fragment getAttachedFragment(String tag) {
        return getSupportFragmentManager().findFragmentByTag(tag);
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
}
