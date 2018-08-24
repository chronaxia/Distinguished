package com.miaxis.distinguished.model;

import com.miaxis.distinguished.app.DistinguishedApp;
import com.miaxis.distinguished.model.entity.MyCustomer;
import com.miaxis.distinguished.model.local.greenDao.gen.MyCustomerDao;

import java.util.List;

/**
 * Created by tang.yf on 2018/8/15.
 */

public class MyCustomerModel {

    public static void saveMyCustomers(List<MyCustomer> myCustomerList, String workerCode) {
        for (MyCustomer myCustomer : myCustomerList) {
            myCustomer.setWORKERCODE(workerCode);
        }
        DistinguishedApp.getInstance().getDaoSession().getMyCustomerDao().deleteAll();
        DistinguishedApp.getInstance().getDaoSession().getMyCustomerDao().insertOrReplaceInTx(myCustomerList);
    }

    public static String getLastOpdate(String workerCode) {
        MyCustomer myCustomer = DistinguishedApp.getInstance().getDaoSession().getMyCustomerDao().queryBuilder()
                .where(MyCustomerDao.Properties.WORKERCODE.eq(workerCode))
                .orderDesc(MyCustomerDao.Properties.OPDATE)
                .offset(0)
                .limit(1)
                .unique();
        if (myCustomer == null) {
            return "";
        }
        return myCustomer.getOPDATE();
    }

    public static List<MyCustomer> getMyCustomers(String workerCode) {
        return DistinguishedApp.getInstance().getDaoSession().getMyCustomerDao().queryBuilder()
                .where(MyCustomerDao.Properties.WORKERCODE.eq(workerCode))
                .where(MyCustomerDao.Properties.STATUS.eq("1"))
                .orderDesc(MyCustomerDao.Properties.OPDATE)
                .list();
    }
    
}
