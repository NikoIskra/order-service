package com.order.service;

import com.order.exception.BadRequestException;
import com.order.model.AccountRoleIDReturnModel;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class AccountApiClient {

  @Value("${account.base.url}")
  private String baseUrl;

  private final RestTemplate restTemplate;

  public void verifyAccountIDProvider(UUID accountID) {
    try {
      AccountRoleIDReturnModel accountRoleIDReturnModel =
          restTemplate.getForObject(
              baseUrl + "/api/v1/account/{account_id}/role/PROVIDER",
              AccountRoleIDReturnModel.class,
              accountID.toString());
    } catch (Exception e) {
      throw new BadRequestException("No provider role for this account found");
    }
  }

  public void verifyAccountIDClient(UUID accountID) {
    try {
      AccountRoleIDReturnModel accountRoleIDReturnModel =
          restTemplate.getForObject(
              baseUrl + "/api/v1/account/{account_id}/role/CLIENT",
              AccountRoleIDReturnModel.class,
              accountID.toString());
    } catch (Exception e) {
      throw new BadRequestException("No client role for this account found");
    }
  }
}
