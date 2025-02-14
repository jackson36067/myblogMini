package com.jackson.dao;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "openid")
    private String openid;

    @Column(name = "name")
    private String name;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "sex")
    private String sex;

    @Column(name = "id_number")
    private String idNumber;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "points")
    private Integer points;

    @Column(name = "create_time")
    @CreatedDate
    private LocalDateTime createTime;

    @Column(name = "is_sign_in")
    private Boolean isSignIn;

    @Column(name = "is_online")
    private Boolean isOnline;

    @Column(name = "last_online_time")
    private LocalDateTime lastOnlineTime;

    public User() {
    }

    public User(Long id, String openid, String name, String nickName, String phone, String sex, String idNumber, String avatar, Integer points, LocalDateTime createTime, Boolean isSignIn, boolean isOnline, LocalDateTime lastOnlineTime) {
        this.id = id;
        this.openid = openid;
        this.name = name;
        this.nickName = nickName;
        this.phone = phone;
        this.sex = sex;
        this.idNumber = idNumber;
        this.avatar = avatar;
        this.points = points;
        this.createTime = createTime;
        this.isSignIn = isSignIn;
        this.isOnline = isOnline;
        this.lastOnlineTime = lastOnlineTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Boolean getisSignIn() {
        return isSignIn;
    }

    public void setisSignIn(Boolean isSignIn) {
        this.isSignIn = isSignIn;
    }

    public Boolean getisOnline() {
        return isOnline;
    }

    public void setisOnline(Boolean isOnline) {
        this.isOnline = isOnline;
    }

    public LocalDateTime getLastOnlineTime() {
        return lastOnlineTime;
    }

    public void setLastOnlineTime(LocalDateTime lastOnlineTime) {
        this.lastOnlineTime = lastOnlineTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(openid, user.openid) && Objects.equals(name, user.name) && Objects.equals(nickName, user.nickName) && Objects.equals(phone, user.phone) && Objects.equals(sex, user.sex) && Objects.equals(idNumber, user.idNumber) && Objects.equals(avatar, user.avatar) && Objects.equals(points, user.points) && Objects.equals(createTime, user.createTime) && Objects.equals(isSignIn, user.isSignIn) && Objects.equals(isOnline, user.isOnline) && Objects.equals(lastOnlineTime, user.lastOnlineTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, openid, name, nickName, phone, sex, idNumber, avatar, points, createTime, isSignIn, isOnline, lastOnlineTime);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", openid='" + openid + '\'' +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                ", phone='" + phone + '\'' +
                ", sex='" + sex + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", avatar='" + avatar + '\'' +
                ", points=" + points +
                ", createTime=" + createTime +
                ", isSignIn=" + isSignIn +
                ", isOnline=" + isOnline +
                ", lastOnlineTime=" + lastOnlineTime +
                '}';
    }
}
