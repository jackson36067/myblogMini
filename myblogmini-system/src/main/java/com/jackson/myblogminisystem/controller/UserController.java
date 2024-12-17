package com.jackson.myblogminisystem.controller;

import com.jackson.dto.UserLoginDTO;
import com.jackson.myblogminisystem.service.UserService;
import com.jackson.result.Result;
import com.jackson.vo.LoginResultVO;
import com.jackson.vo.UserResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 用户登录接口
     * @param userLoginDTO
     * @return
     */
    @PostMapping("/login")
    public Result<LoginResultVO> login(@RequestBody UserLoginDTO userLoginDTO) {
        return userService.login(userLoginDTO);
    }

    @GetMapping("/info")
    public Result<UserResult> getUserInfo() {
        return userService.getUserInfo();
    }
}
