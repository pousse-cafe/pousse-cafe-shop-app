package poussecafe.shop.core.model.events;

import poussecafe.attribute.Attribute;
import poussecafe.domain.DomainEvent;
import poussecafe.shop.core.model.order.OrderId;

public interface OrderCreated extends DomainEvent {

    Attribute<OrderId> orderId();
}
