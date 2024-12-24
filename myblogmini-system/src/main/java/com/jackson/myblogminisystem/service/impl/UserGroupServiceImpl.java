package com.jackson.myblogminisystem.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.jackson.context.BaseContext;
import com.jackson.dao.GroupMember;
import com.jackson.dao.User;
import com.jackson.dao.UserGroup;
import com.jackson.myblogminisystem.repository.GroupMemberRepository;
import com.jackson.myblogminisystem.repository.UserGroupRepository;
import com.jackson.myblogminisystem.repository.UserRepository;
import com.jackson.myblogminisystem.service.UserGroupService;
import com.jackson.result.Result;
import com.jackson.vo.GroupMemberVO;
import com.jackson.vo.UserGroupVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserGroupServiceImpl implements UserGroupService {

    @Resource
    private UserGroupRepository userGroupRepository;
    @Resource
    private GroupMemberRepository groupMemberRepository;
    @Resource
    private UserRepository userRepository;

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
                    .map(user -> BeanUtil.copyProperties(user, GroupMemberVO.class))
                    .toList();
            userGroupVO.setGroupMemberVOList(groupMemberVOList);
            return userGroupVO;
        }).toList();
        return Result.success(userGroupVOList);
    }

    @Override
    public void addUserGroup(String groupName) {
        Long currentId = BaseContext.getCurrentId();
        User user = userRepository.findById(currentId).get();
        UserGroup userGroup = new UserGroup(null, groupName, user);
        userGroupRepository.save(userGroup);
    }
}
