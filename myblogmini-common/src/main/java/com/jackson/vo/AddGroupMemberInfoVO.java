package com.jackson.vo;

import java.io.Serializable;
import java.util.Objects;

public class AddGroupMemberInfoVO implements Serializable {
    private Long id;//用户id
    private String avatar;
    private String nickName;
    private Boolean isMember; // 判断用户是否为该组的成员

    public AddGroupMemberInfoVO() {
    }

    public AddGroupMemberInfoVO(Long id, String avatar, String nickName, Boolean isMember) {
        this.id = id;
        this.avatar = avatar;
        this.nickName = nickName;
        this.isMember = isMember;
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

    public Boolean getIsMember() {
        return isMember;
    }

    public void setIsMember(Boolean member) {
        isMember = member;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddGroupMemberInfoVO that = (AddGroupMemberInfoVO) o;
        return Objects.equals(id, that.id) && Objects.equals(avatar, that.avatar) && Objects.equals(nickName, that.nickName) && Objects.equals(isMember, that.isMember);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, avatar, nickName, isMember);
    }

    @Override
    public String toString() {
        return "AddGroupMemberInfoVO{" +
                "id=" + id +
                ", avatar='" + avatar + '\'' +
                ", nickName='" + nickName + '\'' +
                ", isMember=" + isMember +
                '}';
    }
}
