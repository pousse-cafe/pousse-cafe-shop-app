package poussecafe.shop.model.order.adapters;

import poussecafe.discovery.DataAccessImplementation;
import poussecafe.shop.model.order.Order.OrderRoot;
import poussecafe.shop.model.order.OrderId;
import poussecafe.storage.internal.InternalDataAccess;
import poussecafe.storage.internal.InternalStorage;

@DataAccessImplementation(
    aggregateRoot = OrderRoot.class,
    dataImplementation = OrderData.class,
    storageName = InternalStorage.NAME
)
public class OrderInternalDataAccess extends InternalDataAccess<OrderId, OrderRoot.Attributes> {

}
