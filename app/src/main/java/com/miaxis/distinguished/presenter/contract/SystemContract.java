package com.miaxis.distinguished.presenter.contract;

import com.miaxis.distinguished.model.retrofit.ResponseEntity;

/**
 * Created by tang.yf on 2018/8/15.
 */

public interface SystemContract {
    interface Presenter {
        void editPassword(String password);
        void doDestroy();
    }

    interface View {
        void editPasswordCallback(ResponseEntity responseEntity);
    }
}
