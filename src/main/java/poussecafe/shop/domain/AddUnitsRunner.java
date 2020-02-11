package poussecafe.shop.domain;

import poussecafe.listeners.UpdateOneRunner;
import poussecafe.shop.command.AddUnits;

public class AddUnitsRunner
extends UpdateOneRunner<AddUnits, ProductId, Product> {

    @Override
    protected ProductId aggregateId(AddUnits message) {
        return message.productId().value();
    }
}
