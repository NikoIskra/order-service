package com.order.service;

import com.order.exception.BadRequestException;
import com.order.model.ItemGetReturnModel;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ProviderApiClient {

  @Value("${provider.base.url}")
  private String baseUrl;

  private final RestTemplate restTemplate;

  private static final Logger logger = LoggerFactory.getLogger(ProviderApiClient.class);

  public ItemGetReturnModel getItemReturnModel(Long providerID, Long itemID, UUID accountID) {
    try {
      HttpHeaders headers = new HttpHeaders();
      headers.set("X-ACCOUNT-ID", accountID.toString());
      HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
      String url =
          baseUrl + "/api/v1/provider/" + providerID.toString() + "/item/" + itemID.toString();
      ResponseEntity<ItemGetReturnModel> responseEntity =
          restTemplate.exchange(url, HttpMethod.GET, requestEntity, ItemGetReturnModel.class);
      ItemGetReturnModel itemGetReturnModel = responseEntity.getBody();
      return itemGetReturnModel;
    } catch (Exception e) {
      logger.info(e.getMessage());
      logger.info(e.getLocalizedMessage());
      logger.info(e.getCause().getMessage());
      throw new BadRequestException("No item with that id and provider id found");
    }
  }
}
