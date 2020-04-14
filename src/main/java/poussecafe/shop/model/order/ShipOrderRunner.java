package poussecafe.shop.model.order;

import poussecafe.listeners.UpdateOneRunner;
import poussecafe.shop.commands.ShipOrder;

public class ShipOrderRunner extends UpdateOneRunner<ShipOrder, OrderId, Order> {

    @Override
    public OrderId aggregateId(ShipOrder message) {
        return message.orderId().value();
    }
}