package poussecafe.shop.core.model.customer;

import poussecafe.domain.ValueObject;
import poussecafe.util.StringId;

/**
 * @trivial
 */
public class CustomerId extends StringId implements ValueObject {

    public CustomerId(String value) {
        super(value);
    }

}
