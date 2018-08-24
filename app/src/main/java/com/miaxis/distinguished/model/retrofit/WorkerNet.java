package com.miaxis.distinguished.model.retrofit;

import com.miaxis.distinguished.model.entity.Worker;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by tang.yf on 2018/8/15.
 */

public interface WorkerNet {
    @FormUrlEncoded
    @POST("bankvip/api/updateWorkerByCode")
    Observable<ResponseEntity> editPassword(@Field("WORKERCODE") String workerCode, @Field("PASSWORD") String password);

    @FormUrlEncoded
    @POST("bankvip/api/updateWorkerByCode")
    Observable<ResponseEntity> editMobile(@Field("WORKERCODE") String workerCode, @Field("WORKERPHONE") String workerPhone);
}
