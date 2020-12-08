package poussecafe.shop.model.order;

import poussecafe.domain.EntityDataAccess;
import poussecafe.shop.model.order.Order.Root;

public interface OrderDataAccess<N extends Root.Attributes> extends EntityDataAccess<OrderId, N> {
}
