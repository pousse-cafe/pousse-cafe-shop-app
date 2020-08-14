package poussecafe.shop.model.order;

import poussecafe.listeners.UpdateOneRunner;
import poussecafe.shop.commands.SettleOrder;
import poussecafe.shop.model.order.Order.OrderRoot;

public class SettleRunner extends UpdateOneRunner<SettleOrder, OrderId, OrderRoot> {

    @Override
    protected OrderId aggregateId(SettleOrder message) {
        return message.orderId().value();
    }
}
