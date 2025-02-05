package poussecafe.shop.core.model.order;

import poussecafe.listeners.UpdateOneRunner;
import poussecafe.shop.core.commands.SettleOrder;
import poussecafe.shop.core.model.order.Order.Root;

public class SettleRunner extends UpdateOneRunner<SettleOrder, OrderId, Root> {

    @Override
    protected OrderId aggregateId(SettleOrder message) {
        return message.orderId().value();
    }
}
