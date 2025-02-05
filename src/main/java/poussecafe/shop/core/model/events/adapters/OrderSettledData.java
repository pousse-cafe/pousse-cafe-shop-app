package poussecafe.shop.core.model.events.adapters;

import java.io.Serializable;
import poussecafe.attribute.Attribute;
import poussecafe.attribute.AttributeBuilder;
import poussecafe.discovery.MessageImplementation;
import poussecafe.shop.core.model.events.OrderSettled;
import poussecafe.shop.core.model.order.OrderId;
import poussecafe.shop.core.model.order.adapters.OrderIdData;

@MessageImplementation(message = OrderSettled.class)
@SuppressWarnings("serial")
public class OrderSettledData implements Serializable, OrderSettled {

    @Override
    public Attribute<OrderId> orderId() {
        return AttributeBuilder.single(OrderId.class)
                .storedAs(OrderIdData.class)
                .adaptOnRead(OrderIdData::adapt)
                .read(() -> orderId)
                .adaptOnWrite(OrderIdData::adapt)
                .write(value -> orderId = value)
                .build();
    }

    private OrderIdData orderId;
}
