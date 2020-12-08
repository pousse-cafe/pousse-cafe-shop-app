package poussecafe.shop.model.customer.adapters;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import poussecafe.attribute.Attribute;
import poussecafe.shop.model.customer.Customer.Root;
import poussecafe.shop.model.customer.CustomerId;

@SuppressWarnings("serial")
@Entity
public class CustomerData implements Root.Attributes, Serializable {

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
