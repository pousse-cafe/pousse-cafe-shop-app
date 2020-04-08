package poussecafe.shop.commands;

import poussecafe.attribute.Attribute;
import poussecafe.runtime.Command;
import poussecafe.shop.model.order.OrderId;

public interface ShipOrder extends Command {

    Attribute<OrderId> orderId();
}
