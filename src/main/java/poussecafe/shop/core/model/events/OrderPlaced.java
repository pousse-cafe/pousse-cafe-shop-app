package poussecafe.shop.core.model.events;

import poussecafe.attribute.Attribute;
import poussecafe.domain.DomainEvent;
import poussecafe.shop.core.model.order.OrderDescription;
import poussecafe.shop.core.model.product.ProductId;

public interface OrderPlaced extends DomainEvent {

    Attribute<ProductId> productId();

    Attribute<OrderDescription> description();
}
