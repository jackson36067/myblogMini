package com.jackson.vo;

import java.io.Serializable;
import java.util.Objects;

public class LoginResultVO implements Serializable {
    private Long id;
    private String token;
    private String openid;
    private Integer points;
    private Boolean isSignIn;

    public LoginResultVO() {
    }

    public LoginResultVO(Long id, String token, String openid) {
        this.id = id;
        this.token = token;
        this.openid = openid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Boolean getSignIn() {
        return isSignIn;
    }

    public void setSignIn(Boolean signIn) {
        isSignIn = signIn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginResultVO that = (LoginResultVO) o;
        return Objects.equals(id, that.id) && Objects.equals(token, that.token) && Objects.equals(openid, that.openid) && Objects.equals(points, that.points) && Objects.equals(isSignIn, that.isSignIn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, token, openid, points, isSignIn);
    }

    @Override
    public String toString() {
        return "LoginResultVO{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", openid='" + openid + '\'' +
                ", points=" + points +
                ", isSignIn=" + isSignIn +
                '}';
    }
}
