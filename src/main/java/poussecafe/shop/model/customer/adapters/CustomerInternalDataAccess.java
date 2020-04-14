package poussecafe.shop.model.customer.adapters;

import poussecafe.discovery.DataAccessImplementation;
import poussecafe.shop.model.customer.Customer;
import poussecafe.shop.model.customer.CustomerId;
import poussecafe.storage.internal.InternalDataAccess;
import poussecafe.storage.internal.InternalStorage;

@DataAccessImplementation(
    aggregateRoot = Customer.class,
    dataImplementation = CustomerData.class,
    storageName = InternalStorage.NAME
)
public class CustomerInternalDataAccess extends InternalDataAccess<CustomerId, Customer.Attributes> {

}