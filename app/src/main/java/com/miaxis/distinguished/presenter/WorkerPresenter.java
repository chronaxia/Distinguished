package com.miaxis.distinguished.presenter;

import android.util.Log;

import com.miaxis.distinguished.app.DistinguishedApp;
import com.miaxis.distinguished.model.entity.Worker;
import com.miaxis.distinguished.model.retrofit.ResponseEntity;
import com.miaxis.distinguished.model.retrofit.WorkerNet;
import com.miaxis.distinguished.presenter.contract.WorkerContract;
import com.miaxis.distinguished.util.ValueUtil;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by tang.yf on 2018/8/15.
 */

public class WorkerPresenter extends BaseActivityPresenter implements WorkerContract.Presenter {

    private WorkerContract.View view;

    public WorkerPresenter(LifecycleProvider<ActivityEvent> provider, WorkerContract.View view) {
        super(provider);
        this.view = view;
    }

    @Override
    public void editMobile(final String mobile) {
        Observable.just(mobile)
                .subscribeOn(Schedulers.io())
                .compose(getProvider().<String>bindToLifecycle())
                .observeOn(Schedulers.io())
                .flatMap(new Function<String, ObservableSource<ResponseEntity>>() {
                    @Override
                    public ObservableSource<ResponseEntity> apply(String s) throws Exception {
                        Worker worker = DistinguishedApp.getInstance().getWorker();
                        WorkerNet workerNet = DistinguishedApp.getInstance().getRetrofit().create(WorkerNet.class);
                        return workerNet.editMobile(worker.getWORKERCODE(), s);
                    }
                })
                .doOnNext(new Consumer<ResponseEntity>() {
                    @Override
                    public void accept(ResponseEntity responseEntity) throws Exception {
                        if (ValueUtil.SUCCESS.equals(responseEntity.getCode())) {
                            DistinguishedApp.getInstance().getWorker().setWORKERPHONE(mobile);
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseEntity>() {
                    @Override
                    public void accept(ResponseEntity responseEntity) throws Exception {
                        if (view != null) {
                            view.editMobileCallback(responseEntity);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("asd", "修改手机号失败");
                        if (view != null) {
                            view.editMobileCallback(null);
                        }
                    }
                });
    }

    @Override
    public void doDestroy() {
        this.view = view;
    }
}
