package com.order.test.persistence.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.order.persistence.entity.OrderItem;
import com.order.persistence.repository.OrderItemRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Propagation;

@org.springframework.transaction.annotation.Transactional(propagation = Propagation.NOT_SUPPORTED)
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureTestDatabase
public class OrderItemRepositoryTest {

  @Autowired OrderItemRepository orderItemRepository;

  private static OrderItem createOrderItem() {
    OrderItem orderItem = new OrderItem(1L, 1L, 3, 15);
    return orderItem;
  }

  @Test
  public void expectEmptyList() {
    List<OrderItem> items = orderItemRepository.findAll();
    assertEquals(0, items.size());
  }

  @Test
  @DirtiesContext
  public void testInsertToDB() {
    orderItemRepository.save(createOrderItem());
    List<OrderItem> items = orderItemRepository.findAll();
    assertEquals(1, items.size());
  }

  @Test
  @DirtiesContext
  public void testDataPersists() {
    OrderItem item = createOrderItem();
    orderItemRepository.save(item);
    OrderItem itemFromDB = orderItemRepository.findById(item.getId()).get();
    assertEquals(item.getProviderItemId(), itemFromDB.getProviderItemId());
    assertEquals(item.getPriceCents(), itemFromDB.getPriceCents());
    assertEquals(item.getQuantity(), itemFromDB.getQuantity());
  }

  private void insertEmptyItem() {
    OrderItem item = new OrderItem();
    orderItemRepository.save(item);
  }

  @Test
  public void testInsertEmptyItem() {
    Exception exception =
        assertThrows(DataIntegrityViolationException.class, () -> insertEmptyItem());
  }
}
