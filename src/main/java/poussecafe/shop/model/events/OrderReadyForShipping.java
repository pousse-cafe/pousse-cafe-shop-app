package poussecafe.shop.model.events;

import poussecafe.attribute.Attribute;
import poussecafe.domain.DomainEvent;
import poussecafe.shop.model.order.OrderId;

public interface OrderReadyForShipping extends DomainEvent {

    Attribute<OrderId> orderId();
}
