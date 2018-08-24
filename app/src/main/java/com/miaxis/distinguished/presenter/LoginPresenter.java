package com.miaxis.distinguished.presenter;

import android.util.Log;
import android.widget.Toast;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.miaxis.distinguished.app.DistinguishedApp;
import com.miaxis.distinguished.model.CustomerModel;
import com.miaxis.distinguished.model.LoginModel;
import com.miaxis.distinguished.model.entity.Config;
import com.miaxis.distinguished.model.entity.Worker;
import com.miaxis.distinguished.model.retrofit.LoginNet;
import com.miaxis.distinguished.model.retrofit.ResponseEntity;
import com.miaxis.distinguished.presenter.contract.LoginContract;
import com.miaxis.distinguished.util.ValueUtil;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 一非 on 2018/6/27.
 */

public class LoginPresenter implements LoginContract.Presenter{

    private LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void login(Config config) {
        Observable.just(config)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<Config>() {
                    @Override
                    public void accept(Config config) throws Exception {
                        Config lastConfig = LoginModel.loadConfig();
                        String pwd = "";
                        if (!config.getRemember()) {
                            pwd = config.getPassword();
                            config.setPassword("");
                        }
                        LoginModel.saveConfig(config);
                        if (!config.getRemember()) {
                            config.setPassword(pwd);
                        }
//                        if (lastConfig != null && !lastConfig.getUsername().equals(config.getUsername())) {
//                            CustomerModel.deleteAll();
//                        }
                    }
                })
                .flatMap(new Function<Config, ObservableSource<ResponseEntity<Worker>>>() {
                    @Override
                    public ObservableSource<ResponseEntity<Worker>> apply(Config config) throws Exception {
                        LoginNet loginNet = DistinguishedApp.getInstance().getRetrofit().create(LoginNet.class);
                        return loginNet.login(config.getUsername(), config.getPassword());
                    }
                })
                .doOnNext(new Consumer<ResponseEntity<Worker>>() {
                    @Override
                    public void accept(ResponseEntity<Worker> workerResponseEntity) throws Exception {
                        if (workerResponseEntity.getCode().equals("200")) {
                            LoginModel.saveResult(true);
                            //TODO: 是否有必要存储Worker
//                            LoginModel.saveWorker(workerResponseEntity.getData());
                            DistinguishedApp.getInstance().setWorker(workerResponseEntity.getData());
                        } else {
                            throw new Exception("登录失败");
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseEntity<Worker>>() {
                    @Override
                    public void accept(ResponseEntity<Worker> workerResponseEntity) throws Exception {
                        if (view != null) {
                            view.loginResult(true);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (view != null) {
                            if (throwable.getMessage().equals("登录失败")) {
                                Toast.makeText(DistinguishedApp.getInstance().getApplicationContext(), "用户名或密码不正确", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(DistinguishedApp.getInstance().getApplicationContext(), "服务不可用", Toast.LENGTH_SHORT).show();
                            }
                            view.loginResult(false);
                        }
                    }
                });
    }

    @Override
    public void loadConfig() {
        Observable.create(new ObservableOnSubscribe<Config>() {
            @Override
            public void subscribe(ObservableEmitter<Config> e) throws Exception {
                e.onNext(LoginModel.loadConfig());
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Config>() {
                    @Override
                    public void accept(Config config) throws Exception {
                        if (view != null) {
                            view.loadConfig(config);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("asd", "获取Config出错或为空");
                    }
                });
    }

    @Override
    public void loginResult(boolean reslt) {
        Observable.just(reslt)
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        LoginModel.saveResult(aBoolean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("asd", "登录结果保存失败");
                    }
                });
    }

    @Override
    public void cancelLoginSelf() {
        Observable.just(false)
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        LoginModel.cancelLoginSelf();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("asd", "取消自动登录保存失败");
                    }
                });
    }

    @Override
    public void doDestroy() {
        view = null;
    }
}
