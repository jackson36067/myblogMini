package com.jackson.myblogminisystem.controller;

import com.jackson.dto.AddGroupDTO;
import com.jackson.dto.UpdateGroupDTO;
import com.jackson.myblogminisystem.service.UserGroupService;
import com.jackson.result.Result;
import com.jackson.vo.AddGroupMemberInfoVO;
import com.jackson.vo.UserGroupVO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/group")
public class UserGroupController {

    @Resource
    private UserGroupService userGroupService;

    /**
     * 获取用户分组接口
     *
     * @return
     */
    @GetMapping("/list")
    public Result<List<UserGroupVO>> getGroupList() {
        return userGroupService.getGroupList();
    }

    /**
     * 创建用户分组
     */
    @PostMapping("/add")
    public void addUserGroup(@RequestBody AddGroupDTO addGroupDTO) {
        userGroupService.addUserGroup(addGroupDTO.getGroupName());
    }


    /**
     * 获取添加分组成员时能添加的成员数据 (我的关注用户,并判断是否已经存在在这个组内)
     *
     * @param groupId
     * @param nickNameOrComment
     * @return
     */
    @GetMapping("/add/list")
    public Result<List<AddGroupMemberInfoVO>> getAddGroupMemberInfo(Long groupId, String nickNameOrComment) {
        return userGroupService.getAddGroupMemberInfo(groupId, nickNameOrComment);
    }

    /**
     * 修改用户名称
     *
     * @param updateGroupDTO
     */
    @PutMapping("/name")
    public void updateGroupName(@RequestBody UpdateGroupDTO updateGroupDTO) {
        userGroupService.updateGroupName(updateGroupDTO);
    }

    /**
     * 删除用户分组
     * @param id
     */
    @DeleteMapping("/{id}")
    public void deleteGroup(@PathVariable Long id) {
        userGroupService.deleteGroup(id);
    }
}
