
package com.madgicx.ai_digital_marketing.model;

public class IpModel {

    public String ip;
    public String city;
    public String region;
    public String country;
    public String loc;
    public String org;
    public String postal;
    public String timezone;
    public String readme;

    public IpModel() {
    }

    public IpModel(String ip, String city, String region, String country, String loc, String org, String postal, String timezone, String readme) {
        this.ip = ip;
        this.city = city;
        this.region = region;
        this.country = country;
        this.loc = loc;
        this.org = org;
        this.postal = postal;
        this.timezone = timezone;
        this.readme = readme;
    }
}
