package com.jackson.vo;

import java.io.Serializable;
import java.util.Objects;

public class UserDetailVO implements Serializable {
    private Long id;
    private String nickName;
    private String avatar;
    private String comment;
    private Integer totalLike;
    private Integer totalFollow;
    private Integer totalFans;
    private Boolean isFollow;
    private Boolean isMutualAttention; // 是否互相关注

    public UserDetailVO() {
    }

    public UserDetailVO(Long id, String nickName, String avatar, String comment, Integer totalLike, Integer totalFollow, Integer totalFans, Boolean isFollow, Boolean isMutualAttention) {
        this.id = id;
        this.nickName = nickName;
        this.avatar = avatar;
        this.comment = comment;
        this.totalLike = totalLike;
        this.totalFollow = totalFollow;
        this.totalFans = totalFans;
        this.isFollow = isFollow;
        this.isMutualAttention = isMutualAttention;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getTotalLike() {
        return totalLike;
    }

    public void setTotalLike(Integer totalLike) {
        this.totalLike = totalLike;
    }

    public Integer getTotalFollow() {
        return totalFollow;
    }

    public void setTotalFollow(Integer totalFollow) {
        this.totalFollow = totalFollow;
    }

    public Integer getTotalFans() {
        return totalFans;
    }

    public void setTotalFans(Integer totalFans) {
        this.totalFans = totalFans;
    }

    public Boolean getIsFollow() {
        return isFollow;
    }

    public void setIsFollow(Boolean follow) {
        isFollow = follow;
    }

    public Boolean getIsMutualAttention() {
        return isMutualAttention;
    }

    public void setIsMutualAttention(Boolean mutualAttention) {
        isMutualAttention = mutualAttention;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetailVO that = (UserDetailVO) o;
        return Objects.equals(id, that.id) && Objects.equals(nickName, that.nickName) && Objects.equals(avatar, that.avatar) && Objects.equals(comment, that.comment) && Objects.equals(totalLike, that.totalLike) && Objects.equals(totalFollow, that.totalFollow) && Objects.equals(totalFans, that.totalFans) && Objects.equals(isFollow, that.isFollow) && Objects.equals(isMutualAttention, that.isMutualAttention);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickName, avatar, comment, totalLike, totalFollow, totalFans, isFollow, isMutualAttention);
    }

    @Override
    public String toString() {
        return "UserDetailVO{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", avatar='" + avatar + '\'' +
                ", comment='" + comment + '\'' +
                ", totalLike=" + totalLike +
                ", totalFollow=" + totalFollow +
                ", totalFans=" + totalFans +
                ", isFollow=" + isFollow +
                ", isMutualAttention=" + isMutualAttention +
                '}';
    }
}
