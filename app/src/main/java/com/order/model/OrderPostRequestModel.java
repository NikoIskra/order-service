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

/** OrderPostRequestModel */
@JsonTypeName("orderPostRequestModel")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class OrderPostRequestModel {

  private Long providerId;

  private String comment;

  private String clientContact;

  private String deliveryAddress;

  private Long orderItemId;

  @Valid private List<@Valid OrderPostSubItemModel> orderSubItemIds;

  /**
   * Default constructor
   *
   * @deprecated Use {@link OrderPostRequestModel#OrderPostRequestModel(Long, String, Long)}
   */
  @Deprecated
  public OrderPostRequestModel() {
    super();
  }

  /** Constructor with only required parameters */
  public OrderPostRequestModel(Long providerId, String clientContact, Long orderItemId) {
    this.providerId = providerId;
    this.clientContact = clientContact;
    this.orderItemId = orderItemId;
  }

  public OrderPostRequestModel providerId(Long providerId) {
    this.providerId = providerId;
    return this;
  }

  /**
   * Get providerId
   *
   * @return providerId
   */
  @NotNull
  @JsonProperty("providerId")
  public Long getProviderId() {
    return providerId;
  }

  public void setProviderId(Long providerId) {
    this.providerId = providerId;
  }

  public OrderPostRequestModel comment(String comment) {
    this.comment = comment;
    return this;
  }

  /**
   * Get comment
   *
   * @return comment
   */
  @Size(max = 512)
  @JsonProperty("comment")
  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public OrderPostRequestModel clientContact(String clientContact) {
    this.clientContact = clientContact;
    return this;
  }

  /**
   * Get clientContact
   *
   * @return clientContact
   */
  @NotNull
  @Size(max = 512)
  @JsonProperty("clientContact")
  public String getClientContact() {
    return clientContact;
  }

  public void setClientContact(String clientContact) {
    this.clientContact = clientContact;
  }

  public OrderPostRequestModel deliveryAddress(String deliveryAddress) {
    this.deliveryAddress = deliveryAddress;
    return this;
  }

  /**
   * Get deliveryAddress
   *
   * @return deliveryAddress
   */
  @Size(max = 512)
  @JsonProperty("deliveryAddress")
  public String getDeliveryAddress() {
    return deliveryAddress;
  }

  public void setDeliveryAddress(String deliveryAddress) {
    this.deliveryAddress = deliveryAddress;
  }

  public OrderPostRequestModel orderItemId(Long orderItemId) {
    this.orderItemId = orderItemId;
    return this;
  }

  /**
   * Get orderItemId
   *
   * @return orderItemId
   */
  @NotNull
  @JsonProperty("orderItemId")
  public Long getOrderItemId() {
    return orderItemId;
  }

  public void setOrderItemId(Long orderItemId) {
    this.orderItemId = orderItemId;
  }

  public OrderPostRequestModel orderSubItemIds(List<@Valid OrderPostSubItemModel> orderSubItemIds) {
    this.orderSubItemIds = orderSubItemIds;
    return this;
  }

  public OrderPostRequestModel addOrderSubItemIdsItem(OrderPostSubItemModel orderSubItemIdsItem) {
    if (this.orderSubItemIds == null) {
      this.orderSubItemIds = new ArrayList<>();
    }
    this.orderSubItemIds.add(orderSubItemIdsItem);
    return this;
  }

  /**
   * Get orderSubItemIds
   *
   * @return orderSubItemIds
   */
  @Valid
  @JsonProperty("orderSubItemIds")
  public List<@Valid OrderPostSubItemModel> getOrderSubItemIds() {
    return orderSubItemIds;
  }

  public void setOrderSubItemIds(List<@Valid OrderPostSubItemModel> orderSubItemIds) {
    this.orderSubItemIds = orderSubItemIds;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrderPostRequestModel orderPostRequestModel = (OrderPostRequestModel) o;
    return Objects.equals(this.providerId, orderPostRequestModel.providerId)
        && Objects.equals(this.comment, orderPostRequestModel.comment)
        && Objects.equals(this.clientContact, orderPostRequestModel.clientContact)
        && Objects.equals(this.deliveryAddress, orderPostRequestModel.deliveryAddress)
        && Objects.equals(this.orderItemId, orderPostRequestModel.orderItemId)
        && Objects.equals(this.orderSubItemIds, orderPostRequestModel.orderSubItemIds);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        providerId, comment, clientContact, deliveryAddress, orderItemId, orderSubItemIds);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrderPostRequestModel {\n");
    sb.append("    providerId: ").append(toIndentedString(providerId)).append("\n");
    sb.append("    comment: ").append(toIndentedString(comment)).append("\n");
    sb.append("    clientContact: ").append(toIndentedString(clientContact)).append("\n");
    sb.append("    deliveryAddress: ").append(toIndentedString(deliveryAddress)).append("\n");
    sb.append("    orderItemId: ").append(toIndentedString(orderItemId)).append("\n");
    sb.append("    orderSubItemIds: ").append(toIndentedString(orderSubItemIds)).append("\n");
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
