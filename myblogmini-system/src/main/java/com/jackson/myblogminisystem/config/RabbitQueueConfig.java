package com.jackson.myblogminisystem.config;

import com.jackson.constant.RabbitMQConstant;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitQueueConfig {

    /**
     * 声明queue
     * @return
     */
    @Bean
    public Queue genChatQueue() {
        return QueueBuilder.durable(RabbitMQConstant.CHAT_QUEUE).build();
    }

    @Bean
    public Queue genReadChatQueue() {
        return QueueBuilder.durable(RabbitMQConstant.READ_CHAT_QUEUE).build();
    }
}
