package com.jackson.myblogminisystem.service;

import com.jackson.result.Result;
import com.jackson.vo.UserFollowListVO;

import java.util.List;

public interface UserFollowService {
    void followUser(Long id);

    Result<List<UserFollowListVO>> getUserFollowList();
}
