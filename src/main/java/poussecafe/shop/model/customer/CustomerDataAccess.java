package poussecafe.shop.model.customer;

import poussecafe.domain.EntityDataAccess;

public interface CustomerDataAccess<N extends Customer.Attributes> extends EntityDataAccess<CustomerId, N> {
}
