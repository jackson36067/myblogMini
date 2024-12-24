package com.jackson.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class UserGroupVO implements Serializable {
    private Long id; // 分组id
    private String groupName;
    private List<GroupMemberVO> groupMemberVOList;

    public UserGroupVO() {
    }

    public UserGroupVO(Long id, String groupName, List<GroupMemberVO> groupMemberVOList) {
        this.id = id;
        this.groupName = groupName;
        this.groupMemberVOList = groupMemberVOList;
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

    public List<GroupMemberVO> getGroupMemberVOList() {
        return groupMemberVOList;
    }

    public void setGroupMemberVOList(List<GroupMemberVO> groupMemberVOList) {
        this.groupMemberVOList = groupMemberVOList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserGroupVO that = (UserGroupVO) o;
        return Objects.equals(id, that.id) && Objects.equals(groupName, that.groupName) && Objects.equals(groupMemberVOList, that.groupMemberVOList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, groupName, groupMemberVOList);
    }

    @Override
    public String toString() {
        return "UserGroupVO{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                ", groupMemberVOList=" + groupMemberVOList +
                '}';
    }
}
