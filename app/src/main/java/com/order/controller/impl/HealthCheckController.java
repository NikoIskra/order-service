package com.order.controller.impl;

import com.order.controller.HealthcheckApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController implements HealthcheckApi {
  @Override
  public ResponseEntity<Void> apiV1HealthcheckGet() throws Exception {
    return ResponseEntity.ok().build();
  }
}
