package com.vedha.rabbitmq.controller;

import com.vedha.rabbitmq.dto.UserDTO;
import com.vedha.rabbitmq.producer.RabbitMQJsonProducer;
import com.vedha.rabbitmq.producer.RabbitMQProducer;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rabbit")
@AllArgsConstructor
public class SendMessage {

    private final RabbitMQProducer rabbitMQProducer;

    private final RabbitMQJsonProducer rabbitMQJsonProducer;

    @GetMapping("/v1/sendMessage")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message) {

        rabbitMQProducer.sendMessageToQueue(message);

        return ResponseEntity.ok("Message Sent To Queue vedha-queue : " + message);
    }

    @PostMapping(value = { "/v1/sendJsonMessage" }, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = { MediaType.TEXT_PLAIN_VALUE })
    public ResponseEntity<String> sendJsonMessage(@RequestBody UserDTO userDTO) {

        rabbitMQJsonProducer.sendJsonMessage(userDTO);

        return ResponseEntity.ok("Message Sent To Queue vedha-json-queue : " + userDTO);
    }
}
