package com.jackson.myblogminisystem.service.impl;

import com.jackson.constant.RedisConstant;
import com.jackson.context.BaseContext;
import com.jackson.myblogminisystem.service.HistoryService;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 删除用户历史访问记录
     *
     * @param current 0. 删除访问用户历史记录 1. 删除浏览文章历史记录
     */
    @Override
    public void deleteBrowseHistory(Integer current) {
        Long currentId = BaseContext.getCurrentId();
        if (current == 0) {
            stringRedisTemplate.opsForZSet().removeRange(RedisConstant.USER_BROWSE_HISTORY_PREFIX + currentId, 0, -1);
        } else if (current == 1) {
            stringRedisTemplate.opsForZSet().removeRange(RedisConstant.ARTICLE_BROWSE_HISTORY_PREFIX + currentId, 0, -1);
        }
    }
}
