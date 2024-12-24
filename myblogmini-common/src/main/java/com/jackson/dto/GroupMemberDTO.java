package com.jackson.dto;

import java.io.Serializable;
import java.util.Objects;

public class GroupMemberDTO implements Serializable {
    private Long id; // 分组id
    private Long memberId;

    public GroupMemberDTO() {
    }

    public GroupMemberDTO(Long id, Long memberId) {
        this.id = id;
        this.memberId = memberId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupMemberDTO that = (GroupMemberDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(memberId, that.memberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, memberId);
    }

    @Override
    public String toString() {
        return "GroupMemberDTO{" +
                "id=" + id +
                ", memberId=" + memberId +
                '}';
    }
}
