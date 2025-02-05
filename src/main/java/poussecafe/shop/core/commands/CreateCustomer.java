package poussecafe.shop.core.commands;

import poussecafe.attribute.Attribute;
import poussecafe.runtime.Command;
import poussecafe.shop.core.model.customer.CustomerId;

public interface CreateCustomer extends Command {

    Attribute<CustomerId> customerId();
}
