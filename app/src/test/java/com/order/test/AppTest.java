/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.order.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.order.App;
import com.order.config.SQSConfiguration;
import com.order.test.configuration.SQSTestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.aws.messaging.listener.SimpleMessageListenerContainer;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(
    classes = {App.class, SQSConfiguration.class, SQSTestConfiguration.class},
    properties = "spring.main.allow-bean-definition-overriding=true")
@ActiveProfiles("test2")
public class AppTest {

  @Autowired ApplicationContext context;

  @MockBean SimpleMessageListenerContainer simpleMessageListenerContainer;

  @Test
  void contextLoads(ApplicationContext context) {
    assertNotNull(context);
  }
}
