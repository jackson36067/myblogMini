package com.jackson.myblogminisystem.listener;

import com.jackson.constant.RabbitMQConstant;
import com.jackson.dao.ChatMessage;
import com.jackson.myblogminisystem.repository.ChatMessageRepository;
import jakarta.annotation.Resource;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class SpringRabbitListener {

    @Resource
    private ChatMessageRepository chatMessageRepository;

    @RabbitListener(queues = RabbitMQConstant.CHAT_QUEUE)
    public void listenChatMessage(List<ChatMessage> chatMessages) {
        // 将消息保存到数据库中
        chatMessageRepository.saveAll(chatMessages);
    }

    @RabbitListener(queues = RabbitMQConstant.READ_CHAT_QUEUE)
    public void listenReadChatMessage(Map<String, Long> map) {
        // 修改信息为已读
        List<Long> chatMessageIdList = chatMessageRepository.findAllBySenderIdAndReceiverIdAndStatus(
                map.get(RabbitMQConstant.SENDER_ID), map.get(RabbitMQConstant.RECEIVER_ID), false
        ).stream().map(ChatMessage::getId).toList();
        chatMessageRepository.updateStatusByIds(true,chatMessageIdList);
    }
}
