package com.order.test.persistence.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.order.persistence.entity.OrderTransitionLog;
import com.order.persistence.repository.OrderTransitionLogRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Propagation;

@DataJpaTest
@org.springframework.transaction.annotation.Transactional(propagation = Propagation.NOT_SUPPORTED)
@ActiveProfiles("test")
public class OrderTransitionLogRepositoryTest {

  @Autowired OrderTransitionLogRepository orderTransitionLogRepository;

  private static OrderTransitionLog createOrderTransitionLog() {
    OrderTransitionLog orderTransitionLog =
        new OrderTransitionLog("id", "comment", 15, "12345", "1st street", "new", "completed");
    return orderTransitionLog;
  }

  @Test
  public void expectEmptyList() {
    List<OrderTransitionLog> items = orderTransitionLogRepository.findAll();
    assertEquals(0, items.size());
  }

  @Test
  @DirtiesContext
  public void testInsertToDB() {
    orderTransitionLogRepository.save(createOrderTransitionLog());
    List<OrderTransitionLog> items = orderTransitionLogRepository.findAll();
    assertEquals(1, items.size());
  }

  @Test
  @DirtiesContext
  public void testDataPersists() {
    OrderTransitionLog log = createOrderTransitionLog();
    orderTransitionLogRepository.save(log);
    OrderTransitionLog logFromDB = orderTransitionLogRepository.findById(log.getId()).get();
    assertEquals(log.getComment(), logFromDB.getComment());
    assertEquals(log.getTotalPriceCents(), logFromDB.getTotalPriceCents());
    assertEquals(log.getClientContact(), logFromDB.getClientContact());
    assertEquals(log.getDeliveryAddress(), logFromDB.getDeliveryAddress());
    assertEquals(log.getStage(), logFromDB.getStage());
    assertEquals(log.getCompleteStatus(), logFromDB.getCompleteStatus());
  }

  private void insertEmptyOrderLog() {
    OrderTransitionLog log = new OrderTransitionLog();
    orderTransitionLogRepository.save(log);
  }

  @Test
  public void testInsertEmptyItem() {
    Exception exception = assertThrows(JpaSystemException.class, () -> insertEmptyOrderLog());
  }
}
