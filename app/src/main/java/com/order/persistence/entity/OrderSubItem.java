package com.order.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_sub_item")
public class OrderSubItem {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subItemSequenceGenerator")
  @SequenceGenerator(
      name = "subItemSequenceGenerator",
      sequenceName = "sub_item_id_seq",
      allocationSize = 1)
  private Long id;

  @Column(name = "provider_sub_item_id", nullable = false)
  private Long providerSubItemId;

  @Column(nullable = false)
  private Integer quantity;

  @Column(name = "price_cents", nullable = false)
  private Integer priceCents;

  @ManyToOne
  @JoinColumn(name = "order_item_id")
  private OrderItem item;

  public OrderSubItem(
      Long id, Long providerSubItemId, Integer quantity, Integer priceCents, OrderItem item) {
    this.id = id;
    this.providerSubItemId = providerSubItemId;
    this.quantity = quantity;
    this.priceCents = priceCents;
    this.item = item;
  }

  public OrderSubItem(Long id, Long providerSubItemId, Integer quantity, Integer priceCents) {
    this.id = id;
    this.providerSubItemId = providerSubItemId;
    this.quantity = quantity;
    this.priceCents = priceCents;
  }

  public OrderSubItem() {}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getProviderSubItemId() {
    return providerSubItemId;
  }

  public void setProviderSubItemId(Long providerSubItemId) {
    this.providerSubItemId = providerSubItemId;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public Integer getPriceCents() {
    return priceCents;
  }

  public void setPriceCents(Integer priceCents) {
    this.priceCents = priceCents;
  }

  public OrderItem getItem() {
    return item;
  }

  public void setItem(OrderItem item) {
    this.item = item;
  }
}
