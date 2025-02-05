package poussecafe.shop.core.model.order;

import poussecafe.listeners.UpdateOneRunner;
import poussecafe.shop.core.commands.ShipOrder;
import poussecafe.shop.core.model.order.Order.Root;

public class ShipOrderRunner extends UpdateOneRunner<ShipOrder, OrderId, Root> {

    @Override
    public OrderId aggregateId(ShipOrder message) {
        return message.orderId().value();
    }
}
