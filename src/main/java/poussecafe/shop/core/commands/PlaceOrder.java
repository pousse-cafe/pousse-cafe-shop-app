package poussecafe.shop.core.commands;

import poussecafe.attribute.Attribute;
import poussecafe.runtime.Command;
import poussecafe.shop.core.model.order.OrderDescription;
import poussecafe.shop.core.model.product.ProductId;

public interface PlaceOrder extends Command {

    Attribute<ProductId> productId();

    Attribute<OrderDescription> description();
}
