package poussecafe.shop.model.customer;

import poussecafe.domain.EntityDataAccess;
import poussecafe.shop.model.customer.Customer.CustomerRoot;

public interface CustomerDataAccess<N extends CustomerRoot.Attributes> extends EntityDataAccess<CustomerId, N> {
}
