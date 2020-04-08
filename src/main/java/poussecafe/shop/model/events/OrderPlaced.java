package poussecafe.shop.model.events;

import poussecafe.attribute.Attribute;
import poussecafe.domain.DomainEvent;
import poussecafe.shop.model.order.OrderDescription;
import poussecafe.shop.model.product.ProductId;

public interface OrderPlaced extends DomainEvent {

    Attribute<ProductId> productId();

    Attribute<OrderDescription> description();
}
