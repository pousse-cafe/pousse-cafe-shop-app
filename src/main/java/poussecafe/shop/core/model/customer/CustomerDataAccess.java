package poussecafe.shop.core.model.customer;

import poussecafe.domain.EntityDataAccess;
import poussecafe.shop.core.model.customer.Customer.Root;

public interface CustomerDataAccess<N extends Root.Attributes> extends EntityDataAccess<CustomerId, N> {
}
