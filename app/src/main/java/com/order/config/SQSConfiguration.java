package com.order.config;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
@Slf4j
public class SQSConfiguration {

  @Bean
  @Profile("!test2")
  @Primary
  public AmazonSQSAsync amazonSQSAsync() {
    AmazonSQSAsyncClientBuilder builder =
        AmazonSQSAsyncClientBuilder.standard()
            .withCredentials(new DefaultAWSCredentialsProviderChain())
            .withEndpointConfiguration(getEndpointConfiguration("http://localhost:4566"));
    return builder.build();
  }

  private AwsClientBuilder.EndpointConfiguration getEndpointConfiguration(String url) {
    return new AwsClientBuilder.EndpointConfiguration(url, Regions.US_EAST_1.getName());
  }

  @Bean
  @Primary
  public QueueMessagingTemplate queueMessagingTemplate(AmazonSQSAsync amazonSqs) {
    return new QueueMessagingTemplate(amazonSqs);
  }
}
