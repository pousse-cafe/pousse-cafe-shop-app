package poussecafe.shop.core.model.order.adapters;

import poussecafe.discovery.DataAccessImplementation;
import poussecafe.shop.core.model.order.OrderId;
import poussecafe.shop.core.model.order.Order.Root;
import poussecafe.storage.internal.InternalDataAccess;
import poussecafe.storage.internal.InternalStorage;

@DataAccessImplementation(
    aggregateRoot = Root.class,
    dataImplementation = OrderAttributes.class,
    storageName = InternalStorage.NAME
)
public class OrderInternalDataAccess extends InternalDataAccess<OrderId, Root.Attributes> {

}
