package com.order.service;

import com.order.exception.NotFoundException;
import com.order.model.GetItemsSubItemModel;
import com.order.model.ItemGetReturnModel;
import com.order.model.OrderPostSubItemModel;
import com.order.model.RoleEnum;
import com.order.persistence.repository.OrderRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderValidator {

  private final AccountApiClient accountApiClient;

  private final OrderRepository orderRepository;

  public void validateOrderPost(UUID accountID, RoleEnum role) {
    accountApiClient.verifyAccountID(accountID, role);
  }

  public void validateOrderGet(UUID accountID, Long orderID) {
    accountApiClient.verifyAccountID(accountID, RoleEnum.MANAGER);
    if (!orderRepository.existsById(orderID)) {
      throw new NotFoundException("no order with that ID found!");
    }
  }

  public void validateRetrievedItem(
      ItemGetReturnModel itemGetReturnModel, List<OrderPostSubItemModel> subItems) {
    List<GetItemsSubItemModel> itemGetSubItems = itemGetReturnModel.getResult().getSubItems();
    List<Long> postIds =
        subItems.stream().map(OrderPostSubItemModel::getId).collect(Collectors.toList());
    List<Long> ids =
        itemGetSubItems.stream().map(GetItemsSubItemModel::getId).collect(Collectors.toList());
    if (!ids.containsAll(postIds)) {
      throw new NotFoundException("sub items missing");
    }
  }
}
