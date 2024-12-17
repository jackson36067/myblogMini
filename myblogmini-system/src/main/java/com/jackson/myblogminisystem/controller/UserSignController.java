package com.jackson.myblogminisystem.controller;

import com.jackson.myblogminisystem.service.UserSignService;
import com.jackson.result.Result;
import com.jackson.vo.UserSignInfoVO;
import com.jackson.vo.UserSignResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sign")
public class UserSignController {

    @Resource
    private UserSignService userSignService;

    /**
     * 用户签到
     * @return
     */
    @PostMapping
    public Result<UserSignResult> doSign() {
        return userSignService.doSign();
    }

    /**
     * 获取当前用户签到详情
     * @return
     */
    @GetMapping("/list")
    public Result<List<UserSignInfoVO>> getSignInfo() {
        return userSignService.getSignInfo();
    }
}
