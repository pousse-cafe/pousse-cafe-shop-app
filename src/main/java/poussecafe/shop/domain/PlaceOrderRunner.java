package poussecafe.shop.domain;

import poussecafe.listeners.UpdateOneRunner;
import poussecafe.shop.command.PlaceOrder;

public class PlaceOrderRunner extends UpdateOneRunner<PlaceOrder, ProductId, Product> {

    @Override
    protected ProductId aggregateId(PlaceOrder message) {
        return message.productId().value();
    }
}
