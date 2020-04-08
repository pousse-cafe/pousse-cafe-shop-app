package poussecafe.shop.model.order;

import poussecafe.domain.EntityDataAccess;

public interface OrderDataAccess<N extends Order.Attributes> extends EntityDataAccess<OrderId, N> {
}
