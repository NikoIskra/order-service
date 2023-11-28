package com.order.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.*;
import java.util.Objects;
import java.util.UUID;
import org.hibernate.validator.constraints.*;

/** OrderGetReturnModelResult */
@JsonTypeName("orderGetReturnModelResult")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class OrderGetReturnModelResult {

  private Long id;

  private String orderNumber;

  private Long providerId;

  private UUID clientId;

  private String comment;

  private Integer totalPriceCents;

  private String clientContact;

  private String deliveryAddress;

  private StageEnum stage;

  private StatusEnum status;

  private OrderPostDetailsModel details;

  public OrderGetReturnModelResult id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   *
   * @return id
   */
  @JsonProperty("id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public OrderGetReturnModelResult orderNumber(String orderNumber) {
    this.orderNumber = orderNumber;
    return this;
  }

  /**
   * Get orderNumber
   *
   * @return orderNumber
   */
  @JsonProperty("orderNumber")
  public String getOrderNumber() {
    return orderNumber;
  }

  public void setOrderNumber(String orderNumber) {
    this.orderNumber = orderNumber;
  }

  public OrderGetReturnModelResult providerId(Long providerId) {
    this.providerId = providerId;
    return this;
  }

  /**
   * Get providerId
   *
   * @return providerId
   */
  @JsonProperty("providerId")
  public Long getProviderId() {
    return providerId;
  }

  public void setProviderId(Long providerId) {
    this.providerId = providerId;
  }

  public OrderGetReturnModelResult clientId(UUID clientId) {
    this.clientId = clientId;
    return this;
  }

  /**
   * Get clientId
   *
   * @return clientId
   */
  @Valid
  @JsonProperty("clientId")
  public UUID getClientId() {
    return clientId;
  }

  public void setClientId(UUID clientId) {
    this.clientId = clientId;
  }

  public OrderGetReturnModelResult comment(String comment) {
    this.comment = comment;
    return this;
  }

  /**
   * Get comment
   *
   * @return comment
   */
  @JsonProperty("comment")
  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public OrderGetReturnModelResult totalPriceCents(Integer totalPriceCents) {
    this.totalPriceCents = totalPriceCents;
    return this;
  }

  /**
   * Get totalPriceCents
   *
   * @return totalPriceCents
   */
  @JsonProperty("totalPriceCents")
  public Integer getTotalPriceCents() {
    return totalPriceCents;
  }

  public void setTotalPriceCents(Integer totalPriceCents) {
    this.totalPriceCents = totalPriceCents;
  }

  public OrderGetReturnModelResult clientContact(String clientContact) {
    this.clientContact = clientContact;
    return this;
  }

  /**
   * Get clientContact
   *
   * @return clientContact
   */
  @JsonProperty("clientContact")
  public String getClientContact() {
    return clientContact;
  }

  public void setClientContact(String clientContact) {
    this.clientContact = clientContact;
  }

  public OrderGetReturnModelResult deliveryAddress(String deliveryAddress) {
    this.deliveryAddress = deliveryAddress;
    return this;
  }

  /**
   * Get deliveryAddress
   *
   * @return deliveryAddress
   */
  @JsonProperty("deliveryAddress")
  public String getDeliveryAddress() {
    return deliveryAddress;
  }

  public void setDeliveryAddress(String deliveryAddress) {
    this.deliveryAddress = deliveryAddress;
  }

  public OrderGetReturnModelResult stage(StageEnum stage) {
    this.stage = stage;
    return this;
  }

  /**
   * Get stage
   *
   * @return stage
   */
  @Valid
  @JsonProperty("stage")
  public StageEnum getStage() {
    return stage;
  }

  public void setStage(StageEnum stage) {
    this.stage = stage;
  }

  public OrderGetReturnModelResult status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   *
   * @return status
   */
  @Valid
  @JsonProperty("status")
  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public OrderGetReturnModelResult details(OrderPostDetailsModel details) {
    this.details = details;
    return this;
  }

  /**
   * Get details
   *
   * @return details
   */
  @Valid
  @JsonProperty("details")
  public OrderPostDetailsModel getDetails() {
    return details;
  }

  public void setDetails(OrderPostDetailsModel details) {
    this.details = details;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrderGetReturnModelResult orderGetReturnModelResult = (OrderGetReturnModelResult) o;
    return Objects.equals(this.id, orderGetReturnModelResult.id)
        && Objects.equals(this.orderNumber, orderGetReturnModelResult.orderNumber)
        && Objects.equals(this.providerId, orderGetReturnModelResult.providerId)
        && Objects.equals(this.clientId, orderGetReturnModelResult.clientId)
        && Objects.equals(this.comment, orderGetReturnModelResult.comment)
        && Objects.equals(this.totalPriceCents, orderGetReturnModelResult.totalPriceCents)
        && Objects.equals(this.clientContact, orderGetReturnModelResult.clientContact)
        && Objects.equals(this.deliveryAddress, orderGetReturnModelResult.deliveryAddress)
        && Objects.equals(this.stage, orderGetReturnModelResult.stage)
        && Objects.equals(this.status, orderGetReturnModelResult.status)
        && Objects.equals(this.details, orderGetReturnModelResult.details);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id,
        orderNumber,
        providerId,
        clientId,
        comment,
        totalPriceCents,
        clientContact,
        deliveryAddress,
        stage,
        status,
        details);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrderGetReturnModelResult {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    orderNumber: ").append(toIndentedString(orderNumber)).append("\n");
    sb.append("    providerId: ").append(toIndentedString(providerId)).append("\n");
    sb.append("    clientId: ").append(toIndentedString(clientId)).append("\n");
    sb.append("    comment: ").append(toIndentedString(comment)).append("\n");
    sb.append("    totalPriceCents: ").append(toIndentedString(totalPriceCents)).append("\n");
    sb.append("    clientContact: ").append(toIndentedString(clientContact)).append("\n");
    sb.append("    deliveryAddress: ").append(toIndentedString(deliveryAddress)).append("\n");
    sb.append("    stage: ").append(toIndentedString(stage)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    details: ").append(toIndentedString(details)).append("\n");
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
