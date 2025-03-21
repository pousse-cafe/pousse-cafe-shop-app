package poussecafe.shop.core.model.customer.adapters;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import poussecafe.attribute.Attribute;
import poussecafe.shop.core.model.customer.CustomerId;
import poussecafe.shop.core.model.customer.Customer.Root;

@SuppressWarnings("serial")
@Entity
public class CustomerAttributes implements Root.Attributes, Serializable {

    @Override
    public Attribute<CustomerId> identifier() {
        return new Attribute<>() {

            @Override
            public CustomerId value() {
                return new CustomerId(id);
            }

            @Override
            public void value(CustomerId value) {
                id = value.stringValue();
            }

        };
    }

    @Id
    private String id;
}
