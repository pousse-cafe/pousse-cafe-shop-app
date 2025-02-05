package poussecafe.shop.core.model.product;

import poussecafe.listeners.UpdateOneRunner;
import poussecafe.shop.core.commands.AddUnits;

public class AddUnitsRunner
extends UpdateOneRunner<AddUnits, ProductId, Product.Root> {

    @Override
    protected ProductId aggregateId(AddUnits message) {
        return message.productId().value();
    }
}
