package com.jackson.myblogminisystem.controller;

import com.jackson.myblogminisystem.service.UserFollowService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/follow")
public class UserFollowController {

    @Resource
    private UserFollowService userFollowService;

    /**
     * 关注用户
     * @param id 被关注用户id
     */
    @PostMapping("/{id}")
    public void followUser(@PathVariable Long id) {
        userFollowService.followUser(id);
    }
}
