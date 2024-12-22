package com.jackson.dto;

import java.io.Serializable;
import java.util.Objects;

public class UserLoginDTO implements Serializable {
    // 调取微信登录后获取到的code,用于获取用户openid(唯一标识)
    private String code;
    private String avatar;
    private String gender;
    private String nickName;
    public UserLoginDTO() {
    }

    public UserLoginDTO(String code, String avatar, String gender, String nickName) {
        this.code = code;
        this.avatar = avatar;
        this.gender = gender;
        this.nickName = nickName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserLoginDTO that = (UserLoginDTO) o;
        return Objects.equals(code, that.code) && Objects.equals(avatar, that.avatar) && Objects.equals(gender, that.gender) && Objects.equals(nickName, that.nickName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, avatar, gender, nickName);
    }

    @Override
    public String toString() {
        return "UserLoginDTO{" +
                "code='" + code + '\'' +
                ", avatar='" + avatar + '\'' +
                ", gender='" + gender + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
