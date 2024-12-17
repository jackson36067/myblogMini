package com.jackson.myblogminisystem.service;

import com.jackson.result.Result;
import com.jackson.vo.UserSignInfoVO;
import com.jackson.vo.UserSignResult;

import java.util.List;

public interface UserSignService {
    Result<UserSignResult> doSign();

    Result<List<UserSignInfoVO>> getSignInfo();
}
