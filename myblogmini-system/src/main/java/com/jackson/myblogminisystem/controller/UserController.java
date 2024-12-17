package com.jackson.myblogminisystem.controller;

import com.jackson.dto.UpdateUserDTO;
import com.jackson.dto.UserLoginDTO;
import com.jackson.myblogminisystem.service.UserService;
import com.jackson.result.Result;
import com.jackson.vo.LoginResultVO;
import com.jackson.vo.UserResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 用户登录接口
     *
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

    /**
     * 上传头像至alioss
     *
     * @param image
     * @return
     */
    @PostMapping("/upload")
    public Result<String> uploadImage(MultipartFile image) {
        return userService.uploadImage(image);
    }

    /**
     * 修改用户信息接口
     *
     * @param updateUserDTO
     */
    @PutMapping("/update")
    public void updateUserInfo(@RequestBody UpdateUserDTO updateUserDTO) {
        userService.updateUserInfo(updateUserDTO);
    }
}
