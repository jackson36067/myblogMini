package com.jackson.myblogminisystem.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.jackson.constant.RabbitMQConstant;
import com.jackson.constant.RedisConstant;
import com.jackson.context.BaseContext;
import com.jackson.dao.ChatMessage;
import com.jackson.dao.User;
import com.jackson.vo.ChatMessageVO;
import com.jackson.myblogminisystem.repository.ChatMessageRepository;
import com.jackson.myblogminisystem.repository.UserRepository;
import com.jackson.myblogminisystem.service.ChatMessageService;
import com.jackson.result.Result;
import com.jackson.utils.TimeFormatterUtils;
import com.jackson.vo.UnReadMessageVO;
import jakarta.annotation.Resource;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ChatMessageServiceImpl implements ChatMessageService {

    @Resource
    private ChatMessageRepository chatMessageRepository;
    @Resource
    private UserRepository userRepository;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 获取聊天记录
     *
     * @param id
     * @return
     */
    @Override
    public Result<ChatMessageVO> getChatMessageList(Long id) throws Exception {
        try {
            // 发送信息用户id
            Long currentId = BaseContext.getCurrentId();
            // 接收信息用户id -> 参数
            // 获取双方的聊天记录
            List<ChatMessage> chatMessageList = chatMessageRepository.findAllBySenderIdAndReceiverId(currentId, id);
            // 从redis中删除未读信息
            Set<String> unReadMessageRange = stringRedisTemplate.opsForZSet().range(RedisConstant.USER_CHAT_MESSAGE_PREFIX + currentId, 0, -1);
            if (unReadMessageRange != null && !unReadMessageRange.isEmpty()) {
                List<String> list = unReadMessageRange.stream().filter(range -> {
                    ChatMessage chatMessage = JSONUtil.toBean(range, ChatMessage.class);
                    return Objects.equals(chatMessage.getSenderId(), id);
                }).toList();
                if (!list.isEmpty()) {
                    // 删除对方未读信息
                    stringRedisTemplate.opsForZSet().remove(RedisConstant.USER_CHAT_MESSAGE_PREFIX + currentId, list.toArray());
                    // 异步将信息插入到数据库中
                    List<ChatMessage> chatMessageList1 = list.stream()
                            .map(unReadMessageJson -> JSONUtil.toBean(unReadMessageJson, ChatMessage.class))
                            .toList();
                    rabbitTemplate.convertAndSend(RabbitMQConstant.CHAT_QUEUE, chatMessageList1);
                    chatMessageList.addAll(chatMessageList1);
                }
            }
            User user = userRepository.findById(id).get();
            ChatMessageVO chatMessageVO = BeanUtil.copyProperties(user, ChatMessageVO.class);
            chatMessageVO.setLastOnlineTime(TimeFormatterUtils.formatLastOnline(user.getLastOnlineTime()));
            chatMessageVO.setChatMessages(chatMessageList);
            // 异步将对方发送的消息设置为已读
            Map<String, Long> rabbitMQChatMap = new HashMap<>();
            rabbitMQChatMap.put(RabbitMQConstant.SENDER_ID, id);
            rabbitMQChatMap.put(RabbitMQConstant.RECEIVER_ID, currentId);
            rabbitTemplate.convertAndSend(RabbitMQConstant.READ_CHAT_QUEUE, rabbitMQChatMap);
            return Result.success(chatMessageVO);
        } catch (Exception e) {
            throw new Exception("获取聊天记录失败");
        }
    }

    /**
     * 获取未读消息列表
     *
     * @return
     */
    @Override
    public Result<List<UnReadMessageVO>> getUnReadMessageList() {
        Long currentId = BaseContext.getCurrentId();
        Set<String> unReadMessageJsonList = stringRedisTemplate.opsForZSet().range(RedisConstant.USER_CHAT_MESSAGE_PREFIX + currentId, 0, -1);
        List<UnReadMessageVO> unReadMessageVOList = List.of();
        if (unReadMessageJsonList != null) {
            // 获取未读信息,根据发送者id分组
            Map<Long, List<ChatMessage>> senderMessageGroup = unReadMessageJsonList.stream()
                    .map(unReadMessageJson -> JSONUtil.toBean(unReadMessageJson, ChatMessage.class))
                    // 通过发送者的id分组
                    .collect(Collectors.groupingBy(ChatMessage::getSenderId));
            // 从数据中获取我的聊天记录 -> 哪些人
            // 我作为接受者 -> 作为最终结果
            List<Long> senderIdList = chatMessageRepository.findSenderIdByReceiverId(currentId);
            // 我作为发送者
            List<Long> receiverIdList = chatMessageRepository.findReceiverIdBySenderId(currentId);
            // 合并聊天记录以及未聊天记录
            senderMessageGroup
                    .keySet()  // 获取分组后每个发送者的id
                    .forEach(senderId -> {
                        // 从数据库中获取聊天记录
                        if (!senderIdList.contains(senderId)) {
                            // 合并未读信息发送者id
                            senderIdList.add(senderId);
                        }
                        // 合并已读信息接受者id
                        senderIdList.addAll(receiverIdList);
                    });
            // id去重
            HashSet<Long> chatMessageHistoryIdSet = new HashSet<>(senderIdList);
            ArrayList<Long> chatMessageHistoryIdList = new ArrayList<>(chatMessageHistoryIdSet);
            // 合成结果返回
            unReadMessageVOList = chatMessageHistoryIdList
                    .stream()
                    .map(senderId -> {
                        User user = userRepository.findById(senderId).get();
                        UnReadMessageVO unReadMessageVO = BeanUtil.copyProperties(user, UnReadMessageVO.class);
                        unReadMessageVO.setSenderId(senderId);
                        unReadMessageVO.setLastOnlineTime(TimeFormatterUtils.formatLastOnline(user.getLastOnlineTime()));
                        List<ChatMessage> chatMessages = senderMessageGroup.get(senderId);
                        if (chatMessages != null && !chatMessages.isEmpty()) {
                            unReadMessageVO.setUnReadMessageNumber(chatMessages.size());
                        } else {
                            unReadMessageVO.setUnReadMessageNumber(0);
                        }
                        return unReadMessageVO;
                    }).toList();
        }
        return Result.success(unReadMessageVOList);
    }
}
