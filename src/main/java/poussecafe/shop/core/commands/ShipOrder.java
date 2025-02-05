package poussecafe.shop.core.commands;

import poussecafe.attribute.Attribute;
import poussecafe.runtime.Command;
import poussecafe.shop.core.model.order.OrderId;

public interface ShipOrder extends Command {

    Attribute<OrderId> orderId();
}
