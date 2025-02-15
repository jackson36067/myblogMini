package com.jackson.myblogminisystem.controller;

import com.jackson.dto.UpdateUserDTO;
import com.jackson.dto.UserLoginDTO;
import com.jackson.myblogminisystem.service.UserService;
import com.jackson.result.Result;
import com.jackson.vo.LoginResultVO;
import com.jackson.vo.UserDataVO;
import com.jackson.vo.UserDetailVO;
import com.jackson.vo.UserResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    /**
     * 获取本身详情信息
     * @return
     */
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

    /**
     * 根据id获取用户详情信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<UserDetailVO> getUserDetailData(@PathVariable Long id){
        return userService.getUserDetailData(id);
    }

    /**
     * 获取用户访问他人主页历史记录详情
     * @return
     */
    @GetMapping("/history")
    public Result<List<UserDataVO>> getUserHistoryBrowse(){
        return userService.getUserHistoryBrowse();
    }
}
