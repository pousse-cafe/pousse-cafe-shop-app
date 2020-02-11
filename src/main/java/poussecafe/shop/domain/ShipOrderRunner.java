package poussecafe.shop.domain;

import poussecafe.listeners.UpdateOneRunner;
import poussecafe.shop.command.ShipOrder;

public class ShipOrderRunner extends UpdateOneRunner<ShipOrder, OrderId, Order> {

    @Override
    public OrderId aggregateId(ShipOrder message) {
        return message.orderId().value();
    }
}
