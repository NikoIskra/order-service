package com.order.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "order_transition_log")
public class OrderTransitionLog {

  @Id private String id;

  private String comment;

  @Column(name = "total_price_cents", nullable = false)
  private Integer totalPriceCents;

  @Column(name = "client_contact", nullable = false)
  private String clientContact;

  @Column(name = "delivery_address")
  private String deliveryAddress;

  @Column(nullable = false)
  private String stage;

  @Column(name = "complete_status", nullable = false)
  private String completeStatus;

  @Column(name = "created_at", insertable = false)
  private Timestamp createdAt;

  @OneToOne
  @JoinColumn(name = "order_id", referencedColumnName = "id")
  private Order order;

  public OrderTransitionLog(
      String id,
      String comment,
      Integer totalPriceCents,
      String clientContact,
      String deliveryAddress,
      String stage,
      String completeStatus,
      Timestamp createdAt,
      Order order) {
    this.id = id;
    this.comment = comment;
    this.totalPriceCents = totalPriceCents;
    this.clientContact = clientContact;
    this.deliveryAddress = deliveryAddress;
    this.stage = stage;
    this.completeStatus = completeStatus;
    this.createdAt = createdAt;
    this.order = order;
  }

  public OrderTransitionLog() {}

  public OrderTransitionLog(
      String id,
      String comment,
      Integer totalPriceCents,
      String clientContact,
      String deliveryAddress,
      String stage,
      String completeStatus) {
    this.id = id;
    this.comment = comment;
    this.totalPriceCents = totalPriceCents;
    this.clientContact = clientContact;
    this.deliveryAddress = deliveryAddress;
    this.stage = stage;
    this.completeStatus = completeStatus;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public Integer getTotalPriceCents() {
    return totalPriceCents;
  }

  public void setTotalPriceCents(Integer totalPriceCents) {
    this.totalPriceCents = totalPriceCents;
  }

  public String getClientContact() {
    return clientContact;
  }

  public void setClientContact(String clientContact) {
    this.clientContact = clientContact;
  }

  public String getDeliveryAddress() {
    return deliveryAddress;
  }

  public void setDeliveryAddress(String deliveryAddress) {
    this.deliveryAddress = deliveryAddress;
  }

  public String getStage() {
    return stage;
  }

  public void setStage(String stage) {
    this.stage = stage;
  }

  public String getCompleteStatus() {
    return completeStatus;
  }

  public void setCompleteStatus(String completeStatus) {
    this.completeStatus = completeStatus;
  }

  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
  }

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }
}
