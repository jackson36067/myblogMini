package com.jackson.dto;

import java.util.Objects;

public class UpdateGroupDTO {
    private Long id;
    private String groupName;

    public UpdateGroupDTO() {
    }

    public UpdateGroupDTO(Long id, String groupName) {
        this.id = id;
        this.groupName = groupName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateGroupDTO that = (UpdateGroupDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(groupName, that.groupName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, groupName);
    }

    @Override
    public String toString() {
        return "UpdateGroupDTO{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                '}';
    }
}
