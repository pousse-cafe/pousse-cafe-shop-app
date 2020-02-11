package poussecafe.shop.domain;

import poussecafe.listeners.UpdateOneRunner;
import poussecafe.shop.command.SettleOrder;

public class SettleRunner extends UpdateOneRunner<SettleOrder, OrderId, Order> {

    @Override
    protected OrderId aggregateId(SettleOrder message) {
        return message.orderId().value();
    }
}
