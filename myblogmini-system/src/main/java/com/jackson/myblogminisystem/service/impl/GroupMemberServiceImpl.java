package com.jackson.myblogminisystem.service.impl;

import com.jackson.dao.GroupMember;
import com.jackson.dao.UserGroup;
import com.jackson.dto.GroupMemberDTO;
import com.jackson.myblogminisystem.repository.GroupMemberRepository;
import com.jackson.myblogminisystem.repository.UserGroupRepository;
import com.jackson.myblogminisystem.service.GroupMemberService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class GroupMemberServiceImpl implements GroupMemberService {

    @Resource
    private GroupMemberRepository groupMemberRepository;
    @Resource
    private UserGroupRepository userGroupRepository;

    /**
     * 插入或者改变分促成员接口
     *
     * @param groupMemberDTO
     */
    @Override
    public void insertOrChangeMemberToGroup(GroupMemberDTO groupMemberDTO) {
        // 通过分组id以及成员id
        Long memberId = groupMemberDTO.getMemberId();
        Long groupId = groupMemberDTO.getId();
        GroupMember groupMember = groupMemberRepository.findByMemberId(memberId);
        UserGroup userGroup = userGroupRepository.findById(groupId).get();
        // 判断该成员是否已经存在与某个分组内
        if (groupMember != null) {
            // 存在,修改成员的分组
            groupMember.setUserGroup(userGroup);
            groupMemberRepository.saveAndFlush(groupMember);
        } else {
            groupMember = new GroupMember(null, memberId, userGroup);
            groupMemberRepository.save(groupMember);
        }
    }
}
