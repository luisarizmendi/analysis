package org.larizmen.analysis.domain;

import java.util.Optional;
import java.util.StringJoiner;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class OrderUpdate {

  public final String orderId;
  public final String itemId;
  public final String name;
  public final String item;
  public final String status;
  public final String madeBy;

  public OrderUpdate(final String orderId, final String itemId, final String name, final String item, final String status) {
    this.orderId = orderId;
    this.itemId = itemId;
    this.name = name;
    this.item = item;
    this.status = status;
    this.madeBy = null;
  }

  public OrderUpdate(final String orderId, final String itemId, final String name, final String item, final String status, final String madeBy) {
    this.orderId = orderId;
    this.itemId = itemId;
    this.name = name;
    this.item = item;
    this.status = status;
    this.madeBy = madeBy;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", OrderUpdate.class.getSimpleName() + "[", "]")
            .add("orderId='" + orderId + "'")
            .add("itemId='" + itemId + "'")
            .add("name='" + name + "'")
            .add("item=" + item)
            .add("status=" + status)
            .add("madeBy='" + madeBy + "'")
            .toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    OrderUpdate that = (OrderUpdate) o;

    if (orderId != null ? !orderId.equals(that.orderId) : that.orderId != null) return false;
    if (itemId != null ? !itemId.equals(that.itemId) : that.itemId != null) return false;
    if (name != null ? !name.equals(that.name) : that.name != null) return false;
    if (item != that.item) return false;
    if (status != that.status) return false;
    return madeBy != null ? madeBy.equals(that.madeBy) : that.madeBy == null;
  }

  @Override
  public int hashCode() {
    int result = orderId != null ? orderId.hashCode() : 0;
    result = 31 * result + (itemId != null ? itemId.hashCode() : 0);
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (item != null ? item.hashCode() : 0);
    result = 31 * result + (status != null ? status.hashCode() : 0);
    result = 31 * result + (madeBy != null ? madeBy.hashCode() : 0);
    return result;
  }

  public Optional<String> getMadeBy() {
    return Optional.ofNullable(madeBy);
  }

  public String getOrderId() {
    return orderId;
  }

  public String getItemId() {
    return itemId;
  }

  public String getName() {
    return name;
  }

  public String getItem() {
    return item;
  }

  public String getStatus() {
    return status;
  }

}