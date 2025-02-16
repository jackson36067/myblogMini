package com.jackson.myblogminisystem.controller;

import com.jackson.myblogminisystem.service.UserFollowService;
import com.jackson.result.Result;
import com.jackson.vo.UserFollowListVO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/follow")
public class UserFollowController {

    @Resource
    private UserFollowService userFollowService;

    /**
     * 关注用户
     *
     * @param id 被关注用户id
     */
    @PostMapping("/{id}")
    public void followUser(@PathVariable Long id) {
        userFollowService.followUser(id);
    }

    @GetMapping("/list")
    public Result<List<UserFollowListVO>> getUserFollowList() {
        return userFollowService.getUserFollowList();
    }
}
