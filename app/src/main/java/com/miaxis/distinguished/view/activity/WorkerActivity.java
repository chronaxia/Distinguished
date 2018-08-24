package com.miaxis.distinguished.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.text.InputType;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.jakewharton.rxbinding2.view.RxView;
import com.miaxis.distinguished.R;
import com.miaxis.distinguished.app.DistinguishedApp;
import com.miaxis.distinguished.model.entity.Worker;
import com.miaxis.distinguished.model.retrofit.ResponseEntity;
import com.miaxis.distinguished.presenter.WorkerPresenter;
import com.miaxis.distinguished.presenter.contract.WorkerContract;
import com.miaxis.distinguished.util.ValueUtil;
import com.miaxis.distinguished.view.fragment.SystemFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class WorkerActivity extends BaseActivity implements WorkerContract.View {

    @BindView(R.id.tb_worker)
    Toolbar tbWorker;
    @BindView(R.id.tv_worker_org)
    TextView tvWorkerOrg;
    @BindView(R.id.tv_worker_code)
    TextView tvWorkerCode;
    @BindView(R.id.tv_worker_name)
    TextView tvWorkerName;
    @BindView(R.id.tv_worker_username)
    TextView tvWorkerUsername;
    @BindView(R.id.tv_worker_mobile)
    TextView tvWorkerMobile;
    @BindView(R.id.iv_edit_mobile)
    ImageView ivEditMobile;
//    @BindView(R.id.btn_edit_mobile)
//    Button btnEditMobile;

    private WorkerContract.Presenter presenter;

    public static Intent newInstance(Context context) {
        return new Intent(context, WorkerActivity.class);
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_worker;
    }

    @Override
    protected void initData() {
        presenter = new WorkerPresenter(this, this);
    }

    @Override
    protected void initView() {
        tbWorker.setTitle("个人信息");
        setSupportActionBar(tbWorker);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Worker worker = DistinguishedApp.getInstance().getWorker();
        tvWorkerOrg.setText(worker.getORGNAME());
        tvWorkerCode.setText(worker.getWORKERCODE());
        tvWorkerName.setText(worker.getWORKERNAME());
        tvWorkerUsername.setText(worker.getACCOUNT());
        tvWorkerMobile.setText(worker.getWORKERPHONE());
        RxView.clicks(ivEditMobile)
                .subscribeOn(AndroidSchedulers.mainThread())
                .compose(this.bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        MaterialDialog materialDialog = new MaterialDialog.Builder(WorkerActivity.this)
                                .title("请输入新的联系方式")
                                .input("", "", new MaterialDialog.InputCallback() {
                                    @Override
                                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                                    }
                                })
                                .inputRange(0, 11)
                                .inputType(InputType.TYPE_CLASS_PHONE)
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
                                        String mobile = dialog.getInputEditText().getText().toString();
                                        if ("".equals(mobile)) {
                                            Toasty.info(WorkerActivity.this, "请输入联系方式", Toast.LENGTH_SHORT, true).show();
                                            return;
                                        } else if (!ValueUtil.isPhone(mobile)) {
                                            Toasty.info(WorkerActivity.this, "请输入正确的手机号码", Toast.LENGTH_SHORT, true).show();
                                            return;
                                        }
                                        presenter.editMobile(mobile);
                                        dialog.dismiss();
                                    }
                                })
                                .cancelable(false)
                                .autoDismiss(false)
                                .build();
                        materialDialog.getInputEditText().setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});
                        materialDialog.show();
                    }
                });
    }

    @Override
    public void editMobileCallback(ResponseEntity responseEntity) {
        if (responseEntity == null) {
            Toasty.error(this, "本地错误", Toast.LENGTH_SHORT, true).show();
        } else if (ValueUtil.SUCCESS.equals(responseEntity.getCode())) {
            tvWorkerMobile.setText(DistinguishedApp.getInstance().getWorker().getWORKERPHONE());
//            Toasty.success(this, "修改成功", Toast.LENGTH_SHORT, true).show();
            Toasty.custom(this, responseEntity.getMessage(), getDrawable(R.drawable.ic_action_custom_toast), Color.parseColor("#8bc34a"), Toast.LENGTH_SHORT,true, true).show();
        } else {
            Toasty.error(this, responseEntity.getMessage(), Toast.LENGTH_SHORT, true).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.doDestroy();
    }
}
