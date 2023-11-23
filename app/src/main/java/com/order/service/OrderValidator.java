package com.order.service;

import com.order.exception.NotFoundException;
import com.order.model.GetItemsSubItemModel;
import com.order.model.ItemGetReturnModel;
import com.order.model.OrderPostSubItemModel;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderValidator {

  private final AccountApiClient accountApiClient;

  public void validateOrderPost(UUID accountID) {
    accountApiClient.verifyAccountIDClient(accountID);
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
