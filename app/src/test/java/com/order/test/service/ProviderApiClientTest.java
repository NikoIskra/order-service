package com.order.test.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;

import com.order.model.GetItemsSubItemModel;
import com.order.model.ItemGetReturnModel;
import com.order.model.ItemGetReturnModelResult;
import com.order.model.ItemStatusEnum;
import com.order.service.ProviderApiClient;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
public class ProviderApiClientTest {
  @Mock RestTemplate restTemplate;

  @InjectMocks ProviderApiClient providerApiClient;

  @Value("${provider.base.url}")
  private String baseUrl;

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

  @Test
  void testGetItemReturnModel() {
    HttpHeaders headers = new HttpHeaders();
    headers.set("X-ACCOUNT-ID", accountID.toString());
    HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
    String url = baseUrl + "/api/v1/provider/1000/item/1";
    ItemGetReturnModel itemGetReturnModel = createItemGetReturnModel();
    ResponseEntity<ItemGetReturnModel> response =
        new ResponseEntity<ItemGetReturnModel>(itemGetReturnModel, HttpStatus.OK);
    when(restTemplate.exchange(url, HttpMethod.GET, requestEntity, ItemGetReturnModel.class))
        .thenReturn(response);
    assertDoesNotThrow(() -> providerApiClient.getItemReturnModel(1000L, 1L, accountID));
  }
}
