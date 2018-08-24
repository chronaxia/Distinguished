package com.miaxis.distinguished.presenter.contract;

import com.miaxis.distinguished.model.retrofit.ResponseEntity;

/**
 * Created by tang.yf on 2018/8/15.
 */

public interface WorkerContract {
    interface Presenter {
        void editMobile(String mobile);
        void doDestroy();
    }

    interface View {
        void editMobileCallback(ResponseEntity responseEntity);
    }
}
