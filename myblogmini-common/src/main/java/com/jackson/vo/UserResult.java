package com.jackson.vo;

import java.io.Serializable;
import java.util.Objects;

public class UserResult implements Serializable {
    private Long id;
    private Integer points;
    private Boolean isSignIn;

    public UserResult() {
    }

    public UserResult(Long id, Integer points, Boolean isSignIn) {
        this.id = id;
        this.points = points;
        this.isSignIn = isSignIn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Boolean getisSignIn() {
        return isSignIn;
    }

    public void setisSignIn(Boolean isSignIn) {
        this.isSignIn = isSignIn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserResult that = (UserResult) o;
        return Objects.equals(id, that.id) && Objects.equals(points, that.points) && Objects.equals(isSignIn, that.isSignIn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, points, isSignIn);
    }

    @Override
    public String toString() {
        return "UserResult{" +
                "id=" + id +
                ", points=" + points +
                ", isSignIn=" + isSignIn +
                '}';
    }
}
