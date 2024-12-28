package com.jackson.myblogminisystem.service;

import com.jackson.dto.GroupMemberDTO;
import com.jackson.result.Result;
import com.jackson.vo.GroupMemberVO;

import java.util.List;

public interface GroupMemberService {
    void insertOrChangeMemberToGroup(GroupMemberDTO groupMemberDTO);

    void addOrDeleteMemberToGroup(GroupMemberDTO groupMemberDTO);

    Result<List<GroupMemberVO>> getGroupMemberById(Long id);

    void removeMemberFromGroup(GroupMemberDTO groupMemberDTO);
}
