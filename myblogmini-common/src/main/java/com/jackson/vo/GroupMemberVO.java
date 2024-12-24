package com.jackson.vo;

import java.io.Serializable;
import java.util.Objects;

public class GroupMemberVO implements Serializable {
    private Long id;
    private String nickName;
    private String avatar;

    public GroupMemberVO() {
    }

    public GroupMemberVO(Long id, String nickName, String avatar) {
        this.id = id;
        this.nickName = nickName;
        this.avatar = avatar;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupMemberVO that = (GroupMemberVO) o;
        return Objects.equals(id, that.id) && Objects.equals(nickName, that.nickName) && Objects.equals(avatar, that.avatar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickName, avatar);
    }

    @Override
    public String toString() {
        return "GroupMemberVO{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
