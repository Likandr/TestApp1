package com.likandr.condortestapp._common.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.likandr.condortestapp._common.misc.Layout;

import java.lang.annotation.Annotation;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public abstract class BaseFragment extends Fragment implements BaseView {

    private Unbinder mUnbinder;
    public PublishSubject<FragmentParams> changeFragmentSubject = PublishSubject.create();


    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Class cls = getClass();
        if (!cls.isAnnotationPresent(Layout.class)) return null;
        Annotation annotation = cls.getAnnotation(Layout.class);
        Layout layout = (Layout) annotation;
        View view = inflater.inflate(layout.id(), container, false);//null);
        mUnbinder = ButterKnife.bind(this, view);

        this.getArgs(savedInstanceState != null
                ? savedInstanceState
                : getArguments());
        this.setUpToolbar();

        return view;
    }

    @Override public void onResume() {
        super.onResume();
        beginWith();
    }

    public abstract void beginWith();

    @Override public void onDestroyView() {
        if (mUnbinder != null) mUnbinder.unbind();
        super.onDestroyView();
    }

    public Context getContext() {
        return getActivity();
    }
    public FragmentActivity getFragmentActivity() {
        return getActivity();
    }
    public Fragment getFragment() {
        return this;
    }
    public abstract void getArgs(Bundle _bundle);
    public abstract void setUpToolbar();

    public Observable<FragmentParams> changeFragment() {
        return changeFragmentSubject;
    }
}
