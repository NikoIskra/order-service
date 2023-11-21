package com.order.test.persistence.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.order.persistence.entity.Order;
import com.order.persistence.repository.OrderRepository;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Propagation;

@DataJpaTest
@org.springframework.transaction.annotation.Transactional(propagation = Propagation.NOT_SUPPORTED)
@ActiveProfiles("test")
public class OrderRepositoryTest {

  @Autowired OrderRepository orderRepository;

  private static final UUID defaultUuid = UUID.fromString("ec73eca8-1e43-4c0d-b5a7-588b3c0e3c9c");

  private static Order createOrder() {
    Order order =
        new Order(
            1L,
            "12",
            defaultUuid,
            defaultUuid,
            "comment",
            14,
            "contact",
            "1st street",
            "new",
            "completed");
    return order;
  }

  @Test
  public void expectEmptyList() {
    List<Order> items = orderRepository.findAll();
    assertEquals(0, items.size());
  }

  @Test
  @DirtiesContext
  public void testInsertToDB() {
    orderRepository.save(createOrder());
    List<Order> items = orderRepository.findAll();
    assertEquals(1, items.size());
  }

  @Test
  @DirtiesContext
  public void testDataPersists() {
    Order order = createOrder();
    orderRepository.save(order);
    Order orderFromDB = orderRepository.findById(order.getId()).get();
    assertEquals(order.getOrderNumber(), orderFromDB.getOrderNumber());
    assertEquals(order.getProviderId(), orderFromDB.getProviderId());
    assertEquals(order.getClientContact(), orderFromDB.getClientContact());
    assertEquals(order.getComment(), orderFromDB.getComment());
    assertEquals(order.getDeliveryAddress(), orderFromDB.getDeliveryAddress());
    assertEquals(order.getStage(), orderFromDB.getStage());
    assertEquals(order.getStatus(), orderFromDB.getStatus());
  }

  private void insertEmptyOrder() {
    Order order = new Order();
    orderRepository.save(order);
  }

  @Test
  public void testInsertEmptyItem() {
    Exception exception =
        assertThrows(DataIntegrityViolationException.class, () -> insertEmptyOrder());
  }
}
