package com.miaxis.distinguished.presenter;

import android.util.Log;

import com.miaxis.distinguished.app.DistinguishedApp;
import com.miaxis.distinguished.model.MyCustomerModel;
import com.miaxis.distinguished.model.entity.MyCustomer;
import com.miaxis.distinguished.model.entity.Worker;
import com.miaxis.distinguished.model.retrofit.CustomerNet;
import com.miaxis.distinguished.model.retrofit.ResponseEntity;
import com.miaxis.distinguished.presenter.contract.MyCustomersContract;
import com.miaxis.distinguished.util.ValueUtil;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by tang.yf on 2018/8/15.
 */

public class MyCustomersPresenter extends BaseFragmentPresenter implements MyCustomersContract.Presenter {

    private MyCustomersContract.View view;

    public MyCustomersPresenter(LifecycleProvider<FragmentEvent> provider, MyCustomersContract.View view) {
        super(provider);
        this.view = view;
    }

    @Override
    public void downMyCustomers(final String workCode) {
        Log.e("asd", "开始下载");
        Observable.just(workCode)
                .subscribeOn(Schedulers.io())
                .compose(getProvider().<String>bindToLifecycle())
                .observeOn(Schedulers.io())
                .flatMap(new Function<String, ObservableSource<ResponseEntity<MyCustomer>>>() {
                    @Override
                    public ObservableSource<ResponseEntity<MyCustomer>> apply(String workCode) throws Exception {
                        CustomerNet customerNet = DistinguishedApp.getInstance().getRetrofit().create(CustomerNet.class);
//                        String opdate = MyCustomerModel.getLastOpdate(workCode);
//                        Log.e("asd", opdate);
                        return customerNet.downMyCustomers("", workCode);
                    }
                })
                .map(new Function<ResponseEntity<MyCustomer>, String>() {
                    @Override
                    public String apply(ResponseEntity<MyCustomer> MyCustomerResponseEntity) throws Exception {
                        Log.e("asd", MyCustomerResponseEntity.toString());
                        if (ValueUtil.SUCCESS.equals(MyCustomerResponseEntity.getCode())) {
                            MyCustomerModel.saveMyCustomers(MyCustomerResponseEntity.getListData(), workCode);
                            return ValueUtil.DOWNLOAD_SUCCESS;
                        }
                        return ValueUtil.DOWNLOAD_FAILED;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        if (view != null) {
                            view.downMyCustomersCallback(s);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("asd", "下载银行员工出错");
                        if (view != null) {
                            view.downMyCustomersCallback(ValueUtil.DOWNLOAD_FAILED);
                        }
                    }
                });
    }

    @Override
    public void loadMyCustomers(String orgCode) {
        Observable.just(orgCode)
                .subscribeOn(Schedulers.io())
                .compose(getProvider().<String>bindToLifecycle())
                .observeOn(Schedulers.io())
                .map(new Function<String, List<MyCustomer>>() {
                    @Override
                    public List<MyCustomer> apply(String s) throws Exception {
                        return MyCustomerModel.getMyCustomers(s);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<MyCustomer>>() {
                    @Override
                    public void accept(List<MyCustomer> MyCustomerList) throws Exception {
                        if (view != null) {
                            view.loadMyCustomersCallback(MyCustomerList);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (view != null) {
                            view.loadMyCustomersCallback(null);
                        }
                    }
                });
    }

    @Override
    public void unbindCustomer(final MyCustomer myCustomer) {
        Observable.just(myCustomer)
                .subscribeOn(Schedulers.io())
                .compose(getProvider().<MyCustomer>bindToLifecycle())
                .observeOn(Schedulers.io())
                .flatMap(new Function<MyCustomer, ObservableSource<ResponseEntity>>() {
                    @Override
                    public ObservableSource<ResponseEntity> apply(MyCustomer myCustomer) throws Exception {
                        Worker worker = DistinguishedApp.getInstance().getWorker();
                        CustomerNet customerNet = DistinguishedApp.getInstance().getRetrofit().create(CustomerNet.class);
                        return customerNet.unbindCustomer(myCustomer.getID(), worker.getWORKERCODE());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseEntity>() {
                    @Override
                    public void accept(ResponseEntity responseEntity) throws Exception {
                        if (view != null) {
                            view.unbindCustomerCallback(responseEntity, myCustomer);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (view != null) {
                            view.unbindCustomerCallback(null, null);
                        }
                    }
                });
    }

    @Override
    public void doDestroy() {
        this.view = null;
    }
}