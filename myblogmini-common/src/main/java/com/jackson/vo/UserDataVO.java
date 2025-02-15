package com.jackson.vo;

import java.util.Objects;

public class UserDataVO {
        private Long id;
        private String avatar;
        private String nickName;
        private String comment; // 备注
        private Boolean isFollow;

    public UserDataVO() {
        }

    public UserDataVO(Long id, String avatar, String nickName, String comment,Boolean isFollow) {
        this.id = id;
        this.avatar = avatar;
        this.nickName = nickName;
        this.comment = comment;
        this.isFollow = isFollow;
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

    public Boolean getIsFollow() {
        return isFollow;
    }

    public void setIsFollow(Boolean follow) {
        isFollow = follow;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDataVO that = (UserDataVO) o;
        return Objects.equals(id, that.id) && Objects.equals(avatar, that.avatar) && Objects.equals(nickName, that.nickName) && Objects.equals(comment, that.comment) && Objects.equals(isFollow, that.isFollow);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, avatar, nickName, comment, isFollow);
    }

    @Override
    public String toString() {
        return "UserDataVO{" +
                "id=" + id +
                ", avatar='" + avatar + '\'' +
                ", nickName='" + nickName + '\'' +
                ", comment='" + comment + '\'' +
                ", isFollow=" + isFollow +
                '}';
    }
}
