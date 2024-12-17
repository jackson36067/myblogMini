package com.jackson.vo;

import java.io.Serializable;
import java.util.Objects;

public class UserSignResult implements Serializable {
    private Integer continuousSignInDays;

    public UserSignResult() {
    }

    public UserSignResult(Integer continuousSignInDays) {
        this.continuousSignInDays = continuousSignInDays;
    }

    public Integer getContinuousSignInDays() {
        return continuousSignInDays;
    }

    public void setContinuousSignInDays(Integer continuousSignInDays) {
        this.continuousSignInDays = continuousSignInDays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSignResult that = (UserSignResult) o;
        return Objects.equals(continuousSignInDays, that.continuousSignInDays);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(continuousSignInDays);
    }

    @Override
    public String toString() {
        return "UserSignResult{" +
                "continuousSignInDays=" + continuousSignInDays +
                '}';
    }
}
