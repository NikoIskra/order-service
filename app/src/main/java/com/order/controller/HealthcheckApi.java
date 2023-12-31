/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.6.0).
 * https://openapi-generator.tech Do not edit the class manually.
 */
package com.order.controller;

import jakarta.annotation.Generated;
import jakarta.validation.constraints.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
@Validated
public interface HealthcheckApi {

  /**
   * GET /api/v1/healthcheck Healthcheck endpoint
   *
   * @return OK (status code 200)
   */
  @RequestMapping(method = RequestMethod.GET, value = "/api/v1/healthcheck")
  ResponseEntity<Void> apiV1HealthcheckGet() throws Exception;
}
