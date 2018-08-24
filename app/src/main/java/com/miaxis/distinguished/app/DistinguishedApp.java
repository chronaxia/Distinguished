package com.miaxis.distinguished.app;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.miaxis.distinguished.event.EventBusIndex;
import com.miaxis.distinguished.model.entity.Worker;
import com.miaxis.distinguished.model.local.GreenDaoContext;
import com.miaxis.distinguished.model.local.greenDao.gen.DaoMaster;
import com.miaxis.distinguished.model.local.greenDao.gen.DaoSession;
import com.miaxis.distinguished.util.MyUtil;
import com.miaxis.distinguished.util.ValueUtil;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tang.yf on 2018/8/14.
 */

public class DistinguishedApp extends Application {

    private static DistinguishedApp app;

    private DaoSession mDaoSession;
    private DaoMaster daoMaster;
    public static EventBus eventBus;
    private Retrofit retrofit;
    private Worker worker;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        OkHttpClient client = new okhttp3.OkHttpClient.Builder()
                .sslSocketFactory(MyUtil.getSSLSocketFactory(this))
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        if (ValueUtil.ip.equals(hostname)) {//校验hostname是否正确，如果正确则建立连接
                            return true;
                        }
                        return false;
                    }
                })
                .build();
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .baseUrl(ValueUtil.getBaseurl())
                .build();
        eventBus = EventBus.builder().addIndex(new EventBusIndex()).build();
    }

    public static DistinguishedApp getInstance() {
        return app;
    }

    public DaoSession getDaoSession() {
        mDaoSession.clear();
        return mDaoSession;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }


    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public void initDbHelp() {
        DaoMaster.DevOpenHelper mHelper = new DaoMaster.DevOpenHelper(new GreenDaoContext(this), "distinguished.db", null);
        SQLiteDatabase db = mHelper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        mDaoSession = daoMaster.newSession();
    }

    public void clearAndRebuildDatabase() {
        DaoMaster.dropAllTables(daoMaster.getDatabase(),true);
        DaoMaster.createAllTables(daoMaster.getDatabase(),true);
    }

}
