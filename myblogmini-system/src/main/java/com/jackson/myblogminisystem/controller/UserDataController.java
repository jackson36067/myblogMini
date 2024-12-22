package com.jackson.myblogminisystem.controller;

import com.jackson.myblogminisystem.service.UserDataService;
import com.jackson.result.Result;
import com.jackson.vo.UserDataVO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/data")
public class UserDataController {
    @Resource
    private UserDataService userDataService;
    @GetMapping("/list")

    public Result<List<UserDataVO>> getUserDataList(Integer current, @RequestParam(required = false) Integer sort, @RequestParam(required = false) String nickNameOrComment) {
        return userDataService.getUserDataList(current, sort, nickNameOrComment);
    }
}
