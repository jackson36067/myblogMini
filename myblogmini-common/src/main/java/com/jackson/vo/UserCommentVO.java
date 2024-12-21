package com.jackson.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class UserCommentVO implements Serializable {
    private Long id;
    private String nickName;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime createTime;
    private Long userId;
    private String content;
    private String avatar;
    private Integer totalLike;
    private Boolean isLike;
    private Boolean isFollow;

    public UserCommentVO() {
    }

    public UserCommentVO(Long id, String nickName, LocalDateTime createTime, Long userId, String content, String avatar, Integer totalLike, Boolean isLike, Boolean isFollow) {
        this.id = id;
        this.nickName = nickName;
        this.createTime = createTime;
        this.userId = userId;
        this.content = content;
        this.avatar = avatar;
        this.totalLike = totalLike;
        this.isLike = isLike;
        this.isFollow = isFollow;
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

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Boolean getIsLike() {
        return isLike;
    }

    public void setIsLike(Boolean like) {
        isLike = like;
    }

    public Boolean getIsFollow() {
        return isFollow;
    }

    public void setIsFollow(Boolean follow) {
        isFollow = follow;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
        UserCommentVO that = (UserCommentVO) o;
        return Objects.equals(id, that.id) && Objects.equals(nickName, that.nickName) && Objects.equals(createTime, that.createTime) && Objects.equals(userId, that.userId) && Objects.equals(content, that.content) && Objects.equals(avatar, that.avatar) && Objects.equals(totalLike, that.totalLike) && Objects.equals(isLike, that.isLike) && Objects.equals(isFollow, that.isFollow);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickName, createTime, userId, content, avatar, totalLike, isLike, isFollow);
    }

    @Override
    public String toString() {
        return "UserCommentVO{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", createTime=" + createTime +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                ", avatar='" + avatar + '\'' +
                ", totalLike=" + totalLike +
                ", isLike=" + isLike +
                ", isFollow=" + isFollow +
                '}';
    }
}
