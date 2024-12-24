package com.jackson.dto;

import java.io.Serializable;
import java.util.Objects;

public class AddGroupDTO implements Serializable {
    private String groupName;

    public AddGroupDTO() {
    }

    public AddGroupDTO(String groupName) {
        this.groupName = groupName;
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
        AddGroupDTO that = (AddGroupDTO) o;
        return Objects.equals(groupName, that.groupName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(groupName);
    }

    @Override
    public String toString() {
        return "AddGroupDTO{" +
                "groupName='" + groupName + '\'' +
                '}';
    }
}
