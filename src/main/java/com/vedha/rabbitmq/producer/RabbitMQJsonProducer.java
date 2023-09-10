package com.vedha.rabbitmq.producer;

import com.vedha.rabbitmq.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RabbitMQJsonProducer {

    // @RequiredArgsConstructor will only create object with final variable
    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbit.mq.queue.exchange-name}")
    private String queueExchange;

    @Value("${rabbit.mq.queue.json.routing-key-name}")
    private String jsonQueueRoutingKey;

    public void sendJsonMessage(UserDTO userDTO) {

        log.error("Sending Json User DTO to Queue vedha-json-queue : " + userDTO);

        rabbitTemplate.convertAndSend(queueExchange, jsonQueueRoutingKey, userDTO);
    }
}
