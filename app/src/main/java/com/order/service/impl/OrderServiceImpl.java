package com.order.service.impl;

import com.order.model.ItemGetReturnModel;
import com.order.model.OrderPostRequestModel;
import com.order.model.OrderPostReturnModel;
import com.order.model.RoleEnum;
import com.order.model.StageEnum;
import com.order.model.StatusEnum;
import com.order.persistence.entity.Order;
import com.order.persistence.entity.OrderItem;
import com.order.persistence.entity.OrderSubItem;
import com.order.persistence.repository.OrderRepository;
import com.order.service.EntityConverterService;
import com.order.service.OrderService;
import com.order.service.OrderValidator;
import com.order.service.ProviderApiClient;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final OrderValidator orderValidator;

  private final EntityConverterService entityConverterService;

  private final ProviderApiClient providerApiClient;

  private final OrderRepository orderRepository;

  @Override
  @Transactional
  public OrderPostReturnModel createOrder(
      UUID accountID, OrderPostRequestModel orderPostRequestModel) {
    orderValidator.validateOrderPost(accountID, RoleEnum.CLIENT);
    ItemGetReturnModel itemGetReturnModel =
        providerApiClient.getItemReturnModel(
            orderPostRequestModel.getProviderId(),
            orderPostRequestModel.getOrderItemId(),
            accountID);
    orderValidator.validateRetrievedItem(
        itemGetReturnModel, orderPostRequestModel.getOrderSubItemIds());
    List<OrderSubItem> orderSubItems =
        entityConverterService.convertItemGetSubItemsToOrderSubItems(
            itemGetReturnModel.getResult().getSubItems(),
            orderPostRequestModel.getOrderSubItemIds());
    Order order = entityConverterService.convertPostRequestModelToOrder(orderPostRequestModel);
    OrderItem orderItem =
        entityConverterService.convertItemGetToOrderItem(itemGetReturnModel.getResult());
    orderItem.setQuantity(1);
    orderItem.setSubItems(orderSubItems);
    orderItem.setOrder(order);
    orderItem.setId(null);
    Integer totalPriceCents = orderItem.getPriceCents();
    for (OrderSubItem orderSubItem : orderSubItems) {
      orderSubItem.setItem(orderItem);
      orderSubItem.setId(null);
      totalPriceCents =
          totalPriceCents + (orderSubItem.getPriceCents() * orderSubItem.getQuantity());
    }
    order.setId(null);
    order.setClientId(accountID);
    order.setTotalPriceCents(totalPriceCents);
    order.setOrderItems(List.of(orderItem));
    order.setStage(StageEnum.NEW);
    order.setStatus(StatusEnum.IN_PROGRESS);
    orderRepository.save(order);
    return entityConverterService.convertOrderToOrderReturnModel(order);
  }
}
