package com.miaxis.distinguished.model.retrofit;

import com.miaxis.distinguished.model.entity.Worker;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 一非 on 2018/7/2.
 */

public interface LoginNet {
    @FormUrlEncoded
    @POST("bankvip/api/loginForAndroid")
    Observable<ResponseEntity<Worker>> login(@Field("ACCOUNT") String account, @Field("PASSWORD") String password);
}
