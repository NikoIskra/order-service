package com.order.service;

import com.order.exception.BadRequestException;
import com.order.model.AccountRoleIDReturnModel;
import com.order.model.RoleEnum;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
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
      log.error(e.getMessage());
      log.error(e.getCause().getMessage());
      throw new BadRequestException("No " + role.toString() + " role for this account found");
    }
  }
}
