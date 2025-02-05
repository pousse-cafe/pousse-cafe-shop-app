package poussecafe.shop.core.commands;

import poussecafe.attribute.Attribute;
import poussecafe.runtime.Command;
import poussecafe.shop.core.model.product.ProductId;

public interface AddUnits extends Command {

    Attribute<ProductId> productId();

    Attribute<Integer> units();
}
