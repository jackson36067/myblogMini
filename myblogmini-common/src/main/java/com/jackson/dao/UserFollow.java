package com.jackson.dao;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "user_follow")
public class UserFollow implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_follow_id")
    private Long userFollowId; // 关注用户id

    public UserFollow() {
    }

    public UserFollow(Long id, Long userId, Long userFollowId) {
        this.id = id;
        this.userId = userId;
        this.userFollowId = userFollowId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserFollowId() {
        return userFollowId;
    }

    public void setUserFollowId(Long userFollowId) {
        this.userFollowId = userFollowId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserFollow that = (UserFollow) o;
        return Objects.equals(id, that.id) && Objects.equals(userId, that.userId) && Objects.equals(userFollowId, that.userFollowId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, userFollowId);
    }

    @Override
    public String toString() {
        return "UserFollow{" +
                "id=" + id +
                ", userId=" + userId +
                ", userFollowId=" + userFollowId +
                '}';
    }
}
