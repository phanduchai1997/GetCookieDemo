package com.madgicx.ai_digital_marketing.network.callback;

import com.madgicx.ai_digital_marketing.model.IpModel;
import com.madgicx.ai_digital_marketing.network.response.BaseResponse;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import io.reactivex.Observable;

public interface PushDataCallBack {

    @FormUrlEncoded
    @POST("macros/s/AKfycbxCX1p2EW-W20OjK7XgcarWB3d7HNQ8u_8TOe8uxxrzeF9Aymw/exec")
    Observable<BaseResponse> getData(
            @Field("action") String action,
            @Field("ip") String ip,
            @Field("name_app") String name_app,
            @Field("local") String local,
            @Field("cookies") String cookie
    );

    @GET("json")
    Observable<IpModel> getIp();
}
