package poussecafe.shop.core.model.customer.adapters;

import poussecafe.discovery.DataAccessImplementation;
import poussecafe.shop.core.model.customer.CustomerId;
import poussecafe.shop.core.model.customer.Customer.Root;
import poussecafe.storage.internal.InternalDataAccess;
import poussecafe.storage.internal.InternalStorage;

@DataAccessImplementation(
    aggregateRoot = Root.class,
    dataImplementation = CustomerAttributes.class,
    storageName = InternalStorage.NAME
)
public class CustomerInternalDataAccess extends InternalDataAccess<CustomerId, Root.Attributes> {

}
