package com.order.persistence.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "order_item")
public class OrderItem {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itemSequenceGenerator")
  @SequenceGenerator(
      name = "itemSequenceGenerator",
      sequenceName = "item_id_seq",
      allocationSize = 1)
  private Long id;

  @Column(name = "provider_item_id", nullable = false)
  private Long providerItemId;

  @Column(nullable = false)
  private Integer quantity;

  @Column(name = "price_cents", nullable = false)
  private Integer priceCents;

  @ManyToOne
  @JoinColumn(name = "order_id")
  private Order order;

  @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
  private List<OrderSubItem> subItems;

  public OrderItem(
      Long id,
      Long providerItemId,
      Integer quantity,
      Integer priceCents,
      Order order,
      List<OrderSubItem> subItems) {
    this.id = id;
    this.providerItemId = providerItemId;
    this.quantity = quantity;
    this.priceCents = priceCents;
    this.order = order;
    this.subItems = subItems;
  }

  public OrderItem(Long id, Long providerItemId, Integer quantity, Integer priceCents) {
    this.id = id;
    this.providerItemId = providerItemId;
    this.quantity = quantity;
    this.priceCents = priceCents;
  }

  public OrderItem() {}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getProviderItemId() {
    return providerItemId;
  }

  public void setProviderItemId(Long providerItemId) {
    this.providerItemId = providerItemId;
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

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

  public List<OrderSubItem> getSubItems() {
    return subItems;
  }

  public void setSubItems(List<OrderSubItem> subItems) {
    this.subItems = subItems;
  }
}
