package com.order.service;

import org.springframework.stereotype.Component;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class SQSMessageSender {

  private final AmazonSQSAsync amazonSQS;

  private final ObjectMapper objectMapper;


  public void sendMessage(Object object) {
    try {
      amazonSQS.sendMessage(
          new SendMessageRequest()
              .withQueueUrl(
                  "http://sqs.us-east-1.localhost.localstack.cloud:4566/000000000000/order-event")
              .withMessageBody(
                  objectMapper.writeValueAsString(object)));
    } catch (JsonProcessingException e) {
      log.error(e.getCause().getMessage());
    }
  }

    
}
