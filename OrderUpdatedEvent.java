package org.larizmen.analysis.domain;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.debezium.outbox.quarkus.ExportedEvent;
import java.time.Instant;

public class OrderUpdatedEvent implements ExportedEvent<String, JsonNode> {

  private static ObjectMapper mapper = new ObjectMapper();

  private static final String TYPE = "Order";
  private static final String EVENT_TYPE = "OrderUpdated";

  private final String orderId;
  private final JsonNode jsonNode;
  private final Instant timestamp;

  private OrderUpdatedEvent(String orderId, JsonNode jsonNode, Instant timestamp) {
    this.orderId = orderId;
    this.jsonNode = jsonNode;
    this.timestamp = Instant.now();
  }

  public static OrderUpdatedEvent of(final Order order) {

    ObjectNode asJson = mapper.createObjectNode()
      .put("orderId", order.getOrderId())
      .put("timestamp", order.getTimestamp().toString());

    if (order.getRegularLineItems().isPresent()) {
      ArrayNode regularLineItems = asJson.putArray("regularLineItems") ;
      for (LineItem lineItem : order.getRegularLineItems().get()) {
        ObjectNode lineAsJon = mapper.createObjectNode()
          .put("item", lineItem.getItem().toString())
          .put("name", lineItem.getName())
          .put("lineItemStatus", lineItem.getLineItemStatus().toString());
        regularLineItems.add(lineAsJon);
      }
    }

    if (order.getVirusLineItems().isPresent()) {
      ArrayNode virusLineItems = asJson.putArray("virusLineItems") ;
      for (LineItem lineItem : order.getVirusLineItems().get()) {
        ObjectNode lineAsJon = mapper.createObjectNode()
          .put("item", lineItem.getItem().toString())
          .put("name", lineItem.getName())
          .put("lineItemStatus", lineItem.getLineItemStatus().toString());
        virusLineItems.add(lineAsJon);
      }
    }

    return new OrderUpdatedEvent(
      order.getOrderId(),
      asJson,
      order.getTimestamp());
  }

  @Override
  public String getAggregateId() {
    return orderId;
  }

  @Override
  public String getAggregateType() {
    return TYPE;
  }

  @Override
  public String getType() {
    return EVENT_TYPE;
  }

  @Override
  public Instant getTimestamp() {
    return timestamp;
  }

  @Override
  public JsonNode getPayload() {
    return jsonNode;
  }
}
