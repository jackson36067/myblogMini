package com.jackson.myblogminisystem.service;

import com.jackson.result.Result;
import com.jackson.vo.UserDataVO;

import java.util.List;

public interface UserDataService {
    Result<List<UserDataVO>> getUserDataList(Integer current, Integer sort,String nickNameOrComment);
}
