package com.jackson.myblogminisystem;

import cn.hutool.json.JSONUtil;
import com.jackson.constant.RedisConstant;
import com.jackson.dto.ChatMessageDTO;
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
        List<ChatMessageDTO> messageList = Arrays.asList(
                new ChatMessageDTO(null, 5L, 1L, "你只要来可以来我这住几天", "http://jackson1.oss-cn-beijing.aliyuncs.com/a61be1b7-f7b3-4027-8e18-930fab3bdeef.jpg", "jack", false, LocalDateTime.now()),
                new ChatMessageDTO(null, 5L, 1L, "中国春节非常热闹", "http://jackson1.oss-cn-beijing.aliyuncs.com/a61be1b7-f7b3-4027-8e18-930fab3bdeef.jpg", "jack", false, LocalDateTime.now().plusMinutes(1)),
                new ChatMessageDTO(null, 5L, 1L, "有时间可以来中国玩玩", "http://jackson1.oss-cn-beijing.aliyuncs.com/a61be1b7-f7b3-4027-8e18-930fab3bdeef.jpg", "jack", false, LocalDateTime.now().plusMinutes(2)),
                new ChatMessageDTO(null, 6L, 1L, "在吗", "http://jackson1.oss-cn-beijing.aliyuncs.com/1a3d49a6-2c17-41a9-95da-5eeeacd810b6.jpg", "curry", false, LocalDateTime.now()),
                new ChatMessageDTO(null, 6L, 1L, "勇士今天赢了", "http://jackson1.oss-cn-beijing.aliyuncs.com/1a3d49a6-2c17-41a9-95da-5eeeacd810b6.jpg", "curry", false, LocalDateTime.now().plusMinutes(1)),
                new ChatMessageDTO(null, 6L, 1L, "巴特勒真硬", "http://jackson1.oss-cn-beijing.aliyuncs.com/1a3d49a6-2c17-41a9-95da-5eeeacd810b6.jpg", "curry", false, LocalDateTime.now().plusMinutes(2))
        );
        messageList.forEach(chatMessage -> {
            String jsonStr = JSONUtil.toJsonStr(chatMessage);
            stringRedisTemplate.opsForZSet().add(RedisConstant.USER_CHAT_MESSAGE_PREFIX + 1, jsonStr, System.currentTimeMillis());
        });
        stringRedisTemplate.opsForZSet().add(RedisConstant.USER_FOLLOW_MESSAGE_PREFIX + 1, "8", System.currentTimeMillis());
    }
}
