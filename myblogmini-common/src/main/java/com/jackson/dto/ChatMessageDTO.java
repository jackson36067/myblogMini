package com.jackson.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class ChatMessageDTO implements Serializable {
    // 消息id
    private Long id;
    private Long senderId;
    private Long receiverId;
    private String content;
    // 发送者的头像以及昵称
    private String avatar;
    private String nickName;
    private Boolean status;
    private LocalDateTime createTime;

    public ChatMessageDTO() {
    }

    public ChatMessageDTO(Long id, Long senderId, Long receiverId, String content, String avatar, String nickName, Boolean status, LocalDateTime createTime) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
        this.avatar = avatar;
        this.nickName = nickName;
        this.status = status;
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ChatMessageDTO(Long id) {
        this.id = id;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatMessageDTO that = (ChatMessageDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(senderId, that.senderId) && Objects.equals(receiverId, that.receiverId) && Objects.equals(content, that.content) && Objects.equals(avatar, that.avatar) && Objects.equals(nickName, that.nickName) && Objects.equals(status, that.status) && Objects.equals(createTime, that.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, senderId, receiverId, content, avatar, nickName, status, createTime);
    }

    @Override
    public String toString() {
        return "ChatMessageDTO{" +
                "id=" + id +
                ", senderId=" + senderId +
                ", receiverId=" + receiverId +
                ", content='" + content + '\'' +
                ", avatar='" + avatar + '\'' +
                ", nickName='" + nickName + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                '}';
    }
}
