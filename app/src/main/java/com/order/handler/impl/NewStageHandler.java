package com.order.handler.impl;

import com.order.handler.Handler;
import com.order.model.OrderGetReturnModelResult;
import com.order.model.StageEnum;
import com.order.persistence.entity.OrderTransitionLog;
import com.order.persistence.repository.OrderTransitionLogRepository;
import com.order.service.EntityConverterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewStageHandler implements Handler {

  private final EntityConverterService entityConverterService;

  private final OrderTransitionLogRepository orderTransitionLogRepository;

  @Override
  public void handle(StageEnum stageEnum, OrderGetReturnModelResult orderGetReturnModelResult) {
    OrderTransitionLog orderTransitionLog =
        entityConverterService.convertOrderGetReturnModelResultToOrderTransitionLog(
            orderGetReturnModelResult);
    orderTransitionLog.setId(
        orderGetReturnModelResult.getOrderNumber() + "_" + stageEnum.toString());
    orderTransitionLogRepository.save(orderTransitionLog);
  }
}
