package poussecafe.shop.domain;

import poussecafe.listeners.AlwaysUpdateOneRunner;
import poussecafe.shop.command.AddUnits;

public class AddUnitsRunner
extends AlwaysUpdateOneRunner<AddUnits, ProductId, Product> {

    @Override
    protected ProductId aggregateId(AddUnits message) {
        return message.productId().value();
    }
}
