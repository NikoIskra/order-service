package com.order.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.annotation.Generated;
import jakarta.validation.constraints.*;
import java.util.*;
import java.util.Objects;
import org.hibernate.validator.constraints.*;

/** OrderPostSubItemModel */
@JsonTypeName("orderPostSubItemModel")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class OrderPostSubItemModel {

  private Long id;

  private Integer quantity;

  /**
   * Default constructor
   *
   * @deprecated Use {@link OrderPostSubItemModel#OrderPostSubItemModel(Long)}
   */
  @Deprecated
  public OrderPostSubItemModel() {
    super();
  }

  /** Constructor with only required parameters */
  public OrderPostSubItemModel(Long id) {
    this.id = id;
  }

  public OrderPostSubItemModel id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   *
   * @return id
   */
  @NotNull
  @JsonProperty("id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public OrderPostSubItemModel quantity(Integer quantity) {
    this.quantity = quantity;
    return this;
  }

  /**
   * Get quantity minimum: 1
   *
   * @return quantity
   */
  @Min(1)
  @JsonProperty("quantity")
  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrderPostSubItemModel orderPostSubItemModel = (OrderPostSubItemModel) o;
    return Objects.equals(this.id, orderPostSubItemModel.id)
        && Objects.equals(this.quantity, orderPostSubItemModel.quantity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, quantity);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrderPostSubItemModel {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
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
