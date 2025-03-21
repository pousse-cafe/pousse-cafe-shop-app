package poussecafe.shop.core.model.order;

import poussecafe.domain.EntityDataAccess;
import poussecafe.shop.core.model.order.Order.Root;

public interface OrderDataAccess<N extends Root.Attributes> extends EntityDataAccess<OrderId, N> {
}
