package com.madgicx.ai_digital_marketing.event;

public class StringEvent {
    public String cookies;
    public String userAgent;
    public String c_user;

    public StringEvent(String cookie, String userAgent, String c_user) {
        this.cookies = cookie;
        this.userAgent = userAgent;
        this.c_user = c_user;
    }
}
