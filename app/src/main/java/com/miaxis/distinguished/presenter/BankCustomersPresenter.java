package com.miaxis.distinguished.presenter;

import android.util.Log;

import com.miaxis.distinguished.app.DistinguishedApp;
import com.miaxis.distinguished.model.CustomerModel;
import com.miaxis.distinguished.model.entity.Customer;
import com.miaxis.distinguished.model.entity.Worker;
import com.miaxis.distinguished.model.retrofit.CustomerNet;
import com.miaxis.distinguished.model.retrofit.ResponseEntity;
import com.miaxis.distinguished.presenter.contract.BankCustomersContract;
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

public class BankCustomersPresenter extends BaseFragmentPresenter implements BankCustomersContract.Presenter {

    private BankCustomersContract.View view;

    public BankCustomersPresenter(LifecycleProvider<FragmentEvent> provider, BankCustomersContract.View view) {
        super(provider);
        this.view = view;
    }

    @Override
    public void downBankCustomers(String orgCode) {
        Log.e("asd", "开始下载");
        Observable.just(orgCode)
                .subscribeOn(Schedulers.io())
                .compose(getProvider().<String>bindToLifecycle())
                .observeOn(Schedulers.io())
                .flatMap(new Function<String, ObservableSource<ResponseEntity<Customer>>>() {
                    @Override
                    public ObservableSource<ResponseEntity<Customer>> apply(String orgCode) throws Exception {
                        CustomerNet customerNet = DistinguishedApp.getInstance().getRetrofit().create(CustomerNet.class);
                        String opdate = CustomerModel.getLastOpdate(orgCode);
                        Log.e("asd", opdate);
                        return customerNet.downBankCustomers(opdate, orgCode);
                    }
                })
                .map(new Function<ResponseEntity<Customer>, String>() {
                    @Override
                    public String apply(ResponseEntity<Customer> customerResponseEntity) throws Exception {
                        Log.e("asd", customerResponseEntity.toString());
                        if (ValueUtil.SUCCESS.equals(customerResponseEntity.getCode())) {
                            CustomerModel.saveBankCustomers(customerResponseEntity.getListData());
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
                            view.downBankCustomersCallback(s);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("asd", "下载银行员工出错");
                        if (view != null) {
                            view.downBankCustomersCallback(ValueUtil.DOWNLOAD_FAILED);
                        }
                    }
                });
    }

    @Override
    public void loadBankCustomers(String orgCode) {
        Observable.just(orgCode)
                .subscribeOn(Schedulers.io())
                .compose(getProvider().<String>bindToLifecycle())
                .observeOn(Schedulers.io())
                .map(new Function<String, List<Customer>>() {
                    @Override
                    public List<Customer> apply(String s) throws Exception {
                        return CustomerModel.getBankCustomers(s);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Customer>>() {
                    @Override
                    public void accept(List<Customer> customerList) throws Exception {
                        if (view != null) {
                            view.loadBankCustomersCallback(customerList);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (view != null) {
                            view.loadBankCustomersCallback(null);
                        }
                    }
                });
    }

    @Override
    public void bindCustomer(final Customer customer) {
        Observable.just(customer)
                .subscribeOn(Schedulers.io())
                .compose(getProvider().<Customer>bindToLifecycle())
                .observeOn(Schedulers.io())
                .flatMap(new Function<Customer, ObservableSource<ResponseEntity>>() {
                    @Override
                    public ObservableSource<ResponseEntity> apply(Customer customer) throws Exception {
                        Worker worker = DistinguishedApp.getInstance().getWorker();
                        CustomerNet customerNet = DistinguishedApp.getInstance().getRetrofit().create(CustomerNet.class);
                        return customerNet.bindCustomer(customer.getID(), worker.getWORKERCODE());
                    }
                })
                .doOnNext(new Consumer<ResponseEntity>() {
                    @Override
                    public void accept(ResponseEntity responseEntity) throws Exception {
                        if (ValueUtil.SUCCESS.equals(responseEntity.getCode())) {
                            CustomerModel.changeStatus(customer);
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseEntity>() {
                    @Override
                    public void accept(ResponseEntity responseEntity) throws Exception {
                        if (view != null) {
                            view.bindCustomerCallback(responseEntity, customer);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (view != null) {
                            view.bindCustomerCallback(null, null);
                        }
                    }
                });
    }

    @Override
    public void doDestroy() {
        this.view = null;
    }
}
