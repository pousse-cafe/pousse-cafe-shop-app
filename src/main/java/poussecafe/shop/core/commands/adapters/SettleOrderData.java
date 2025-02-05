package poussecafe.shop.core.commands.adapters;

import java.io.Serializable;
import poussecafe.attribute.Attribute;
import poussecafe.attribute.AttributeBuilder;
import poussecafe.discovery.MessageImplementation;
import poussecafe.shop.core.commands.SettleOrder;
import poussecafe.shop.core.model.order.OrderId;
import poussecafe.shop.core.model.order.adapters.OrderIdData;

@MessageImplementation(message = SettleOrder.class)
@SuppressWarnings("serial")
public class SettleOrderData implements Serializable, SettleOrder {

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
