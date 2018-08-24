package com.miaxis.distinguished.view.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.jakewharton.rxbinding2.view.RxView;
import com.miaxis.distinguished.R;
import com.miaxis.distinguished.app.DistinguishedApp;
import com.miaxis.distinguished.model.entity.Worker;
import com.miaxis.distinguished.model.retrofit.ResponseEntity;
import com.miaxis.distinguished.presenter.SystemPresenter;
import com.miaxis.distinguished.presenter.contract.SystemContract;
import com.miaxis.distinguished.util.ValueUtil;
import com.miaxis.distinguished.view.activity.LoginActivity;
import com.miaxis.distinguished.view.activity.WorkerActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * A simple {@link Fragment} subclass.
 */
public class SystemFragment extends BaseFragment implements SystemContract.View {


    @BindView(R.id.profile_image)
    CircleImageView profileImage;
    @BindView(R.id.tv_setting_worker_name)
    TextView tvSettingWorkerName;
    @BindView(R.id.tv_setting_worker_code)
    TextView tvSettingWorkerCode;
    @BindView(R.id.ll_worker_message)
    LinearLayout llWorkerMessage;
    @BindView(R.id.ll_edit_password)
    LinearLayout llEditPassword;
    @BindView(R.id.ll_customer_service)
    LinearLayout llCustomerService;
    @BindView(R.id.ll_question)
    LinearLayout llQuestion;
    @BindView(R.id.ll_logout)
    LinearLayout llLogout;

    private String password;
    private SystemContract.Presenter presenter;

    public SystemFragment() {
        // Required empty public constructor
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_system;
    }

    @Override
    protected void initData() {
        presenter = new SystemPresenter(this, this);
    }

    @Override
    protected void initView() {
        Worker worker = DistinguishedApp.getInstance().getWorker();
        tvSettingWorkerName.setText(worker.getWORKERNAME());
        tvSettingWorkerCode.setText(worker.getWORKERCODE());
        RxView.clicks(llWorkerMessage)
                .subscribeOn(AndroidSchedulers.mainThread())
                .compose(this.bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        startActivity(WorkerActivity.newInstance(SystemFragment.this.getContext()));
                    }
                });
        RxView.clicks(llEditPassword)
                .subscribeOn(AndroidSchedulers.mainThread())
                .compose(this.bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        password = "";
                        MaterialDialog materialDialog = new MaterialDialog.Builder(SystemFragment.this.getContext())
                                .title("请输入新密码")
                                .input("", "", new MaterialDialog.InputCallback() {
                                    @Override
                                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                                    }
                                })
                                .inputRange(0, 20)
                                .inputType(InputType.TYPE_TEXT_VARIATION_PASSWORD)
                                .input("", "", new MaterialDialog.InputCallback() {
                                    @Override
                                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                                    }
                                })
                                .negativeText("取消")
                                .onNegative(new MaterialDialog.SingleButtonCallback() {
                                    @Override
                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                        dialog.dismiss();
                                    }
                                })
                                .positiveText("确认")
                                .onPositive(new MaterialDialog.SingleButtonCallback() {
                                    @Override
                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                        if (dialog.getInputEditText().getText().toString().equals("")) {
                                            Toasty.info(SystemFragment.this.getContext(), "请输入新密码", Toast.LENGTH_SHORT, true).show();
                                            return;
                                        }
                                        password = dialog.getInputEditText().getText().toString();
                                        MaterialDialog materialDialog = new MaterialDialog.Builder(SystemFragment.this.getContext())
                                                .title("请再次输入新密码")
                                                .input("", "", new MaterialDialog.InputCallback() {
                                                    @Override
                                                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                                                    }
                                                })
                                                .inputRange(0, 20)
                                                .inputType(InputType.TYPE_TEXT_VARIATION_PASSWORD)
                                                .input("", "", new MaterialDialog.InputCallback() {
                                                    @Override
                                                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                                                    }
                                                })
                                                .negativeText("取消")
                                                .onNegative(new MaterialDialog.SingleButtonCallback() {
                                                    @Override
                                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                                        dialog.dismiss();
                                                    }
                                                })
                                                .positiveText("确认")
                                                .onPositive(new MaterialDialog.SingleButtonCallback() {
                                                    @Override
                                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                                        if (password.equals(dialog.getInputEditText().getText().toString())) {
                                                            presenter.editPassword(password);
                                                        } else {
                                                            Toasty.info(SystemFragment.this.getContext(), "前后密码输入不一致", Toast.LENGTH_SHORT, true).show();
                                                        }
                                                    }
                                                })
                                                .cancelable(false)
                                                .autoDismiss(false)
                                                .build();
                                        materialDialog.getInputEditText().setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});
                                        materialDialog.show();
                                        dialog.dismiss();
                                    }
                                })
                                .cancelable(false)
                                .autoDismiss(false)
                                .build();
                        materialDialog.getInputEditText().setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});
                        materialDialog.show();
                    }
                });
        RxView.clicks(llCustomerService)
                .subscribeOn(AndroidSchedulers.mainThread())
                .compose(this.bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        new MaterialDialog.Builder(SystemFragment.this.getContext())
                                .title("联系客服")
                                .content("请联系QQ：120257780")
                                .positiveText("确认")
                                .show();
                    }
                });
        RxView.clicks(llLogout)
                .subscribeOn(AndroidSchedulers.mainThread())
                .compose(this.bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        new MaterialDialog.Builder(SystemFragment.this.getContext())
                                .title("退出登录")
                                .content("您确认要退出登录?")
                                .negativeText("取消")
                                .positiveText("确认")
                                .onPositive(new MaterialDialog.SingleButtonCallback() {
                                    @Override
                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                        SystemFragment.this.getActivity().finish();
                                    }
                                })
                                .show();
                    }
                });
    }

    @Override
    public void editPasswordCallback(ResponseEntity responseEntity) {
        if (responseEntity == null) {
            Toasty.error(SystemFragment.this.getContext(), "本地错误", Toast.LENGTH_SHORT, true).show();
        } else if (ValueUtil.SUCCESS.equals(responseEntity.getCode())) {
//            Toasty.success(SystemFragment.this.getContext(), "修改成功", Toast.LENGTH_SHORT, true).show();
            Toasty.custom(DistinguishedApp.getInstance().getApplicationContext(), "修改成功", getContext().getDrawable(R.drawable.ic_action_custom_toast), Color.parseColor("#8bc34a"), Toast.LENGTH_SHORT,true, true).show();
            Intent intent = new Intent(getContext(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            Toasty.error(SystemFragment.this.getContext(), responseEntity.getMessage(), Toast.LENGTH_SHORT, true).show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.doDestroy();
    }
}
