package com.jackson.myblogminisystem.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.jackson.constant.RedisConstant;
import com.jackson.context.BaseContext;
import com.jackson.dao.*;
import com.jackson.myblogminisystem.repository.*;
import com.jackson.myblogminisystem.service.UserGroupService;
import com.jackson.result.Result;
import com.jackson.vo.AddGroupMemberInfoVO;
import com.jackson.vo.GroupMemberVO;
import com.jackson.vo.UserGroupVO;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UserGroupServiceImpl implements UserGroupService {

    @Resource
    private UserGroupRepository userGroupRepository;
    @Resource
    private GroupMemberRepository groupMemberRepository;
    @Resource
    private UserRepository userRepository;
    @Resource
    private UserFollowRepository userFollowRepository;
    @Resource
    private UserNoteRepository userNoteRepository;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 获取用户分组接口
     *
     * @return
     */
    @Override
    public Result<List<UserGroupVO>> getGroupList() {
        Long currentId = BaseContext.getCurrentId();
        // 通过id获取该用户创建的所有分组
        List<UserGroup> userGroupList = userGroupRepository.findAllByUserId(currentId);
        List<UserGroupVO> userGroupVOList = userGroupList.stream().map(userGroup -> {
            // 将分组转变成要的数据
            UserGroupVO userGroupVO = BeanUtil.copyProperties(userGroup, UserGroupVO.class);
            // 通过分组id获取分组成员对象
            List<GroupMember> groupMemberList = groupMemberRepository.findAllByGroupId(userGroup.getId());
            // 获取分组成员id集合
            List<Long> groupMemberIdList = groupMemberList.stream().map(GroupMember::getMemberId).toList();
            List<User> userList = userRepository.findAllById(groupMemberIdList);
            // 将分组成员转换为想要的数据
            List<GroupMemberVO> groupMemberVOList = userList.stream()
                    .map(user -> {
                        GroupMemberVO groupMemberVO = BeanUtil.copyProperties(user, GroupMemberVO.class);
                        Boolean isMember = stringRedisTemplate.opsForSet().isMember(RedisConstant.USER_FOLLOW_KEY_PREFIX + currentId, user.getId().toString());
                        groupMemberVO.setIsFollow(isMember);
                        UserNote userNote = userNoteRepository.findByUserIdAndUserNoteId(currentId, user.getId());
                        if (userNote != null) {
                            groupMemberVO.setComment(userNote.getNote());
                        }
                        return groupMemberVO;
                    })
                    .toList();
            userGroupVO.setGroupMemberVOList(groupMemberVOList);
            return userGroupVO;
        }).toList();
        return Result.success(userGroupVOList);
    }

    /**
     * 用户新增分组
     *
     * @param groupName
     */
    @Override
    public void addUserGroup(String groupName) {
        Long currentId = BaseContext.getCurrentId();
        User user = userRepository.findById(currentId).get();
        UserGroup userGroup = new UserGroup(null, groupName, user);
        userGroupRepository.save(userGroup);
    }

    /**
     * 获取添加分组成员时能添加的成员数据 (我的关注用户,并判断是否已经存在在这个组内)
     *
     * @param groupId
     * @param nickNameOrComment
     * @return
     */
    @Override
    public Result<List<AddGroupMemberInfoVO>> getAddGroupMemberInfo(Long groupId, String nickNameOrComment) {
        Long currentId = BaseContext.getCurrentId();
        List<UserFollow> userFollowList = userFollowRepository.findAllByUserId(currentId);
        // 如果有传递用户名或者备注,则过滤出用户
        if (StringUtils.hasText(nickNameOrComment)) {
            userFollowList = userFollowList.stream().filter(userFollow ->
                    {
                        UserNote userNote = userNoteRepository.findByUserIdAndUserNoteId(currentId, userFollow.getUserFollowId());
                        if (userNote == null) {
                            return userRepository.findById(userFollow.getUserFollowId()).get().getNickName().contains(nickNameOrComment);
                        } else {
                            return userRepository.findById(userFollow.getUserFollowId()).get().getNickName().contains(nickNameOrComment)
                                    ||
                                    userNote.getNote().contains(nickNameOrComment);
                        }
                    }
            ).toList();
        }
        // 封装数据返回
        List<AddGroupMemberInfoVO> addGroupMemberInfoVOList = userFollowList.stream().map(userFollow -> {
            User user = userRepository.findById(userFollow.getUserFollowId()).get();
            AddGroupMemberInfoVO addGroupMemberInfoVO = BeanUtil.copyProperties(user, AddGroupMemberInfoVO.class);
            boolean isMember = groupMemberRepository.existsByGroupIdAndMemberId(groupId, user.getId());
            addGroupMemberInfoVO.setIsMember(isMember);
            return addGroupMemberInfoVO;
        }).toList();
        return Result.success(addGroupMemberInfoVOList);
    }
}
