package poussecafe.shop.commands.adapters;

import java.io.Serializable;
import poussecafe.attribute.Attribute;
import poussecafe.attribute.AttributeBuilder;
import poussecafe.discovery.MessageImplementation;
import poussecafe.shop.commands.CreateCustomer;
import poussecafe.shop.model.customer.CustomerId;

@MessageImplementation(message = CreateCustomer.class)
@SuppressWarnings("serial")
public class CreateCustomerData implements Serializable, CreateCustomer {

    @Override
    public Attribute<CustomerId> customerId() {
        return AttributeBuilder.stringId(CustomerId.class)
                .read(() -> customerId)
                .write(value -> customerId = value)
                .build();
    }

    private String customerId;
}
