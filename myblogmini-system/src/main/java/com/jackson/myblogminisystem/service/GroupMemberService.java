package com.jackson.myblogminisystem.service;

import com.jackson.dto.GroupMemberDTO;

public interface GroupMemberService {
    void insertOrChangeMemberToGroup(GroupMemberDTO groupMemberDTO);
}
