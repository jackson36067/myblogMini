package com.jackson.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@ConfigurationProperties(prefix = "wechat")
public class WeChatProperties {
    private String appid; //小程序的appid
    private String secret; //小程序的秘钥

    public WeChatProperties() {
    }

    public WeChatProperties(String appid, String secret) {
        this.appid = appid;
        this.secret = secret;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeChatProperties that = (WeChatProperties) o;
        return Objects.equals(appid, that.appid) && Objects.equals(secret, that.secret);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appid, secret);
    }

    @Override
    public String toString() {
        return "WeChatProperties{" +
                "appid='" + appid + '\'' +
                ", secret='" + secret + '\'' +
                '}';
    }
}
