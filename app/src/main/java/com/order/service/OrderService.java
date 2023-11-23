package com.order.service;

import com.order.model.OrderPostRequestModel;
import com.order.model.OrderPostReturnModel;
import java.util.UUID;

public interface OrderService {
  OrderPostReturnModel createOrder(UUID accountID, OrderPostRequestModel orderPostRequestModel);
}
