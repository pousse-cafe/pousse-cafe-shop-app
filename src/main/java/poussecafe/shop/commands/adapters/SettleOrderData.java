package poussecafe.shop.commands.adapters;

import java.io.Serializable;
import poussecafe.attribute.Attribute;
import poussecafe.attribute.AttributeBuilder;
import poussecafe.discovery.MessageImplementation;
import poussecafe.shop.commands.SettleOrder;
import poussecafe.shop.model.order.OrderId;
import poussecafe.shop.model.order.adapters.OrderIdData;

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
