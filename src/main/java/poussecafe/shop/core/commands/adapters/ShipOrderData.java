package poussecafe.shop.core.commands.adapters;

import java.io.Serializable;
import poussecafe.attribute.Attribute;
import poussecafe.attribute.AttributeBuilder;
import poussecafe.discovery.MessageImplementation;
import poussecafe.shop.core.commands.ShipOrder;
import poussecafe.shop.core.model.order.OrderId;
import poussecafe.shop.core.model.order.adapters.OrderIdData;

@MessageImplementation(message = ShipOrder.class)
@SuppressWarnings("serial")
public class ShipOrderData implements Serializable, ShipOrder {

    @Override
    public Attribute<OrderId> orderId() {
        return AttributeBuilder.single(OrderId.class)
                .usingAutoAdapter(OrderIdData.class)
                .read(() -> orderId)
                .write(value -> orderId = value)
                .build();
    }

    private OrderIdData orderId;
}
