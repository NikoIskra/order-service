package com.order.test.configuration;

import static org.mockito.Mockito.mock;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class SQSTestConfiguration {

  @MockBean SimpleMessageListenerContainer simpleMessageListenerContainer;

  @Bean
  @Primary
  public AmazonSQSAsync amazonSQSAsyncTest() {
    return mock(AmazonSQSAsync.class);
  }

  @Bean
  @Primary
  public QueueMessagingTemplate queueMessagingTemplate(AmazonSQSAsync amazonSqs) {
    return new QueueMessagingTemplate(amazonSqs);
  }
}
