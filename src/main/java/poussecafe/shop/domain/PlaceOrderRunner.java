package poussecafe.shop.domain;

import poussecafe.listeners.AlwaysUpdateOneRunner;
import poussecafe.shop.command.PlaceOrder;

public class PlaceOrderRunner extends AlwaysUpdateOneRunner<PlaceOrder, ProductId, Product> {

    @Override
    protected ProductId aggregateId(PlaceOrder message) {
        return message.productId().value();
    }
}
