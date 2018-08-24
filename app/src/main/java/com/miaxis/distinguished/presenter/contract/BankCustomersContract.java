package com.miaxis.distinguished.presenter.contract;

import com.miaxis.distinguished.model.entity.Customer;
import com.miaxis.distinguished.model.retrofit.ResponseEntity;

import java.util.List;

/**
 * Created by tang.yf on 2018/8/15.
 */

public interface BankCustomersContract {
    interface Presenter {
        void downBankCustomers(String orgCode);
        void loadBankCustomers(String orgCode);
        void bindCustomer(Customer customer);
        void doDestroy();
    }

    interface View {
        void downBankCustomersCallback(String message);
        void loadBankCustomersCallback(List<Customer> customerList);
        void bindCustomerCallback(ResponseEntity responseEntity, Customer customer);
    }
}
