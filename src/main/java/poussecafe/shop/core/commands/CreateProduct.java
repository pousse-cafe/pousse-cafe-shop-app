package poussecafe.shop.core.commands;

import poussecafe.attribute.Attribute;
import poussecafe.runtime.Command;
import poussecafe.shop.core.model.product.ProductId;

public interface CreateProduct extends Command {

    Attribute<ProductId> productId();
}
