package com.jackson.myblogminisystem.controller;

import com.jackson.myblogminisystem.service.UserGroupService;
import com.jackson.result.Result;
import com.jackson.vo.UserGroupVO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/group")
public class UserGroupController {

    @Resource
    private UserGroupService userGroupService;

    /**
     * 获取用户分组接口
     * @return
     */
    @GetMapping("/list")
    public Result<List<UserGroupVO>> getGroupList() {
        return userGroupService.getGroupList();
    }
}
