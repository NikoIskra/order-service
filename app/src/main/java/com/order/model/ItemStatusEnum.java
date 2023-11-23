package com.order.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ItemStatusEnum {
  ACTIVE("active"),

  SUSPENDED("suspended"),

  VIEW_ONLY("view-only"),

  CANCELLED("cancelled");

  private String value;

  ItemStatusEnum(String value) {
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
  public static ItemStatusEnum fromValue(String value) {
    for (ItemStatusEnum b : ItemStatusEnum.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}
