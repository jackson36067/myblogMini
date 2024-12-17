package com.jackson.dto;

import java.io.Serializable;
import java.util.Objects;

public class UserLoginDTO implements Serializable {
    // 调取微信登录后获取到的code,用于获取用户openid(唯一标识)
    private String code;

    public UserLoginDTO() {
    }

    public UserLoginDTO(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserLoginDTO that = (UserLoginDTO) o;
        return Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(code);
    }

    @Override
    public String toString() {
        return "UserLoginDTO{" +
                "code='" + code + '\'' +
                '}';
    }
}
