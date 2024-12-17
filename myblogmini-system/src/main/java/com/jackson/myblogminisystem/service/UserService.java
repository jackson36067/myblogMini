package com.jackson.myblogminisystem.service;

import com.jackson.dto.UserLoginDTO;
import com.jackson.result.Result;
import com.jackson.vo.LoginResultVO;
import com.jackson.vo.UserResult;

public interface UserService {
    Result<LoginResultVO> login(UserLoginDTO userLoginDTO);

    Result<UserResult> getUserInfo();
}
