package com.order.test.persistence.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.order.persistence.entity.OrderSubItem;
import com.order.persistence.repository.OrderSubItemRepository;
import java.util.List;
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
public class OrderSubItemRepositoryTest {

  @Autowired OrderSubItemRepository orderSubItemRepository;

  private static OrderSubItem createOrderSubItem() {
    OrderSubItem orderSubItem = new OrderSubItem(1L, 2L, 3, 5);
    return orderSubItem;
  }

  @Test
  public void expectEmptyList() {
    List<OrderSubItem> items = orderSubItemRepository.findAll();
    assertEquals(0, items.size());
  }

  @Test
  @DirtiesContext
  public void testInsertToDB() {
    orderSubItemRepository.save(createOrderSubItem());
    List<OrderSubItem> items = orderSubItemRepository.findAll();
    assertEquals(1, items.size());
  }

  @Test
  @DirtiesContext
  public void testDataPersists() {
    OrderSubItem subItem = createOrderSubItem();
    orderSubItemRepository.save(subItem);
    OrderSubItem subItemFromDB = orderSubItemRepository.findById(subItem.getId()).get();
    assertEquals(subItem.getProviderSubItemId(), subItemFromDB.getProviderSubItemId());
    assertEquals(subItem.getQuantity(), subItemFromDB.getQuantity());
    assertEquals(subItem.getPriceCents(), subItemFromDB.getPriceCents());
  }

  private void insertEmptyOrderSubItem() {
    OrderSubItem order = new OrderSubItem();
    orderSubItemRepository.save(order);
  }

  @Test
  public void testInsertEmptyItem() {
    Exception exception =
        assertThrows(DataIntegrityViolationException.class, () -> insertEmptyOrderSubItem());
  }
}
