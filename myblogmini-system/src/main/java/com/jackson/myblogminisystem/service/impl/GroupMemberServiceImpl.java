package com.jackson.myblogminisystem.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.jackson.constant.RedisConstant;
import com.jackson.context.BaseContext;
import com.jackson.dao.GroupMember;
import com.jackson.dao.User;
import com.jackson.dao.UserGroup;
import com.jackson.dao.UserNote;
import com.jackson.dto.GroupMemberDTO;
import com.jackson.myblogminisystem.repository.GroupMemberRepository;
import com.jackson.myblogminisystem.repository.UserGroupRepository;
import com.jackson.myblogminisystem.repository.UserNoteRepository;
import com.jackson.myblogminisystem.repository.UserRepository;
import com.jackson.myblogminisystem.service.GroupMemberService;
import com.jackson.result.Result;
import com.jackson.vo.GroupMemberVO;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupMemberServiceImpl implements GroupMemberService {

    @Resource
    private GroupMemberRepository groupMemberRepository;
    @Resource
    private UserGroupRepository userGroupRepository;
    @Resource
    private UserRepository userRepository;
    @Resource
    private UserNoteRepository userNoteRepository;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

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
        // 通过成员id以及用户id判断改成员是否已经被该用户分组
        Long currentId = BaseContext.getCurrentId();
        GroupMember groupMember = groupMemberRepository.findByMemberId(memberId, currentId);
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

    /**
     * 将成员添加进分组或者从分组中删除
     *
     * @param groupMemberDTO
     */
    @Override
    public void addOrDeleteMemberToGroup(GroupMemberDTO groupMemberDTO) {
        // 判断用户是否已经在分组中
        Long groupId = groupMemberDTO.getId();
        Long memberId = groupMemberDTO.getMemberId();
        GroupMember groupMember = groupMemberRepository.findByGroupIdAndMemberId(groupId, memberId);
        // 不存在
        if (groupMember == null) {
            // 将新数据添加到分组成员中
            UserGroup userGroup = userGroupRepository.findById(groupId).get();
            groupMember = new GroupMember(null, memberId, userGroup);
            groupMemberRepository.save(groupMember);
        } else {
            // 从分组中删除该成员
            groupMemberRepository.delete(groupMember);
        }
    }

    /**
     * 根据分组id获取分组成员
     *
     * @param id
     * @return
     */
    @Override
    public Result<List<GroupMemberVO>> getGroupMemberById(Long id) {
        Long currentId = BaseContext.getCurrentId();
        List<GroupMember> groupMemberList = groupMemberRepository.findAllByGroupId(id);
        List<GroupMemberVO> groupMemberVOList = groupMemberList.stream().map(groupMember -> {
            User user = userRepository.findById(groupMember.getMemberId()).get();
            GroupMemberVO groupMemberVO = BeanUtil.copyProperties(user, GroupMemberVO.class);
            Boolean isMember = stringRedisTemplate.opsForSet().isMember(RedisConstant.USER_FOLLOW_KEY_PREFIX + currentId, user.getId().toString());
            groupMemberVO.setIsFollow(isMember);
            UserNote userNote = userNoteRepository.findByUserIdAndUserNoteId(currentId, user.getId());
            if (userNote != null) {
                groupMemberVO.setComment(userNote.getNote());
            }
            return groupMemberVO;
        }).toList();
        return Result.success(groupMemberVOList);
    }

    /**
     * 从分组中移除成员
     *
     * @param groupMemberDTO
     */
    @Override
    public void removeMemberFromGroup(GroupMemberDTO groupMemberDTO) {
        groupMemberRepository.deleteByUserGroupIdAndMemberId(groupMemberDTO.getId(), groupMemberDTO.getMemberId());
    }
}