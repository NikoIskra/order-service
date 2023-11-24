package com.order.listener;

import com.order.persistence.entity.Order;
import com.order.service.OrderNumberGenerator;
import jakarta.persistence.PrePersist;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderListener {

  private final OrderNumberGenerator orderNumberGenerator;

  @PrePersist
  public void generateOrderNumber(Order order) {
    order.setOrderNumber(orderNumberGenerator.generateOrderNumber());
  }
}
