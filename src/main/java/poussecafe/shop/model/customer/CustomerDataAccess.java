package poussecafe.shop.model.customer;

import poussecafe.domain.EntityDataAccess;
import poussecafe.shop.model.customer.Customer.Root;

public interface CustomerDataAccess<N extends Root.Attributes> extends EntityDataAccess<CustomerId, N> {
}
