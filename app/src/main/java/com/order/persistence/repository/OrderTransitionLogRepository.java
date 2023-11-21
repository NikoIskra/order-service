package com.order.persistence.repository;

import com.order.persistence.entity.OrderTransitionLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderTransitionLogRepository extends JpaRepository<OrderTransitionLog, String> {}
