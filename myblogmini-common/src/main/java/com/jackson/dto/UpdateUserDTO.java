package com.jackson.dto;

import java.io.Serializable;
import java.util.Objects;

public class UpdateUserDTO implements Serializable {
    private String avatar;
    private String nickName;

    public UpdateUserDTO() {
    }

    public UpdateUserDTO(String avatar, String nickName) {
        this.avatar = avatar;
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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
        UpdateUserDTO that = (UpdateUserDTO) o;
        return Objects.equals(avatar, that.avatar) && Objects.equals(nickName, that.nickName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(avatar, nickName);
    }

    @Override
    public String toString() {
        return "UpdateUserDTO{" +
                "avatar='" + avatar + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
