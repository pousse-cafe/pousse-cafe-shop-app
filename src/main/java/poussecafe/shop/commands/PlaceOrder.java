package poussecafe.shop.commands;

import poussecafe.attribute.Attribute;
import poussecafe.runtime.Command;
import poussecafe.shop.model.order.OrderDescription;
import poussecafe.shop.model.product.ProductId;

public interface PlaceOrder extends Command {

    Attribute<ProductId> productId();

    Attribute<OrderDescription> description();
}
