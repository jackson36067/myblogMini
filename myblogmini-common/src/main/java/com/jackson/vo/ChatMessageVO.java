package com.jackson.vo;

import com.jackson.dao.ChatMessage;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class ChatMessageVO implements Serializable {

    // 接受者的id、名字以及头像
    private Long id;
    private String nickName;
    private String avatar;
    private String lastOnlineTime;
    private Boolean isOnline;
    private List<ChatMessage> chatMessages;

    public ChatMessageVO() {
    }

    public ChatMessageVO(Long id, String nickname, String avatar, String lastOnlineTime, Boolean isOnline, List<ChatMessage> chatMessages) {
        this.id = id;
        this.nickName = nickname;
        this.avatar = avatar;
        this.lastOnlineTime = lastOnlineTime;
        this.isOnline = isOnline;
        this.chatMessages = chatMessages;
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

    public String getLastOnlineTime() {
        return lastOnlineTime;
    }

    public void setLastOnlineTime(String lastOnlineTime) {
        this.lastOnlineTime = lastOnlineTime;
    }

    public List<ChatMessage> getChatMessages() {
        return chatMessages;
    }

    public void setChatMessages(List<ChatMessage> chatMessages) {
        this.chatMessages = chatMessages;
    }

    public Boolean getisOnline() {
        return isOnline;
    }

    public void setisOnline(Boolean online) {
        isOnline = online;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatMessageVO that = (ChatMessageVO) o;
        return Objects.equals(id, that.id) && Objects.equals(nickName, that.nickName) && Objects.equals(avatar, that.avatar) && Objects.equals(lastOnlineTime, that.lastOnlineTime) && Objects.equals(isOnline, that.isOnline) && Objects.equals(chatMessages, that.chatMessages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickName, avatar, lastOnlineTime, isOnline, chatMessages);
    }

    @Override
    public String toString() {
        return "ChatMessageDTO{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", avatar='" + avatar + '\'' +
                ", lastOnlineTime='" + lastOnlineTime + '\'' +
                ", isOnline=" + isOnline +
                ", chatMessages=" + chatMessages +
                '}';
    }
}
