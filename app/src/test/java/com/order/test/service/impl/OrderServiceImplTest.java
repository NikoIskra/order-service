package com.order.test.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import com.order.exception.NotFoundException;
import com.order.model.GetItemsSubItemModel;
import com.order.model.ItemGetReturnModel;
import com.order.model.ItemGetReturnModelResult;
import com.order.model.ItemStatusEnum;
import com.order.model.OrderPostDetailsModel;
import com.order.model.OrderPostRequestModel;
import com.order.model.OrderPostReturnModel;
import com.order.model.OrderPostReturnModelResult;
import com.order.model.RoleEnum;
import com.order.model.StageEnum;
import com.order.model.StatusEnum;
import com.order.persistence.entity.Order;
import com.order.persistence.entity.OrderItem;
import com.order.persistence.entity.OrderSubItem;
import com.order.persistence.repository.OrderRepository;
import com.order.service.EntityConverterService;
import com.order.service.OrderValidator;
import com.order.service.ProviderApiClient;
import com.order.service.impl.OrderServiceImpl;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {

  @Mock OrderValidator orderValidator;

  @Mock EntityConverterService entityConverterService;

  @Mock ProviderApiClient providerApiClient;

  @Mock OrderRepository orderRepository;

  @InjectMocks OrderServiceImpl orderServiceImpl;

  private static final String orderNumber = "X3-23ZEEG";

  private static final UUID accountID = UUID.fromString("45c01a73-5058-4135-84a7-01b964377ef8");

  private static OrderPostRequestModel createOrderPostRequestModel() {
    OrderPostRequestModel model = new OrderPostRequestModel(1L, "1st street 123", 2L);
    return model;
  }

  private static Order createOrder() {
    Order order =
        new Order(
            1L,
            orderNumber,
            1L,
            accountID,
            "comment",
            1500,
            "1st street 123",
            "1st street 123",
            StageEnum.NEW,
            StatusEnum.IN_PROGRESS);
    return order;
  }

  private static OrderPostReturnModel createOrderPostReturnModel() {
    OrderPostDetailsModel orderPostDetailsModel =
        new OrderPostDetailsModel().providerItemId(2L).quantity(1).priceCents(1500);
    OrderPostReturnModelResult result =
        new OrderPostReturnModelResult()
            .orderNumber(orderNumber)
            .providerId(1L)
            .clientId(accountID)
            .comment("comment")
            .totalPriceCents(1500)
            .clientContact("1st street 123")
            .deliveryAddress("1st street 123")
            .stage(StageEnum.NEW)
            .status(StatusEnum.IN_PROGRESS)
            .details(orderPostDetailsModel);
    return new OrderPostReturnModel().ok(true).result(result);
  }

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

  private static OrderSubItem createOrderSubItem() {
    OrderSubItem orderSubItem = new OrderSubItem(1L, 2L, 1, 1500);
    return orderSubItem;
  }

  private static OrderItem createOrderItem() {
    OrderItem orderItem = new OrderItem(1L, 1L, 1, 1500);
    return orderItem;
  }

  @Test
  void testCreateOrder() {
    OrderPostRequestModel orderPostRequestModel = createOrderPostRequestModel();
    OrderSubItem orderSubItem = createOrderSubItem();
    ItemGetReturnModel itemGetReturnModel = createItemGetReturnModel();
    Order order = createOrder();
    OrderItem orderItem = createOrderItem();
    OrderPostReturnModel orderPostReturnModel = createOrderPostReturnModel();
    when(providerApiClient.getItemReturnModel(
            orderPostRequestModel.getProviderId(),
            orderPostRequestModel.getOrderItemId(),
            accountID))
        .thenReturn(itemGetReturnModel);
    when(entityConverterService.convertItemGetSubItemsToOrderSubItems(
            itemGetReturnModel.getResult().getSubItems(),
            orderPostRequestModel.getOrderSubItemIds()))
        .thenReturn(List.of(orderSubItem));
    when(entityConverterService.convertPostRequestModelToOrder(orderPostRequestModel))
        .thenReturn(order);
    when(entityConverterService.convertItemGetToOrderItem(itemGetReturnModel.getResult()))
        .thenReturn(orderItem);
    when(entityConverterService.convertOrderToOrderReturnModel(order))
        .thenReturn(orderPostReturnModel);
    OrderPostReturnModel orderPostReturnModel2 =
        orderServiceImpl.createOrder(accountID, orderPostRequestModel);
    assertEquals(orderPostReturnModel.isOk(), orderPostReturnModel2.isOk());
    assertEquals(
        orderPostReturnModel.getResult().getOrderNumber(),
        orderPostReturnModel2.getResult().getOrderNumber());
    assertEquals(
        orderPostReturnModel.getResult().getClientId(),
        orderPostReturnModel2.getResult().getClientId());
    assertEquals(
        orderPostReturnModel.getResult().getClientContact(),
        orderPostReturnModel2.getResult().getClientContact());
    assertEquals(
        orderPostReturnModel.getResult().getDeliveryAddress(),
        orderPostReturnModel2.getResult().getDeliveryAddress());
    assertEquals(
        orderPostReturnModel.getResult().getProviderId(),
        orderPostReturnModel2.getResult().getProviderId());
    assertEquals(
        orderPostReturnModel.getResult().getStage(), orderPostReturnModel2.getResult().getStage());
    assertEquals(
        orderPostReturnModel.getResult().getStatus(),
        orderPostReturnModel2.getResult().getStatus());
    assertEquals(
        orderPostReturnModel.getResult().getTotalPriceCents(),
        orderPostReturnModel2.getResult().getTotalPriceCents());
    verify(orderValidator).validateOrderPost(accountID, RoleEnum.CLIENT);
    verify(providerApiClient)
        .getItemReturnModel(
            orderPostRequestModel.getProviderId(),
            orderPostRequestModel.getOrderItemId(),
            accountID);
    verify(entityConverterService)
        .convertItemGetSubItemsToOrderSubItems(
            itemGetReturnModel.getResult().getSubItems(),
            orderPostRequestModel.getOrderSubItemIds());
    verify(entityConverterService).convertPostRequestModelToOrder(orderPostRequestModel);
    verify(entityConverterService).convertItemGetToOrderItem(itemGetReturnModel.getResult());
    verify(entityConverterService).convertOrderToOrderReturnModel(order);
    verify(orderRepository).save(order);
  }

  @Test
  void testCreateOrder_badID() {
    OrderPostRequestModel orderPostRequestModel = createOrderPostRequestModel();
    when(providerApiClient.getItemReturnModel(
            orderPostRequestModel.getProviderId(),
            orderPostRequestModel.getOrderItemId(),
            accountID))
        .thenThrow(NotFoundException.class);
    assertThrows(
        NotFoundException.class,
        () -> orderServiceImpl.createOrder(accountID, orderPostRequestModel));
    verify(orderValidator).validateOrderPost(accountID, RoleEnum.CLIENT);
    verify(providerApiClient)
        .getItemReturnModel(
            orderPostRequestModel.getProviderId(),
            orderPostRequestModel.getOrderItemId(),
            accountID);
    verifyNoInteractions(entityConverterService, orderRepository);
  }
}
