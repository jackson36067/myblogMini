package com.jackson.myblogminisystem.service.impl;

import com.jackson.constant.RedisConstant;
import com.jackson.context.BaseContext;
import com.jackson.dao.UserFollow;
import com.jackson.exception.OnFollowException;
import com.jackson.myblogminisystem.repository.UserFollowRepository;
import com.jackson.myblogminisystem.service.UserFollowService;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserFollowServiceImpl implements UserFollowService {

    @Resource
    private UserFollowRepository userFollowRepository;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 关注用户
     *
     * @param id
     */
    @Override
    public void followUser(Long id) {
        Long currentId = BaseContext.getCurrentId();
        // 判断是否已经关注
        if (Boolean.TRUE.equals(stringRedisTemplate.opsForSet().isMember(RedisConstant.USER_FOLLOW_KEY_PREFIX + currentId, id.toString()))) {
            throw new OnFollowException("你已经关注该用户");
        }
        UserFollow userFollow = new UserFollow();
        userFollow.setUserId(currentId);
        userFollow.setUserFollowId(id);
        userFollowRepository.save(userFollow);
        // 将关注记录保存到Redis中 (使用hash进行存储,key为用户id,value为被关注用户id)
        stringRedisTemplate.opsForSet().add(RedisConstant.USER_FOLLOW_KEY_PREFIX + currentId, id.toString());
    }
}
