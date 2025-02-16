package com.jackson.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class UserFollowListVO implements Serializable {
    private Long id; // 关注者id
    private String avatar;
    private String nickName;
    private String comment;
    private Boolean isMutualAttention; // 是否互相关注
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createTime;

    public UserFollowListVO() {
    }

    public UserFollowListVO(Long id, String avatar, String nickName, String comment, Boolean isMutualAttention, LocalDate createTime) {
        this.id = id;
        this.avatar = avatar;
        this.nickName = nickName;
        this.comment = comment;
        this.isMutualAttention = isMutualAttention;
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getMutualAttention() {
        return isMutualAttention;
    }

    public void setMutualAttention(Boolean mutualAttention) {
        isMutualAttention = mutualAttention;
    }

    public LocalDate getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDate createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserFollowListVO that = (UserFollowListVO) o;
        return Objects.equals(id, that.id) && Objects.equals(avatar, that.avatar) && Objects.equals(nickName, that.nickName) && Objects.equals(comment, that.comment) && Objects.equals(isMutualAttention, that.isMutualAttention) && Objects.equals(createTime, that.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, avatar, nickName, comment, isMutualAttention, createTime);
    }

    @Override
    public String toString() {
        return "UserFollowListVO{" +
                "id=" + id +
                ", avatar='" + avatar + '\'' +
                ", nickName='" + nickName + '\'' +
                ", comment='" + comment + '\'' +
                ", isMutualAttention=" + isMutualAttention +
                ", createTime=" + createTime +
                '}';
    }
}
