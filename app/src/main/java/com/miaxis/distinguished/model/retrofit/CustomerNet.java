package com.miaxis.distinguished.model.retrofit;

import com.miaxis.distinguished.model.entity.Customer;
import com.miaxis.distinguished.model.entity.MyCustomer;
import com.miaxis.distinguished.model.entity.Worker;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by tang.yf on 2018/8/15.
 */

public interface CustomerNet {
    @FormUrlEncoded
    @POST("bankvip/api/downCustByBankCodeToAndroid")
    Observable<ResponseEntity<Customer>> downBankCustomers(@Field("OPDATE") String opdate, @Field("ORGCODE") String orgCode);

    @FormUrlEncoded
    @POST("bankvip/api/downCustByWorkerCodeToAndroid")
    Observable<ResponseEntity<MyCustomer>> downMyCustomers(@Field("OPDATE") String opdate, @Field("WORKERCODE") String workerCode);

    @FormUrlEncoded
    @POST("bankvip/api/bindCustomer")
    Observable<ResponseEntity> bindCustomer(@Field("CUSTOMERID") String customerId, @Field("WORKERCODE") String workerCode);

    @FormUrlEncoded
    @POST("bankvip/api/unBindCustomer")
    Observable<ResponseEntity> unbindCustomer(@Field("CUSTOMERID") String customerId, @Field("WORKERCODE") String workerCode);
}
