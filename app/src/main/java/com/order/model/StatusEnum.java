package com.order.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.annotation.Generated;
import jakarta.validation.constraints.*;
import java.util.*;
import org.hibernate.validator.constraints.*;

/** Gets or Sets statusEnum */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public enum StatusEnum {
  COMPLETED("completed"),

  IN_PROGRESS("in-progress"),

  CANCELLED("cancelled"),

  REJECTED("rejected");

  private String value;

  StatusEnum(String value) {
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
  public static StatusEnum fromValue(String value) {
    for (StatusEnum b : StatusEnum.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}
