package poussecafe.shop.commands;

import poussecafe.attribute.Attribute;
import poussecafe.runtime.Command;
import poussecafe.shop.model.customer.CustomerId;

public interface CreateCustomer extends Command {

    Attribute<CustomerId> customerId();
}
