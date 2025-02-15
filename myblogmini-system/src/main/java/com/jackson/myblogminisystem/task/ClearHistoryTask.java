package com.jackson.myblogminisystem.task;

import com.jackson.constant.RedisConstant;
import com.jackson.context.BaseContext;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class ClearHistoryTask {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Scheduled(cron = "0 0 0 * * ?")
    public void doClearHistory() {
        Long currentId = BaseContext.getCurrentId();
        long threeDaysAgo = Instant.now().minus(3, TimeUnit.DAYS.toChronoUnit()).getEpochSecond();
        // 历史浏览记录清除 - 3天前
        stringRedisTemplate.opsForZSet().removeRangeByScore(RedisConstant.USER_BROWSE_HISTORY_PREFIX + currentId, 0, threeDaysAgo);
        stringRedisTemplate.opsForZSet().removeRangeByScore(RedisConstant.ARTICLE_BROWSE_HISTORY_PREFIX + currentId, 0, threeDaysAgo);
    }
}
