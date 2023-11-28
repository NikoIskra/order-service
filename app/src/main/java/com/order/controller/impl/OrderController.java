package com.order.controller.impl;

import com.order.controller.OrderApi;
import com.order.model.OrderGetReturnModel;
import com.order.model.OrderPostRequestModel;
import com.order.model.OrderPostReturnModel;
import com.order.service.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController implements OrderApi {

  private final OrderService orderService;

  @Override
  public ResponseEntity<OrderPostReturnModel> createOrder(
      @NotNull UUID X_ACCOUNT_ID, @Valid OrderPostRequestModel orderPostRequestModel)
      throws Exception {
    OrderPostReturnModel orderPostReturnModel =
        orderService.createOrder(X_ACCOUNT_ID, orderPostRequestModel);
    HttpHeaders returnHeaders = new HttpHeaders();
    String headerString = "/api/v1/order/" + orderPostReturnModel.getResult().getOrderNumber();
    returnHeaders.set("Location", headerString);
    return ResponseEntity.status(HttpStatus.CREATED)
        .headers(returnHeaders)
        .body(orderPostReturnModel);
  }

  @Override
  public ResponseEntity<OrderGetReturnModel> getOrder(@NotNull UUID X_ACCOUNT_ID, Long orderId)
      throws Exception {
    OrderGetReturnModel model = orderService.getOrder(X_ACCOUNT_ID, orderId);
    return ResponseEntity.status(HttpStatus.OK).body(model);
  }
}
