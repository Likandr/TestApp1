package com.likandr.condortestapp.ui.main.some_data.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.likandr.condortestapp.R;
import com.likandr.condortestapp._common.App;
import com.likandr.condortestapp._common.base.BaseFragment;
import com.likandr.condortestapp._common.misc.EndlessRecyclerOnScrollListener;
import com.likandr.condortestapp._common.misc.Layout;
import com.likandr.condortestapp._common.pref.PrefUtil;
import com.likandr.condortestapp.data._models.SomeData;
import com.likandr.condortestapp.ui.main.MainActivity;
import com.likandr.condortestapp.ui.main._di.DaggerMainComponent;
import com.likandr.condortestapp.ui.main._di.MainComponent;
import com.likandr.condortestapp.ui.main._di.MainModule;
import com.likandr.condortestapp.ui.main.some_data.SomeDataMVP;
import com.likandr.condortestapp.ui.main.some_data.SomeDataPresenter;
import com.likandr.condortestapp.ui.main.some_data_detail.SomeDataDetailFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

@Layout(id = R.layout.fragment_some_data)
public class SomeDataFragment extends BaseFragment implements SomeDataMVP.View,
        SomeDataAdapter.OnClickListener {

    public static final String TAG = SomeDataFragment.class.getSimpleName();

    MainComponent component;
    @Inject SomeDataPresenter presenter;

    OnChangeFragment onChangeFragment;

    @BindView(R.id.progress) ProgressBar pbData;
    @BindView(R.id.rv_data) RecyclerView rvData;

    private SomeDataAdapter mAdapter;
    private int pageNumber = 1;

    @Override public void setUpToolbar() {
        MainActivity activity = (MainActivity) getActivity();

        ActionBar actionBar = activity.getSupportActionBar();
        if(actionBar!= null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setTitle(getResources().getString(R.string.app_name));
            actionBar.hide();
        }
    }

    @Override public void getArgs(Bundle _bundle) { }

    @Override public void injectDependencies() {
        Log.d(TAG, "injectDependencies");
        if (component == null) {
            component = DaggerMainComponent.builder()
                    .appComponent(App.getContext().getComponent())
                    .mainModule(new MainModule(this))
                    .build();
            component.inject(this);
        }
    }

    @Override public void attachToPresenter() {
        Log.d(TAG, "attachToPresenter");
        this.presenter.attachView(this);
    }

    @Override public void detachFromPresenter() {
        Log.d(TAG, "detachFromPresenter");
        this.presenter.detachView();
    }

    public SomeDataFragment() { }

    public static SomeDataFragment newInstance() {
        return new SomeDataFragment();
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override public void beginWith() {
        fillViews();
        presenter.getSomeData(PrefUtil.getCode());
    }

    public void fillViews() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mAdapter = new SomeDataAdapter(getFragmentActivity());
        mAdapter.setOnClickListener(this);
        rvData.setHasFixedSize(true);
        rvData.setLayoutManager(layoutManager);
        rvData.setAdapter(mAdapter);
        rvData.addOnScrollListener(new EndlessRecyclerOnScrollListener(layoutManager) {
            @Override public void onLoadMore(int currentPage) {
                mAdapter.addProgress();
                pageNumber = currentPage;
                presenter.getSomeData(PrefUtil.getCode(), currentPage);
            }
        });
    }

    @Override public void onAttach(Context context) {
        Log.d(TAG, "onAttach");
        this.injectDependencies();
        this.attachToPresenter();
        super.onAttach(context);
        if (context instanceof OnChangeFragment) {
            onChangeFragment = (OnChangeFragment) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnChangeFragment");
        }
    }

    @Override public void onDetach() {
        Log.d(TAG, "onDetach");
        detachFromPresenter();
        super.onDetach();
    }

    @Override public void onDestroyView() {
        Log.d(TAG, "onDestroyView");
        hideLoading();
        super.onDestroyView();
    }

    @Override public void showLoading() {
        pbData.setVisibility(View.VISIBLE);
    }

    @Override public void hideLoading() {
        pbData.setVisibility(View.GONE);
    }

    @Override public void showSomeDataList(List<SomeData> someDataList) {
        Log.d(TAG, "onSuccess");

        pbData.setVisibility(View.GONE);
        rvData.setVisibility(View.VISIBLE);

        if (someDataList != null) {
            if (pageNumber > 1) mAdapter.addItems(someDataList);
            else                mAdapter.setItems(someDataList);
        } else {
            mAdapter.removeProgress();
            Toast.makeText(getContext(), R.string.no_more_data, Toast.LENGTH_SHORT).show();
        }
    }

    @Override public void onFailure(Throwable e) {
        Log.d(TAG, "onFailure");
        e.printStackTrace();
    }

    @Override public void onItemClicked(SomeData someData) {
        Bundle args = new Bundle();
        args.putString(MainActivity.ARGUMENT_ID, someData.getId());
        args.putString(MainActivity.ARGUMENT_NAME, someData.getName());
        args.putString(MainActivity.ARGUMENT_COUNTRY, someData.getCountry());
        args.putDouble(MainActivity.ARGUMENT_LAT, someData.getLat());
        args.putDouble(MainActivity.ARGUMENT_LON, someData.getLon());

        onChangeFragment.onChangeFragment(SomeDataDetailFragment.TAG, args, null);
    }
}