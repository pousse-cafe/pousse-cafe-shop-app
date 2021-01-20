package poussecafe.shop.model.product;

import poussecafe.domain.ValueObject;
import poussecafe.util.StringId;

/**
 * @trivial
 */
public class ProductId extends StringId implements ValueObject {

    public ProductId(String id) {
        super(id);
    }
}
