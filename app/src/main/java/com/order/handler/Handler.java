package com.order.handler;

import com.order.model.OrderGetReturnModelResult;
import com.order.model.StageEnum;

public interface Handler {
  void handle(StageEnum stageEnum, OrderGetReturnModelResult orderGetReturnModelResult);
}
