package com.order.persistence.repository;

import com.order.persistence.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
  Boolean existsByOrderNumber(String orderNumber);
}
