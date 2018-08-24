package com.miaxis.distinguished.model;

import com.miaxis.distinguished.app.DistinguishedApp;
import com.miaxis.distinguished.model.entity.Config;
import com.miaxis.distinguished.model.entity.Worker;

/**
 * Created by 一非 on 2018/6/27.
 */

public class LoginModel {

    public static Config loadConfig() {
        return DistinguishedApp.getInstance().getDaoSession().getConfigDao().load(1L);
    }

    public static void saveConfig(Config config) {
        DistinguishedApp.getInstance().getDaoSession().getConfigDao().insertOrReplace(config);
    }

    public static void saveResult(boolean result) {
        Config config = DistinguishedApp.getInstance().getDaoSession().getConfigDao().load(1L);
        config.setLastLogin(result);
        saveConfig(config);
    }

    public static void cancelLoginSelf() {
        Config config = DistinguishedApp.getInstance().getDaoSession().getConfigDao().load(1L);
        config.setLogingSelf(false);
        saveConfig(config);
    }

    public static void saveWorker(Worker worker) {
        DistinguishedApp.getInstance().getDaoSession().getWorkerDao().deleteAll();
        DistinguishedApp.getInstance().getDaoSession().getWorkerDao().insert(worker);
    }

}
