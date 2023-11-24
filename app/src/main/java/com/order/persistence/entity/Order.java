package com.order.persistence.entity;

import com.order.converter.StageEnumConverter;
import com.order.converter.StatusEnumConverter;
import com.order.listener.OrderListener;
import com.order.model.StageEnum;
import com.order.model.StatusEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@EntityListeners(OrderListener.class)
@Table(name = "orders")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderSequenceGenerator")
  @SequenceGenerator(
      name = "orderSequenceGenerator",
      sequenceName = "order_id_seq",
      allocationSize = 1)
  private Long id;

  @Column(name = "order_number", nullable = false)
  private String orderNumber;

  @Column(name = "provider_id", nullable = false)
  private Long providerId;

  @Column(name = "client_id", nullable = false)
  private UUID clientId;

  private String comment;

  private Integer totalPriceCents;

  @Column(name = "client_contact", nullable = false)
  private String clientContact;

  @Column(name = "delivery_address")
  private String deliveryAddress;

  @Convert(converter = StageEnumConverter.class)
  private StageEnum stage;

  @Convert(converter = StatusEnumConverter.class)
  private StatusEnum status;

  @Column(name = "created_at", insertable = false)
  private Timestamp createdAt;

  @Column(name = "updated_at")
  @UpdateTimestamp
  private Timestamp updatedAt;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
  private List<OrderItem> orderItems;

  @OneToOne(mappedBy = "order")
  private OrderTransitionLog orderTransitionLog;

  public Order(
      Long id,
      String orderNumber,
      Long providerId,
      UUID clientId,
      String comment,
      Integer totalPriceCents,
      String clientContact,
      String deliveryAddress,
      StageEnum stage,
      StatusEnum status,
      Timestamp createdAt,
      Timestamp updatedAt,
      List<OrderItem> orderItems,
      OrderTransitionLog orderTransitionLog) {
    this.id = id;
    this.orderNumber = orderNumber;
    this.providerId = providerId;
    this.clientId = clientId;
    this.comment = comment;
    this.totalPriceCents = totalPriceCents;
    this.clientContact = clientContact;
    this.deliveryAddress = deliveryAddress;
    this.stage = stage;
    this.status = status;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.orderItems = orderItems;
    this.orderTransitionLog = orderTransitionLog;
  }

  public Order(
      Long id,
      String orderNumber,
      Long providerId,
      UUID clientId,
      String comment,
      Integer totalPriceCents,
      String clientContact,
      String deliveryAddress,
      StageEnum stage,
      StatusEnum status) {
    this.id = id;
    this.orderNumber = orderNumber;
    this.providerId = providerId;
    this.clientId = clientId;
    this.comment = comment;
    this.totalPriceCents = totalPriceCents;
    this.clientContact = clientContact;
    this.deliveryAddress = deliveryAddress;
    this.stage = stage;
    this.status = status;
  }

  public Order() {}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getOrderNumber() {
    return orderNumber;
  }

  public void setOrderNumber(String orderNumber) {
    this.orderNumber = orderNumber;
  }

  public Long getProviderId() {
    return providerId;
  }

  public void setProviderId(Long providerId) {
    this.providerId = providerId;
  }

  public UUID getClientId() {
    return clientId;
  }

  public void setClientId(UUID clientId) {
    this.clientId = clientId;
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

  public StageEnum getStage() {
    return stage;
  }

  public void setStage(StageEnum stage) {
    this.stage = stage;
  }

  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
  }

  public Timestamp getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Timestamp updatedAt) {
    this.updatedAt = updatedAt;
  }

  public List<OrderItem> getOrderItems() {
    return orderItems;
  }

  public void setOrderItems(List<OrderItem> orderItems) {
    this.orderItems = orderItems;
  }

  public OrderTransitionLog getOrderTransitionLog() {
    return orderTransitionLog;
  }

  public void setOrderTransitionLog(OrderTransitionLog orderTransitionLog) {
    this.orderTransitionLog = orderTransitionLog;
  }
}
