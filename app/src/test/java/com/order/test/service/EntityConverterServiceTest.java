package com.order.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.order.config.Configuration;
import com.order.model.GetItemsSubItemModel;
import com.order.model.ItemGetReturnModelResult;
import com.order.model.ItemStatusEnum;
import com.order.model.OrderGetReturnModel;
import com.order.model.OrderPostRequestModel;
import com.order.model.OrderPostReturnModel;
import com.order.model.OrderPostSubItemModel;
import com.order.model.StageEnum;
import com.order.model.StatusEnum;
import com.order.persistence.entity.Order;
import com.order.persistence.entity.OrderItem;
import com.order.persistence.entity.OrderSubItem;
import com.order.service.EntityConverterService;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class EntityConverterServiceTest {

  private final EntityConverterService entityConverterService =
      new EntityConverterService(
          new Configuration().strictModelMapper(), new Configuration().modelMapper());

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
    OrderItem orderItem = new OrderItem(1L, 2L, 1, 1500);
    order.setOrderItems(List.of(orderItem));
    return order;
  }

  private static GetItemsSubItemModel createGetItemsSubItemModel() {
    GetItemsSubItemModel subItemModel =
        new GetItemsSubItemModel()
            .id(1L)
            .title("title")
            .description("description")
            .priceCents(1500)
            .status(ItemStatusEnum.ACTIVE);
    return subItemModel;
  }

  private static OrderPostSubItemModel createOrderPostSubItemModel() {
    OrderPostSubItemModel model = new OrderPostSubItemModel(1L);
    return model;
  }

  private static ItemGetReturnModelResult createItemGetReturnModelResult() {
    ItemGetReturnModelResult itemGetReturnModel =
        new ItemGetReturnModelResult()
            .id(2L)
            .title("title")
            .description("description")
            .priceCents(1500)
            .status(ItemStatusEnum.ACTIVE)
            .subItems(List.of(createGetItemsSubItemModel()));
    return itemGetReturnModel;
  }

  @Test
  void testConvertPostRequestModelToOrder() {
    OrderPostRequestModel orderPostRequestModel = createOrderPostRequestModel();
    Order order = entityConverterService.convertPostRequestModelToOrder(orderPostRequestModel);
    assertEquals(orderPostRequestModel.getProviderId(), order.getProviderId());
    assertEquals(orderPostRequestModel.getClientContact(), order.getClientContact());
  }

  @Test
  void testConvertOrderToPostReturnmodel() {
    Order order = createOrder();
    OrderPostReturnModel returnModel = entityConverterService.convertOrderToPostReturnModel(order);
    assertEquals(order.getOrderNumber(), returnModel.getResult().getOrderNumber());
    assertEquals(order.getProviderId(), returnModel.getResult().getProviderId());
    assertEquals(order.getClientId(), returnModel.getResult().getClientId());
    assertEquals(order.getComment(), returnModel.getResult().getComment());
    assertEquals(order.getTotalPriceCents(), returnModel.getResult().getTotalPriceCents());
    assertEquals(order.getClientContact(), returnModel.getResult().getClientContact());
    assertEquals(order.getDeliveryAddress(), returnModel.getResult().getDeliveryAddress());
    assertEquals(order.getStage(), returnModel.getResult().getStage());
    assertEquals(order.getStatus(), returnModel.getResult().getStatus());
  }

  @Test
  void testConvertItemGetSubItemsToOrderSubItems() {
    GetItemsSubItemModel getItemsSubItemModel = createGetItemsSubItemModel();
    OrderPostSubItemModel orderPostSubItemModel = createOrderPostSubItemModel();
    List<OrderSubItem> orderSubItems =
        entityConverterService.convertItemGetSubItemsToOrderSubItems(
            List.of(getItemsSubItemModel), List.of(orderPostSubItemModel));
    OrderSubItem orderSubItem = orderSubItems.get(0);
    assertEquals(orderSubItem.getPriceCents(), getItemsSubItemModel.getPriceCents());
    assertEquals(orderSubItem.getProviderSubItemId(), getItemsSubItemModel.getId());
    assertEquals(orderSubItem.getQuantity(), orderPostSubItemModel.getQuantity());
  }

  @Test
  void testConvertItemGetToOrderItem() {
    ItemGetReturnModelResult itemGetReturnModelResult = createItemGetReturnModelResult();
    OrderItem orderItem =
        entityConverterService.convertItemGetToOrderItem(itemGetReturnModelResult);
    assertEquals(itemGetReturnModelResult.getId(), orderItem.getProviderItemId());
    assertEquals(itemGetReturnModelResult.getPriceCents(), orderItem.getPriceCents());
  }

  @Test
  void testConvertOrderToOrderReturnModel() {
    Order order = createOrder();
    OrderPostReturnModel returnModel = entityConverterService.convertOrderToPostReturnModel(order);
    assertEquals(order.getOrderNumber(), returnModel.getResult().getOrderNumber());
    assertEquals(order.getProviderId(), returnModel.getResult().getProviderId());
    assertEquals(order.getClientId(), returnModel.getResult().getClientId());
    assertEquals(order.getComment(), returnModel.getResult().getComment());
    assertEquals(order.getTotalPriceCents(), returnModel.getResult().getTotalPriceCents());
    assertEquals(order.getClientContact(), returnModel.getResult().getClientContact());
    assertEquals(order.getDeliveryAddress(), returnModel.getResult().getDeliveryAddress());
    assertEquals(order.getStage(), returnModel.getResult().getStage());
    assertEquals(order.getStatus(), returnModel.getResult().getStatus());
  }

  @Test
  void testConvertOrderToOrderGetReturnModel() {
    Order order = createOrder();
    OrderGetReturnModel returnModel =
        entityConverterService.converOrderToOrderGetReturnModel(order);
    assertEquals(order.getId(), returnModel.getResult().getId());
    assertEquals(order.getOrderNumber(), returnModel.getResult().getOrderNumber());
    assertEquals(order.getProviderId(), returnModel.getResult().getProviderId());
    assertEquals(order.getClientId(), returnModel.getResult().getClientId());
    assertEquals(order.getComment(), returnModel.getResult().getComment());
    assertEquals(order.getTotalPriceCents(), returnModel.getResult().getTotalPriceCents());
    assertEquals(order.getClientContact(), returnModel.getResult().getClientContact());
    assertEquals(order.getDeliveryAddress(), returnModel.getResult().getDeliveryAddress());
    assertEquals(order.getStage(), returnModel.getResult().getStage());
    assertEquals(order.getStatus(), returnModel.getResult().getStatus());
  }
}
