package poussecafe.shop.model.order;

import poussecafe.listeners.UpdateOneRunner;
import poussecafe.shop.commands.ShipOrder;
import poussecafe.shop.model.order.Order.OrderRoot;

public class ShipOrderRunner extends UpdateOneRunner<ShipOrder, OrderId, OrderRoot> {

    @Override
    public OrderId aggregateId(ShipOrder message) {
        return message.orderId().value();
    }
}
