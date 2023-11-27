package com.order.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.hibernate.validator.constraints.*;

/** OrderPostDetailsModel */
@JsonTypeName("orderPostDetailsModel")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class OrderPostDetailsModel {

  private Long providerItemId;

  private Integer quantity;

  private Integer priceCents;

  @Valid private List<@Valid OrderPostDetailsSubItemModel> subItems;

  public OrderPostDetailsModel providerItemId(Long providerItemId) {
    this.providerItemId = providerItemId;
    return this;
  }

  /**
   * Get providerItemId
   *
   * @return providerItemId
   */
  @JsonProperty("providerItemId")
  public Long getProviderItemId() {
    return providerItemId;
  }

  public void setProviderItemId(Long providerItemId) {
    this.providerItemId = providerItemId;
  }

  public OrderPostDetailsModel quantity(Integer quantity) {
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

  public OrderPostDetailsModel priceCents(Integer priceCents) {
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

  public OrderPostDetailsModel subItems(List<@Valid OrderPostDetailsSubItemModel> subItems) {
    this.subItems = subItems;
    return this;
  }

  public OrderPostDetailsModel addSubItemsItem(OrderPostDetailsSubItemModel subItemsItem) {
    if (this.subItems == null) {
      this.subItems = new ArrayList<>();
    }
    this.subItems.add(subItemsItem);
    return this;
  }

  /**
   * Get subItems
   *
   * @return subItems
   */
  @Valid
  @JsonProperty("subItems")
  public List<@Valid OrderPostDetailsSubItemModel> getSubItems() {
    return subItems;
  }

  public void setSubItems(List<@Valid OrderPostDetailsSubItemModel> subItems) {
    this.subItems = subItems;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrderPostDetailsModel orderPostDetailsModel = (OrderPostDetailsModel) o;
    return Objects.equals(this.providerItemId, orderPostDetailsModel.providerItemId)
        && Objects.equals(this.quantity, orderPostDetailsModel.quantity)
        && Objects.equals(this.priceCents, orderPostDetailsModel.priceCents)
        && Objects.equals(this.subItems, orderPostDetailsModel.subItems);
  }

  @Override
  public int hashCode() {
    return Objects.hash(providerItemId, quantity, priceCents, subItems);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrderPostDetailsModel {\n");
    sb.append("    providerItemId: ").append(toIndentedString(providerItemId)).append("\n");
    sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
    sb.append("    priceCents: ").append(toIndentedString(priceCents)).append("\n");
    sb.append("    subItems: ").append(toIndentedString(subItems)).append("\n");
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
