package com.jackson.vo;

import java.io.Serializable;
import java.util.Objects;

public class UnReadMessageVO implements Serializable {
    private Long senderId;
    private String avatar;
    private String nickName;
    private String lastOnlineTime;
    private Integer unReadMessageNumber;

    public UnReadMessageVO() {
    }

    public UnReadMessageVO(Long receiverId, String avatar, String nickName, String lastOnlineTime,Integer unReadMessageNumber) {
        this.senderId = receiverId;
        this.avatar = avatar;
        this.nickName = nickName;
        this.lastOnlineTime = lastOnlineTime;
        this.unReadMessageNumber = unReadMessageNumber;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
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

    public String getLastOnlineTime() {
        return lastOnlineTime;
    }

    public void setLastOnlineTime(String lastOnlineTime) {
        this.lastOnlineTime = lastOnlineTime;
    }

    public Integer getUnReadMessageNumber() {
        return unReadMessageNumber;
    }

    public void setUnReadMessageNumber(Integer unReadMessageNumber) {
        this.unReadMessageNumber = unReadMessageNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnReadMessageVO that = (UnReadMessageVO) o;
        return Objects.equals(senderId, that.senderId) && Objects.equals(avatar, that.avatar) && Objects.equals(nickName, that.nickName) && Objects.equals(lastOnlineTime, that.lastOnlineTime) && Objects.equals(unReadMessageNumber, that.unReadMessageNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(senderId, avatar, nickName, lastOnlineTime, unReadMessageNumber);
    }

    @Override
    public String toString() {
        return "UnReadMessageVO{" +
                "receiverId=" + senderId +
                ", avatar='" + avatar + '\'' +
                ", nickName='" + nickName + '\'' +
                ", lastOnlineTime='" + lastOnlineTime + '\'' +
                ", unReadMessageNumber=" + unReadMessageNumber +
                '}';
    }
}
