package com.order.service;

import com.order.model.GetItemsSubItemModel;
import com.order.model.ItemGetReturnModelResult;
import com.order.model.OrderGetReturnModel;
import com.order.model.OrderGetReturnModelResult;
import com.order.model.OrderPostDetailsModel;
import com.order.model.OrderPostRequestModel;
import com.order.model.OrderPostReturnModel;
import com.order.model.OrderPostReturnModelResult;
import com.order.model.OrderPostSubItemModel;
import com.order.persistence.entity.Order;
import com.order.persistence.entity.OrderItem;
import com.order.persistence.entity.OrderSubItem;
import com.order.persistence.entity.OrderTransitionLog;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class EntityConverterService {
  private final ModelMapper modelMapper;
  private final ModelMapper strictModelMapper;

  public EntityConverterService(
      @Qualifier("strictModelMapper") ModelMapper strictModelMapper,
      @Qualifier("modelMapper") ModelMapper modelMapper) {
    this.strictModelMapper = strictModelMapper;
    this.modelMapper = modelMapper;
  }

  public Order convertPostRequestModelToOrder(OrderPostRequestModel orderPostRequestModel) {
    return modelMapper.map(orderPostRequestModel, Order.class);
  }

  public OrderPostReturnModel convertOrderToPostReturnModel(Order order) {
    OrderPostReturnModelResult result = modelMapper.map(order, OrderPostReturnModelResult.class);
    return new OrderPostReturnModel().ok(true).result(result);
  }

  public List<OrderSubItem> convertItemGetSubItemsToOrderSubItems(
      List<GetItemsSubItemModel> itemsSubItemModels,
      List<OrderPostSubItemModel> orderPostSubItemModels) {
    List<OrderSubItem> orderSubItems = new ArrayList<>();
    Map<Long, GetItemsSubItemModel> subItemMap =
        itemsSubItemModels.stream()
            .collect(Collectors.toMap(GetItemsSubItemModel::getId, Function.identity()));
    for (OrderPostSubItemModel subItemModel : orderPostSubItemModels) {
      OrderSubItem orderSubItem =
          modelMapper.map(subItemMap.get(subItemModel.getId()), OrderSubItem.class);
      orderSubItem.setQuantity(subItemModel.getQuantity());
      orderSubItems.add(orderSubItem);
    }
    return orderSubItems;
  }

  public OrderItem convertItemGetToOrderItem(ItemGetReturnModelResult itemGetReturnModelResult) {
    return modelMapper.map(itemGetReturnModelResult, OrderItem.class);
  }

  public OrderPostReturnModel convertOrderToOrderReturnModel(Order order) {
    OrderPostReturnModelResult orderPostReturnModelResult =
        modelMapper.map(order, OrderPostReturnModelResult.class);
    OrderPostDetailsModel orderPostDetailsModel =
        modelMapper.map(order.getOrderItems().get(0), OrderPostDetailsModel.class);
    orderPostReturnModelResult.setDetails(orderPostDetailsModel);
    return new OrderPostReturnModel().ok(true).result(orderPostReturnModelResult);
  }

  public OrderGetReturnModel converOrderToOrderGetReturnModel(Order order) {
    OrderGetReturnModelResult result = modelMapper.map(order, OrderGetReturnModelResult.class);
    OrderPostDetailsModel model =
        modelMapper.map(order.getOrderItems().get(0), OrderPostDetailsModel.class);
    result.setDetails(model);
    return new OrderGetReturnModel().ok(true).result(result);
  }

  public OrderTransitionLog convertOrderGetReturnModelResultToOrderTransitionLog(
      OrderGetReturnModelResult order) {
    return modelMapper.map(order, OrderTransitionLog.class);
  }
}
