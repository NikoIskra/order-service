package com.order.config;

import com.order.model.GetItemsSubItemModel;
import com.order.model.ItemGetReturnModelResult;
import com.order.model.OrderGetReturnModelResult;
import com.order.persistence.entity.OrderItem;
import com.order.persistence.entity.OrderSubItem;
import com.order.persistence.entity.OrderTransitionLog;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@org.springframework.context.annotation.Configuration
public class Configuration {

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Bean(name = "modelMapper")
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setSkipNullEnabled(true);
    modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
    TypeMap<GetItemsSubItemModel, OrderSubItem> getItemsSubItemToOrderSubItemTypeMap =
        modelMapper.createTypeMap(GetItemsSubItemModel.class, OrderSubItem.class);
    getItemsSubItemToOrderSubItemTypeMap.addMapping(
        GetItemsSubItemModel::getId, OrderSubItem::setProviderSubItemId);
    TypeMap<ItemGetReturnModelResult, OrderItem> itemGetToOrderItemTypeMap =
        modelMapper.createTypeMap(ItemGetReturnModelResult.class, OrderItem.class);
    itemGetToOrderItemTypeMap.addMapping(
        ItemGetReturnModelResult::getId, OrderItem::setProviderItemId);
    TypeMap<OrderGetReturnModelResult, OrderTransitionLog> orderGetResultToOrderLogTypeMap =
        modelMapper.createTypeMap(OrderGetReturnModelResult.class, OrderTransitionLog.class);
    orderGetResultToOrderLogTypeMap.addMapping(
        OrderGetReturnModelResult::getStatus, OrderTransitionLog::setCompleteStatus);
    return modelMapper;
  }

  @Bean(name = "strictModelMapper")
  public ModelMapper strictModelMapper() {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setSkipNullEnabled(false);
    return modelMapper;
  }
}
