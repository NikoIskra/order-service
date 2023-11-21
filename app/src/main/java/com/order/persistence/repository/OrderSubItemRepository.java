package com.order.persistence.repository;

import com.order.persistence.entity.OrderSubItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderSubItemRepository extends JpaRepository<OrderSubItem, Long> {}
