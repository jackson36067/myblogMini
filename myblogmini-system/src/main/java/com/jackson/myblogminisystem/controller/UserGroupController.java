package com.jackson.myblogminisystem.controller;

import com.jackson.dto.AddGroupDTO;
import com.jackson.myblogminisystem.service.UserGroupService;
import com.jackson.result.Result;
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
}
