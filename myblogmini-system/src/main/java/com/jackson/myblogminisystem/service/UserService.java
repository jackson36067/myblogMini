package com.jackson.myblogminisystem.service;

import com.jackson.dto.UpdateUserDTO;
import com.jackson.dto.UserLoginDTO;
import com.jackson.result.Result;
import com.jackson.vo.LoginResultVO;
import com.jackson.vo.UserDataVO;
import com.jackson.vo.UserDetailVO;
import com.jackson.vo.UserResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    Result<LoginResultVO> login(UserLoginDTO userLoginDTO);

    Result<UserResult> getUserInfo();
    Result<String> uploadImage(MultipartFile image);

    void updateUserInfo(UpdateUserDTO updateUserDTO);

    Result<UserDetailVO> getUserDetailData(Long id);

    Result<List<UserDataVO>> getUserHistoryBrowse();
}
