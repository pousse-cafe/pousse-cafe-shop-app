package poussecafe.shop.domain;

import poussecafe.listeners.AlwaysUpdateOneRunner;
import poussecafe.shop.command.SettleOrder;

public class SettleRunner
extends AlwaysUpdateOneRunner<SettleOrder, OrderId, Order> {

    @Override
    protected OrderId aggregateId(SettleOrder message) {
        return message.orderId().value();
    }
}
