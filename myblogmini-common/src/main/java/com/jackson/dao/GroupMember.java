package com.jackson.dao;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "group_members")
public class GroupMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;

    @ManyToOne
    @JoinColumn(name = "group_id",referencedColumnName = "id")
    private UserGroup userGroup;

    public GroupMember() {
    }

    public GroupMember(Long id, Long memberId, UserGroup userGroup) {
        this.id = id;
        this.memberId = memberId;
        this.userGroup = userGroup;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupMember that = (GroupMember) o;
        return Objects.equals(id, that.id) && Objects.equals(memberId, that.memberId) && Objects.equals(userGroup, that.userGroup);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, memberId, userGroup);
    }

    @Override
    public String toString() {
        return "GroupMember{" +
                "id=" + id +
                ", memberId=" + memberId +
                ", userGroup=" + userGroup +
                '}';
    }
}
