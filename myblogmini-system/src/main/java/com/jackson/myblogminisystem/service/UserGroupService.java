package com.jackson.myblogminisystem.service;

import com.jackson.result.Result;
import com.jackson.vo.AddGroupMemberInfoVO;
import com.jackson.vo.UserGroupVO;

import java.util.List;

public interface UserGroupService {
    Result<List<UserGroupVO>> getGroupList();

    void addUserGroup(String groupName);

    Result<List<AddGroupMemberInfoVO>> getAddGroupMemberInfo(Long groupId, String nickNameOrComment);
}
