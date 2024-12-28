package com.jackson.vo;

import java.io.Serializable;
import java.util.Objects;

public class GroupMemberVO implements Serializable {
    private Long id;
    private String nickName;
    private String avatar;
    private Boolean isFollow;
    private String comment;

    public GroupMemberVO() {
    }

    public GroupMemberVO(Long id, String nickName, String avatar, Boolean isFollow,String comment) {
        this.id = id;
        this.nickName = nickName;
        this.avatar = avatar;
        this.isFollow = isFollow;
        this.comment = comment;
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

    public Boolean getIsFollow() {
        return isFollow;
    }

    public void setIsFollow(Boolean follow) {
        isFollow = follow;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupMemberVO that = (GroupMemberVO) o;
        return Objects.equals(id, that.id) && Objects.equals(nickName, that.nickName) && Objects.equals(avatar, that.avatar) && Objects.equals(isFollow, that.isFollow);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickName, avatar, isFollow);
    }

    @Override
    public String toString() {
        return "GroupMemberVO{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", avatar='" + avatar + '\'' +
                ", isFollow=" + isFollow +
                '}';
    }
}
