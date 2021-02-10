package poussecafe.shop.model.customer.adapters;

import poussecafe.discovery.DataAccessImplementation;
import poussecafe.shop.model.customer.Customer.Root;
import poussecafe.shop.model.customer.CustomerId;
import poussecafe.storage.internal.InternalDataAccess;
import poussecafe.storage.internal.InternalStorage;

@DataAccessImplementation(
    aggregateRoot = Root.class,
    dataImplementation = CustomerAttributes.class,
    storageName = InternalStorage.NAME
)
public class CustomerInternalDataAccess extends InternalDataAccess<CustomerId, Root.Attributes> {

}
