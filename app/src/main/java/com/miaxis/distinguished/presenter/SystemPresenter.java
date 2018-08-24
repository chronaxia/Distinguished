package com.miaxis.distinguished.presenter;

import android.util.Log;

import com.miaxis.distinguished.app.DistinguishedApp;
import com.miaxis.distinguished.model.retrofit.ResponseEntity;
import com.miaxis.distinguished.model.retrofit.WorkerNet;
import com.miaxis.distinguished.presenter.contract.SystemContract;
import com.miaxis.distinguished.util.ValueUtil;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.FragmentEvent;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by tang.yf on 2018/8/15.
 */

public class SystemPresenter extends BaseFragmentPresenter implements SystemContract.Presenter {

    private SystemContract.View view;

    public SystemPresenter(LifecycleProvider<FragmentEvent> provider, SystemContract.View view) {
        super(provider);
        this.view = view;
    }

    @Override
    public void editPassword(String password) {
        Observable.just(password)
                .subscribeOn(Schedulers.io())
                .compose(getProvider().<String>bindToLifecycle())
                .observeOn(Schedulers.io())
                .flatMap(new Function<String, ObservableSource<ResponseEntity>>() {
                    @Override
                    public ObservableSource<ResponseEntity> apply(String password) throws Exception {
                        String workerCode = DistinguishedApp.getInstance().getWorker().getWORKERCODE();
                        WorkerNet workerNet = DistinguishedApp.getInstance().getRetrofit().create(WorkerNet.class);
                        return workerNet.editPassword(workerCode, password);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseEntity>() {
                    @Override
                    public void accept(ResponseEntity responseEntity) throws Exception {
                        if (view != null) {
                            view.editPasswordCallback(responseEntity);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("asd", "修改密码出错");
                        if (view != null) {
                            view.editPasswordCallback(null);
                        }
                    }
                });
    }

    @Override
    public void doDestroy() {
        this.view = null;
    }
}
