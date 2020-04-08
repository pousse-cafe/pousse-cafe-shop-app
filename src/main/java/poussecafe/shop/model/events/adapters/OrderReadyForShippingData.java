package poussecafe.shop.model.events.adapters;

import java.io.Serializable;
import poussecafe.attribute.Attribute;
import poussecafe.attribute.AttributeBuilder;
import poussecafe.discovery.MessageImplementation;
import poussecafe.shop.model.events.OrderReadyForShipping;
import poussecafe.shop.model.order.OrderId;
import poussecafe.shop.model.order.adapters.OrderIdData;

@MessageImplementation(message = OrderReadyForShipping.class)
@SuppressWarnings("serial")
public class OrderReadyForShippingData implements Serializable, OrderReadyForShipping {

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
