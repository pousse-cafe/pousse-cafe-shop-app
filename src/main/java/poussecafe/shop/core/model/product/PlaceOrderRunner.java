package poussecafe.shop.core.model.product;

import poussecafe.listeners.UpdateOneRunner;
import poussecafe.shop.core.commands.PlaceOrder;

public class PlaceOrderRunner extends UpdateOneRunner<PlaceOrder, ProductId, Product.Root> {

    @Override
    protected ProductId aggregateId(PlaceOrder message) {
        return message.productId().value();
    }
}
