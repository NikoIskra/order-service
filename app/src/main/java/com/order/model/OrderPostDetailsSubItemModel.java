package com.order.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.annotation.Generated;
import jakarta.validation.constraints.*;
import java.util.*;
import java.util.Objects;
import org.hibernate.validator.constraints.*;

/** OrderPostDetailsSubItemModel */
@JsonTypeName("orderPostDetailsSubItemModel")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class OrderPostDetailsSubItemModel {

  private Long providerSubItemId;

  private Integer quantity;

  private Integer priceCents;

  public OrderPostDetailsSubItemModel providerSubItemId(Long providerSubItemId) {
    this.providerSubItemId = providerSubItemId;
    return this;
  }

  /**
   * Get providerSubItemId
   *
   * @return providerSubItemId
   */
  @JsonProperty("providerSubItemId")
  public Long getProviderSubItemId() {
    return providerSubItemId;
  }

  public void setProviderSubItemId(Long providerSubItemId) {
    this.providerSubItemId = providerSubItemId;
  }

  public OrderPostDetailsSubItemModel quantity(Integer quantity) {
    this.quantity = quantity;
    return this;
  }

  /**
   * Get quantity
   *
   * @return quantity
   */
  @JsonProperty("quantity")
  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public OrderPostDetailsSubItemModel priceCents(Integer priceCents) {
    this.priceCents = priceCents;
    return this;
  }

  /**
   * Get priceCents
   *
   * @return priceCents
   */
  @JsonProperty("priceCents")
  public Integer getPriceCents() {
    return priceCents;
  }

  public void setPriceCents(Integer priceCents) {
    this.priceCents = priceCents;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrderPostDetailsSubItemModel orderPostDetailsSubItemModel = (OrderPostDetailsSubItemModel) o;
    return Objects.equals(this.providerSubItemId, orderPostDetailsSubItemModel.providerSubItemId)
        && Objects.equals(this.quantity, orderPostDetailsSubItemModel.quantity)
        && Objects.equals(this.priceCents, orderPostDetailsSubItemModel.priceCents);
  }

  @Override
  public int hashCode() {
    return Objects.hash(providerSubItemId, quantity, priceCents);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrderPostDetailsSubItemModel {\n");
    sb.append("    providerSubItemId: ").append(toIndentedString(providerSubItemId)).append("\n");
    sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
    sb.append("    priceCents: ").append(toIndentedString(priceCents)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
