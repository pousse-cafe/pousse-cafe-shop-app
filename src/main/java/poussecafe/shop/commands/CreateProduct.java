package poussecafe.shop.commands;

import poussecafe.attribute.Attribute;
import poussecafe.runtime.Command;
import poussecafe.shop.model.product.ProductId;

public interface CreateProduct extends Command {

    Attribute<ProductId> productId();
}
