package com.order.test.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.order.exception.NotFoundException;
import com.order.model.GetItemsSubItemModel;
import com.order.model.ItemGetReturnModel;
import com.order.model.ItemGetReturnModelResult;
import com.order.model.ItemStatusEnum;
import com.order.model.OrderPostSubItemModel;
import com.order.model.RoleEnum;
import com.order.persistence.repository.OrderRepository;
import com.order.service.AccountApiClient;
import com.order.service.OrderValidator;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OrderValidatorTest {

  @Mock AccountApiClient accountApiClient;

  @Mock OrderRepository orderRepository;

  @InjectMocks OrderValidator orderValidator;

  private static final UUID accountID = UUID.fromString("45c01a73-5058-4135-84a7-01b964377ef8");

  private static ItemGetReturnModel createItemGetReturnModel() {
    GetItemsSubItemModel subItemModel =
        new GetItemsSubItemModel()
            .id(1L)
            .title("title")
            .description("description")
            .priceCents(1500)
            .status(ItemStatusEnum.ACTIVE);
    ItemGetReturnModelResult itemGetReturnModel =
        new ItemGetReturnModelResult()
            .id(2L)
            .title("title")
            .description("description")
            .priceCents(1500)
            .status(ItemStatusEnum.ACTIVE)
            .subItems(List.of(subItemModel));
    return new ItemGetReturnModel().ok(true).result(itemGetReturnModel);
  }

  private static OrderPostSubItemModel createOrderPostSubItemModel() {
    OrderPostSubItemModel model = new OrderPostSubItemModel(1L);
    return model;
  }

  @Test
  void testValidateOrder() {
    assertDoesNotThrow(() -> orderValidator.validateOrderPost(accountID, RoleEnum.CLIENT));
  }

  @Test
  public void testValidateRetrievedItem() {
    ItemGetReturnModel itemGetReturnModel = createItemGetReturnModel();
    OrderPostSubItemModel orderPostSubItemModel = createOrderPostSubItemModel();
    assertDoesNotThrow(
        () ->
            orderValidator.validateRetrievedItem(
                itemGetReturnModel, List.of(orderPostSubItemModel)));
  }

  @Test
  public void testValidateRetrievedItem_notFound() {
    ItemGetReturnModel itemGetReturnModel = createItemGetReturnModel();
    OrderPostSubItemModel orderPostSubItemModel = createOrderPostSubItemModel();
    orderPostSubItemModel.setId(2L);
    assertThrows(
        NotFoundException.class,
        () ->
            orderValidator.validateRetrievedItem(
                itemGetReturnModel, List.of(orderPostSubItemModel)));
  }

  @Test
  public void testValidateOrderGet() {
    when(orderRepository.existsById(anyLong())).thenReturn(true);
    assertDoesNotThrow(() -> orderValidator.validateOrderGet(accountID, 1L));
  }

  @Test
  public void testValidateOrderGet_orderDoesNotExist() {
    when(orderRepository.existsById(anyLong())).thenReturn(false);
    assertThrows(NotFoundException.class, () -> orderValidator.validateOrderGet(accountID, 1L));
  }
}
