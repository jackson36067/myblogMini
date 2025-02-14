package com.jackson.myblogminisystem;

import cn.hutool.json.JSONUtil;
import com.jackson.constant.RedisConstant;
import com.jackson.dao.ChatMessage;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * 添加未读消息,用于测试
 */
@SpringBootTest
public class UnReadMessageTest {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testUnReadMessage() {
        List<ChatMessage> messageList = Arrays.asList(
                new ChatMessage(null, 5L, 1L, "你只要来可以来我这住几天", false, LocalDateTime.now()),
                new ChatMessage(null, 5L, 1L, "中国春节非常热闹", false, LocalDateTime.now().plusMinutes(1)),
                new ChatMessage(null, 5L, 1L, "有时间可以来中国玩玩", false, LocalDateTime.now().plusMinutes(2)),
                new ChatMessage(null, 6L, 1L, "在吗", false, LocalDateTime.now()),
                new ChatMessage(null, 6L, 1L, "勇士今天赢了", false, LocalDateTime.now().plusMinutes(1)),
                new ChatMessage(null, 6L, 1L, "巴特勒真硬", false, LocalDateTime.now().plusMinutes(2))
        );
        messageList.forEach(chatMessage -> {
            String jsonStr = JSONUtil.toJsonStr(chatMessage);
            stringRedisTemplate.opsForZSet().add(RedisConstant.USER_CHAT_MESSAGE_PREFIX + 1, jsonStr, System.currentTimeMillis());
        });
    }
}
