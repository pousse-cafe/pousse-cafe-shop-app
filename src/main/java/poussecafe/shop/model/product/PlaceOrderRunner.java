package poussecafe.shop.model.product;

import poussecafe.listeners.UpdateOneRunner;
import poussecafe.shop.commands.PlaceOrder;

public class PlaceOrderRunner extends UpdateOneRunner<PlaceOrder, ProductId, ProductRoot> {

    @Override
    protected ProductId aggregateId(PlaceOrder message) {
        return message.productId().value();
    }
}
