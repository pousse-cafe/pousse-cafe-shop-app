package poussecafe.shop.model.order;

import poussecafe.domain.EntityDataAccess;
import poussecafe.shop.model.order.Order.OrderRoot;

public interface OrderDataAccess<N extends OrderRoot.Attributes> extends EntityDataAccess<OrderId, N> {
}
