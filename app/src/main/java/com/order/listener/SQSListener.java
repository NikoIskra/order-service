package com.order.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.handler.Handler;
import com.order.handler.impl.CompletedStageHandler;
import com.order.handler.impl.DeliveryStageHandler;
import com.order.handler.impl.FullfilmentStageHandler;
import com.order.handler.impl.NewStageHandler;
import com.order.handler.impl.PaidStageHandler;
import com.order.handler.impl.PickedStageHandler;
import com.order.model.OrderGetReturnModelResult;
import com.order.model.StageEnum;
import java.util.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.config.annotation.EnableSqs;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@EnableSqs
@RequiredArgsConstructor
@Profile("!test2")
public class SQSListener {

  @Value("${sqs.queueName}")
  private String queueName;

  private final ObjectMapper objectMapper;

  private final CompletedStageHandler completedStageHandler;

  private final DeliveryStageHandler deliveryStageHandler;

  private final FullfilmentStageHandler fullfilmentStageHandler;

  private final NewStageHandler newStageHandler;

  private final PaidStageHandler paidStageHandler;

  private final PickedStageHandler pickedStageHandler;

  @SqsListener(value = "${sqs.queueName}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
  public void receiveMessage(String message) {
    Map<StageEnum, Handler> map =
        Map.of(
            StageEnum.COMPLETED,
            completedStageHandler,
            StageEnum.DELIVERY,
            deliveryStageHandler,
            StageEnum.FULLFILMENT,
            fullfilmentStageHandler,
            StageEnum.NEW,
            newStageHandler,
            StageEnum.PAID,
            paidStageHandler,
            StageEnum.PICKED,
            pickedStageHandler);
    OrderGetReturnModelResult result;
    try {
      result = objectMapper.readValue(message, OrderGetReturnModelResult.class);
      log.info(result.getStage().toString());
      map.get(result.getStage()).handle(result.getStage(), result);
    } catch (JsonMappingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (JsonProcessingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    log.info(message);
  }
}
