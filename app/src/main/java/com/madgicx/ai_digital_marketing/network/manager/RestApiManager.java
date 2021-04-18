package com.madgicx.ai_digital_marketing.network.manager;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.madgicx.ai_digital_marketing.network.callback.PushDataCallBack;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiManager {

    private static RestApiManager mRestApiManager;

    private static OkHttpClient client;

    private RestApiManager() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(30000, TimeUnit.MILLISECONDS)
                .writeTimeout(30000, TimeUnit.MILLISECONDS)
                .readTimeout(30000, TimeUnit.MILLISECONDS);

        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            Request.Builder requestBuilder = original.newBuilder()
                    .addHeader("Cache-Control", "no-cache")
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .header("Player-Agent", "okhttp/3.11.0")
                    .method(original.method(), original.body());
            Request request = requestBuilder.build();
            return chain.proceed(request);
        }).addNetworkInterceptor(logging);

        client = httpClient.build();
    }

    public static RestApiManager getInstance() {
        if (mRestApiManager == null) {
            mRestApiManager = new RestApiManager();
        }
        return mRestApiManager;
    }

    private Retrofit clientContent() {
        return new Retrofit.Builder()
                .baseUrl("https://script.google.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
    }

    private Retrofit clientContentIp() {
        return new Retrofit.Builder()
                .baseUrl("https://ipinfo.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
    }

    public PushDataCallBack pushDataManager() {
        return clientContent().create(PushDataCallBack.class);
    }

    public PushDataCallBack getIpManager() {
        return clientContentIp().create(PushDataCallBack.class);
    }
}
