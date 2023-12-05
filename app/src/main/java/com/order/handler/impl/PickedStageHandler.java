package com.order.handler.impl;

import com.order.handler.Handler;
import com.order.model.OrderGetReturnModelResult;
import com.order.model.StageEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PickedStageHandler implements Handler {

  @Override
  public void handle(StageEnum stageEnum, OrderGetReturnModelResult orderGetReturnModelResult) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'handle' for picked stage");
  }
}
