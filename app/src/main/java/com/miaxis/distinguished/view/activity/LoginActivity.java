package com.miaxis.distinguished.view.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;
import com.miaxis.distinguished.R;
import com.miaxis.distinguished.app.DistinguishedApp;
import com.miaxis.distinguished.model.entity.Config;
import com.miaxis.distinguished.presenter.LoginPresenter;
import com.miaxis.distinguished.presenter.contract.LoginContract;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * Created by 一非 on 2018/6/26.
 */

public class LoginActivity extends BaseActivity implements LoginContract.View{

    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.btn_username_clear)
    Button btnUsernameClear;
    @BindView(R.id.btn_pwd_clear)
    Button btnPwdClear;
    @BindView(R.id.btn_pwd_eye)
    Button btnPwdEye;
    @BindView(R.id.cb_remember)
    CheckBox cbRemember;
    @BindView(R.id.cb_login_self)
    CheckBox cbLoginSelf;
    @BindView(R.id.btn_login)
    Button btnLogin;

    private LoginContract.Presenter presenter;
    private ProgressDialog progressDialog;

    @Override
    protected int setContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {
        presenter = new LoginPresenter(this);
        new RxPermissions(this)
                .request(Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            DistinguishedApp.getInstance().initDbHelp();
                            presenter.loadConfig();
                        } else {
                            Toast.makeText(LoginActivity.this, "拒绝权限将无法正常使用", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("asd", "权限获取出错");
                    }
                });
    }

    @Override
    protected void initView() {
        addTextWatcher(etUsername, btnUsernameClear);
        addTextWatcher(etPwd, btnPwdClear);
        etPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        RxView.clicks(btnPwdEye)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        if (etPwd.getInputType() == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)){
                            btnPwdEye.setBackgroundResource(R.drawable.ic_action_eye_open);
                            etPwd.setInputType(InputType.TYPE_CLASS_TEXT);
                        } else {
                            btnPwdEye.setBackgroundResource(R.drawable.ic_action_eye_closed);
                            etPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        }
                        etPwd.setSelection(etPwd.getText().toString().length());
                    }
                });
        RxView.clicks(cbRemember)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        if (cbLoginSelf.isChecked()) {
                            cbRemember.setChecked(true);
                        }
                    }
                });
        RxView.clicks(cbLoginSelf)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        if (cbLoginSelf.isChecked()) {
                            cbRemember.setChecked(true);
                        }
                    }
                });
        RxView.clicks(btnLogin)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        if (etUsername.getText().toString().isEmpty()) {
                            Toast.makeText(LoginActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (etPwd.getText().toString().isEmpty()) {
                            Toast.makeText(LoginActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        progressDialog.show();
                        Config config = new Config();
                        config.setId(1L);
                        config.setUsername(etUsername.getText().toString());
                        config.setPassword(etPwd.getText().toString());
                        config.setRemember(cbRemember.isChecked());
                        config.setLogingSelf(cbLoginSelf.isChecked());
                        presenter.login(config);
                    }
                });
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("请稍后...");
    }

    private void addTextWatcher(final EditText editText, final Button button) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (Objects.equals("", s.toString())) {
                    button.setVisibility(View.INVISIBLE);
                } else {
                    button.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
        RxView.clicks(button)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        editText.setText("");
                    }
                });
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && !editText.getText().toString().isEmpty()) {
                    button.setVisibility(View.VISIBLE);
                } else {
                    button.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    @Override
    public void loadConfig(Config config) {
        if (config != null) {
            if (config.getUsername() != null) {
                etUsername.setText(config.getUsername());
                btnUsernameClear.setVisibility(View.INVISIBLE);
            }
            if (config.getPassword() != null) {
                etPwd.setText(config.getPassword());
                btnPwdClear.setVisibility(View.INVISIBLE);
            }
            cbRemember.setChecked(config.getRemember());
            cbLoginSelf.setChecked(config.getLogingSelf());
            if (config.getLastLogin() && config.getLogingSelf()) {
                btnLogin.performClick();
            } else {
                cbLoginSelf.setChecked(false);
            }
        }
    }

    @Override
    public void loginResult(boolean result) {
        progressDialog.dismiss();
        if (result) {
            startActivity(MainActivity.newInstance(LoginActivity.this));
        } else {
            presenter.loginResult(false);
        }
    }

    @Override
    public void onBackPressed() {
        if (!cbLoginSelf.isChecked()) {
            presenter.cancelLoginSelf();
        }
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.doDestroy();
    }
}
