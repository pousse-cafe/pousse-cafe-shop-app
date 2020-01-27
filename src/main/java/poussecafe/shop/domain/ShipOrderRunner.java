package poussecafe.shop.domain;

import poussecafe.listeners.AlwaysUpdateOneRunner;
import poussecafe.shop.command.ShipOrder;

public class ShipOrderRunner extends AlwaysUpdateOneRunner<ShipOrder, OrderId, Order> {

    @Override
    public OrderId aggregateId(ShipOrder message) {
        return message.orderId().value();
    }
}
