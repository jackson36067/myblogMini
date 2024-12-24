package com.jackson.myblogminisystem.service;

import com.jackson.result.Result;
import com.jackson.vo.UserGroupVO;

import java.util.List;

public interface UserGroupService {
    Result<List<UserGroupVO>> getGroupList();

    void addUserGroup(String groupName);
}
