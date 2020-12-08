package poussecafe.shop.model.order;

import poussecafe.listeners.UpdateOneRunner;
import poussecafe.shop.commands.SettleOrder;
import poussecafe.shop.model.order.Order.Root;

public class SettleRunner extends UpdateOneRunner<SettleOrder, OrderId, Root> {

    @Override
    protected OrderId aggregateId(SettleOrder message) {
        return message.orderId().value();
    }
}
