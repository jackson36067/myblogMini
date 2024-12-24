package com.jackson.dao;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "user_group")
public class UserGroup implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String groupName;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

    public UserGroup() {
    }

    public UserGroup(Long id, String groupName, User user) {
        this.id = id;
        this.groupName = groupName;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserGroup userGroup = (UserGroup) o;
        return Objects.equals(id, userGroup.id) && Objects.equals(groupName, userGroup.groupName) && Objects.equals(user, userGroup.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, groupName, user);
    }

    @Override
    public String toString() {
        return "UserGroup{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                ", user=" + user +
                '}';
    }
}
