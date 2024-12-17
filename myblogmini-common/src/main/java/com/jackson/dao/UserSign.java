package com.jackson.dao;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "user_sign")
public class UserSign implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;
    @Column(name = "sign_date")
    private LocalDate signDate;

    public UserSign() {
    }

    public UserSign(Long id, User user, LocalDate signDate) {
        this.id = id;
        this.user = user;
        this.signDate = signDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getSignDate() {
        return signDate;
    }

    public void setSignDate(LocalDate signDate) {
        this.signDate = signDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSign userSign = (UserSign) o;
        return Objects.equals(id, userSign.id) && Objects.equals(user, userSign.user) && Objects.equals(signDate, userSign.signDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, signDate);
    }

    @Override
    public String toString() {
        return "UserSign{" +
                "id=" + id +
                ", user=" + user +
                ", signDate=" + signDate +
                '}';
    }
}