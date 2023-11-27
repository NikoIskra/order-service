package com.order.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.annotation.Generated;
import jakarta.validation.constraints.*;
import java.util.*;
import org.hibernate.validator.constraints.*;

/** Gets or Sets stageEnum */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public enum StageEnum {
  NEW("new"),

  PICKED("picked"),

  PAID("paid"),

  FULLFILMENT("fullfilment"),

  DELIVERY("delivery"),

  COMPLETED("completed");

  private String value;

  StageEnum(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static StageEnum fromValue(String value) {
    for (StageEnum b : StageEnum.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}
