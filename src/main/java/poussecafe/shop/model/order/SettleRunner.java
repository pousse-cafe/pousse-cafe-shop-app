package poussecafe.shop.model.order;

import poussecafe.listeners.UpdateOneRunner;
import poussecafe.shop.commands.SettleOrder;

public class SettleRunner extends UpdateOneRunner<SettleOrder, OrderId, Order> {

    @Override
    protected OrderId aggregateId(SettleOrder message) {
        return message.orderId().value();
    }
}
