package com.jackson.vo;

import java.io.Serializable;
import java.util.Objects;

public class UserResult implements Serializable {
    private Long id;
    private Integer points;
    private Boolean isSignIn;
    private Integer totalLike;// 用户文章总获赞数
    private Integer totalFriend; // 用户朋友总数
    private Integer totalFollow; // 用户关注总数
    private Integer totalFans; // 用户粉丝总数

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

    public Integer getTotalFans() {
        return totalFans;
    }

    public void setTotalFans(Integer totalFans) {
        this.totalFans = totalFans;
    }

    public Integer getTotalFollow() {
        return totalFollow;
    }

    public void setTotalFollow(Integer totalFollow) {
        this.totalFollow = totalFollow;
    }

    public Integer getTotalFriend() {
        return totalFriend;
    }

    public void setTotalFriend(Integer totalFriend) {
        this.totalFriend = totalFriend;
    }

    public Integer getTotalLike() {
        return totalLike;
    }

    public void setTotalLike(Integer totalLike) {
        this.totalLike = totalLike;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserResult that = (UserResult) o;
        return Objects.equals(id, that.id) && Objects.equals(points, that.points) && Objects.equals(isSignIn, that.isSignIn) && Objects.equals(totalLike, that.totalLike) && Objects.equals(totalFriend, that.totalFriend) && Objects.equals(totalFollow, that.totalFollow) && Objects.equals(totalFans, that.totalFans);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, points, isSignIn, totalLike, totalFriend, totalFollow, totalFans);
    }

    @Override
    public String toString() {
        return "UserResult{" +
                "id=" + id +
                ", points=" + points +
                ", isSignIn=" + isSignIn +
                ", totalLike=" + totalLike +
                ", totalFriend=" + totalFriend +
                ", totalFollow=" + totalFollow +
                ", totalFans=" + totalFans +
                '}';
    }
}
