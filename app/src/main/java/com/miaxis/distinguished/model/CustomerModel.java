package com.miaxis.distinguished.model;

import com.miaxis.distinguished.app.DistinguishedApp;
import com.miaxis.distinguished.model.entity.Customer;
import com.miaxis.distinguished.model.local.greenDao.gen.CustomerDao;

import java.util.List;

/**
 * Created by tang.yf on 2018/8/15.
 */

public class CustomerModel {

    public static void deleteAll() {
        DistinguishedApp.getInstance().getDaoSession().getCustomerDao().deleteAll();
    }

    public static void saveBankCustomers(List<Customer> customerList) {
        DistinguishedApp.getInstance().getDaoSession().getCustomerDao().insertOrReplaceInTx(customerList);
    }

    public static String getLastOpdate(String orgCode) {
        Customer customer = DistinguishedApp.getInstance().getDaoSession().getCustomerDao().queryBuilder()
                .where(CustomerDao.Properties.ORGCODE.eq(orgCode))
                .orderDesc(CustomerDao.Properties.OPDATE)
                .offset(0)
                .limit(1)
                .unique();
        if (customer == null) {
            return "";
        }
        return customer.getOPDATE();
    }

    public static List<Customer> getBankCustomers(String orgCode) {
        return DistinguishedApp.getInstance().getDaoSession().getCustomerDao().queryBuilder()
                .where(CustomerDao.Properties.ORGCODE.eq(orgCode))
                .where(CustomerDao.Properties.STATUS.eq("0"))
                .orderDesc(CustomerDao.Properties.OPDATE)
                .list();
    }

    public static void changeStatus(Customer customer) {
        customer.setSTATUS("1");
        DistinguishedApp.getInstance().getDaoSession().getCustomerDao().insertOrReplace(customer);
    }

}
