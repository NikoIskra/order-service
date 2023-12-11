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
@Profile("!test2")
public class SQSConfiguration {

  private String region = "us-east-1";

  @Bean(name = "amazonSqs")
  @Primary
  public AmazonSQSAsync amazonSQSAsync() {
    AmazonSQSAsyncClientBuilder builder =
        AmazonSQSAsyncClientBuilder.standard()
            .withCredentials(new DefaultAWSCredentialsProviderChain())
            .withEndpointConfiguration(getEndpointConfiguration("http://localhost:4566"))
            .withRegion(region);
    return builder.build();
  }

  private AwsClientBuilder.EndpointConfiguration getEndpointConfiguration(String url) {
    return new AwsClientBuilder.EndpointConfiguration(url, Regions.US_EAST_1.getName());
  }

  @Bean
  public QueueMessagingTemplate queueMessagingTemplate(AmazonSQSAsync amazonSqs) {
    return new QueueMessagingTemplate(amazonSqs);
  }
}
