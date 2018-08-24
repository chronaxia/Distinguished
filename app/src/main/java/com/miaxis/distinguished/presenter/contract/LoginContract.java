package com.miaxis.distinguished.presenter.contract;


import com.miaxis.distinguished.model.entity.Config;

/**
 * Created by 一非 on 2018/6/27.
 */

public interface LoginContract {
    interface Presenter {
        void login(Config config);
        void loadConfig();
        void loginResult(boolean reslt);
        void cancelLoginSelf();
        void doDestroy();
    }

    interface View {
        void loginResult(boolean result);
        void loadConfig(Config config);
    }
}
