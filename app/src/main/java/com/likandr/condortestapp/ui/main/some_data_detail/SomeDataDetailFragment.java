package com.likandr.condortestapp.ui.main.some_data_detail;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.likandr.condortestapp.R;
import com.likandr.condortestapp._common.App;
import com.likandr.condortestapp._common.base.BaseFragment;
import com.likandr.condortestapp._common.misc.Layout;
import com.likandr.condortestapp._common.misc.Utils;
import com.likandr.condortestapp.ui.main.MainActivity;
import com.likandr.condortestapp.ui.main._di.DaggerMainComponent;
import com.likandr.condortestapp.ui.main._di.MainComponent;
import com.likandr.condortestapp.ui.main._di.MainModule;

import javax.inject.Inject;

import butterknife.BindView;

@Layout(id = R.layout.fragment_some_data_detail)
public class SomeDataDetailFragment extends BaseFragment implements SomeDataDetailMVP.View, OnMapReadyCallback {

    public static final String TAG = SomeDataDetailPresenter.class.getSimpleName();

    MainComponent component;
    @Inject SomeDataDetailPresenter presenter;

    OnChangeFragment onChangeFragment;
    private GoogleMap mMap;
    private String mId, mName, mCountry;
    private Double mLat, mLon;

    @BindView(R.id.tv_id) TextView tvId;
    @BindView(R.id.tv_name) TextView tvName;
    @BindView(R.id.tv_country) TextView tvCountry;

    @Override public void setUpToolbar() {
        setHasOptionsMenu(true);
        MainActivity activity = (MainActivity) getFragmentActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        if(actionBar!= null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(getResources().getString(R.string.app_name));
            actionBar.show();
        }
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getFragmentActivity().onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override public void getArgs(Bundle _bundle) {
        if (_bundle != null) {
            mId = Utils.getString(_bundle, MainActivity.ARGUMENT_ID);
            mName = Utils.getString(_bundle, MainActivity.ARGUMENT_NAME);
            mCountry = Utils.getString(_bundle, MainActivity.ARGUMENT_COUNTRY);
            mLat = Utils.getDouble(_bundle, MainActivity.ARGUMENT_LAT);
            mLon = Utils.getDouble(_bundle, MainActivity.ARGUMENT_LON);
        }
    }

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

    public SomeDataDetailFragment() { }

    public static SomeDataDetailFragment newInstance(Bundle args) {
        SomeDataDetailFragment fragment = new SomeDataDetailFragment();
        fragment.setArguments(args);
        return fragment;
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
        tvId.setText(mId);
        tvName.setText(mName);
        tvCountry.setText(mCountry);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) mapFragment.getMapAsync(this);
    }

    @Override public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (mLat != null) {
            LatLng sydney = new LatLng(mLat, mLon);
            mMap.addMarker(new MarkerOptions().position(sydney).title(mName));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        }
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

    @Override public void showLoading() { }

    @Override public void hideLoading() { }
}
