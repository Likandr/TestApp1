package com.likandr.condortestapp.ui.auth.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.likandr.condortestapp.R;
import com.likandr.condortestapp._common.App;
import com.likandr.condortestapp._common.base.BaseFragment;
import com.likandr.condortestapp._common.misc.Layout;
import com.likandr.condortestapp._common.pref.PrefUtil;
import com.likandr.condortestapp.ui.auth.AuthActivity;
import com.likandr.condortestapp.ui.auth._di.AuthComponent;
import com.likandr.condortestapp.ui.auth._di.AuthModule;
import com.likandr.condortestapp.ui.auth._di.DaggerAuthComponent;
import com.likandr.condortestapp.ui.main.MainActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

@Layout(id = R.layout.fragment_login)
public class LoginFragment extends BaseFragment implements LoginMVP.View {

    public static final String TAG = LoginFragment.class.getSimpleName();

    AuthComponent component;
    @Inject LoginPresenter presenter;

    private ProgressDialog progressDialog = null;

    @BindView(R.id.text_input_layout_username) TextInputLayout edtEmail;
    @BindView(R.id.text_input_layout_password) TextInputLayout edtPassword;
    @BindView(R.id.btn_login) Button btnLogin;

    @Override public void setUpToolbar() {
        AuthActivity activity = (AuthActivity) getActivity();

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
            component = DaggerAuthComponent.builder()
                    .appComponent(App.getContext().getComponent())
                    .authModule(new AuthModule(this))
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

    public LoginFragment() { }

    public static LoginFragment newInstance() {
        return new LoginFragment();
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
        if (PrefUtil.isCode()) MainActivity.startMe(getContext());
    }

    @Override public void onAttach(Context context) {
        Log.d(TAG, "onAttach");
        this.injectDependencies();
        this.attachToPresenter();
        super.onAttach(context);
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

    @OnClick({R.id.btn_login})
    public void onClick(View view) {
        hideError();
        switch (view.getId()) {
            case R.id.btn_login:
                Log.d(TAG, "OnClickLogin");
                presenter.login(
                        edtEmail.getEditText().getText().toString(),
                        edtPassword.getEditText().getText().toString());
                break;
        }
    }

    @Override public void setErrorUsernameFieldEmpty() {
        showError(edtEmail, R.string.error_field_empty);
    }

    @Override public void setErrorPasswordFieldEmpty() {
        showError(edtPassword, R.string.error_field_empty);
    }

    @Override public void setErrorWrongAuthPair() {
        showError(edtEmail, R.string.error_login_invalid_email);
        showError(edtPassword, R.string.error_login_invalid_password);
    }

    private void showError(TextInputLayout view, int idRes) {
        view.setError(getString(idRes));
        view.setErrorEnabled(true);
        view.requestFocus();
    }

    private void hideError() {
        edtEmail.setErrorEnabled(false);
        edtEmail.requestFocus();
        edtPassword.setErrorEnabled(false);
    }

    @Override public void showLoading() {
        if (progressDialog == null) {
            try {
                progressDialog = ProgressDialog.show(getContext(), "", "Waiting response..");
                progressDialog.setCancelable(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override public void hideLoading() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    @Override public void onSuccess() {
        Log.d(TAG, "onSuccess");
        MainActivity.startMe(getContext());
        getFragmentActivity().finish();
    }

    @Override public void onFailure(Throwable e) {
        Log.d(TAG, "onFailure");
        e.printStackTrace();
    }
}
