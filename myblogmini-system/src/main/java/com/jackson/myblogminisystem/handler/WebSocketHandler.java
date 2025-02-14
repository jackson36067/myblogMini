package com.jackson.myblogminisystem.handler;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jackson.constant.RedisConstant;
import com.jackson.dao.ChatMessage;
import com.jackson.dao.User;
import com.jackson.dto.ChatMessageDTO;
import com.jackson.myblogminisystem.repository.ChatMessageRepository;
import com.jackson.myblogminisystem.repository.UserRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class WebSocketHandler extends TextWebSocketHandler {
    @Resource
    private UserRepository userRepository;
    @Resource
    private RabbitTemplate rabbitTemplate;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private ChatMessageRepository chatMessageRepository;

    private static final ConcurrentHashMap<String, WebSocketSession> onlineUsers = new ConcurrentHashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper(); // 用于解析 JSON

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        String userId = getUserIdFromSession(session);
        if (userId != null) {
            onlineUsers.put(userId, session);
            // 将用户在线状态改为在线
            userRepository.updateUserIsOnlineById(Long.valueOf(userId), true);
            log.info("用户:{} 上线", userId);
        }
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("收到消息: {}", message.getPayload());
        // 发送消息为Json格式
        ChatMessage chatMessage = JSONUtil.toBean(message.getPayload(), ChatMessage.class);
        chatMessage.setCreateTime(LocalDateTime.now());
        WebSocketSession receiverSession = onlineUsers.get(chatMessage.getReceiverId().toString());
        // 用户在线 -> 通过session发送信息给指定用户
        if (receiverSession != null && receiverSession.isOpen()) {
            ChatMessageDTO chatMessageDTO = BeanUtil.copyProperties(chatMessage, ChatMessageDTO.class);
            User receiver = userRepository.findById(chatMessage.getReceiverId()).get();
            chatMessageDTO.setAvatar(receiver.getAvatar());
            chatMessageDTO.setNickName(receiver.getNickName());
            receiverSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(chatMessageDTO)));
        } else {
            // 用户不在线 -> 将消息保存到redis
            String jsonStr = JSONUtil.toJsonStr(chatMessage);
            stringRedisTemplate.opsForZSet().add(RedisConstant.USER_CHAT_MESSAGE_PREFIX + chatMessage.getReceiverId(), jsonStr, System.currentTimeMillis());
        }
        // 保存到数据库
        chatMessageRepository.save(chatMessage);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        String userId = getUserIdFromSession(session);
        if (userId != null) {
            onlineUsers.remove(userId);
            // 将用户在线状态改为false
            userRepository.updateUserIsOnlineById(Long.valueOf(userId), false);
            log.info("用户:{} 下线", userId);
        }
    }

    private String getUserIdFromSession(WebSocketSession session) {
        return Objects.requireNonNull(session.getUri()).getQuery().split("=")[1]; // 解析 ?userId=123
    }
}
