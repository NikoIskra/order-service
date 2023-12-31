package com.order.service;

import com.order.exception.BadRequestException;
import com.order.model.AccountRoleIDReturnModel;
import com.order.model.RoleEnum;
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

  public void verifyAccountID(UUID accountID, RoleEnum role) {
    try {
      AccountRoleIDReturnModel accountRoleIDReturnModel =
          restTemplate.getForObject(
              baseUrl + "/api/v1/account/{account_id}/role/" + role.toString().toUpperCase(),
              AccountRoleIDReturnModel.class,
              accountID.toString());
    } catch (Exception e) {
      throw new BadRequestException("No " + role.toString() + " role for this account found");
    }
  }
}
