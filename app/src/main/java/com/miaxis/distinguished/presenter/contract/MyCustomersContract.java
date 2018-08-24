package com.miaxis.distinguished.presenter.contract;

import com.miaxis.distinguished.model.entity.MyCustomer;
import com.miaxis.distinguished.model.retrofit.ResponseEntity;

import java.util.List;

/**
 * Created by tang.yf on 2018/8/15.
 */

public interface MyCustomersContract {
    interface Presenter {
        void downMyCustomers(String orgCode);
        void loadMyCustomers(String orgCode);
        void unbindCustomer(MyCustomer myCustomer);
        void doDestroy();
    }

    interface View {
        void downMyCustomersCallback(String message);
        void loadMyCustomersCallback(List<MyCustomer> customerList);
        void unbindCustomerCallback(ResponseEntity responseEntity, MyCustomer myCustomer);
    }
}
