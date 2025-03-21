package poussecafe.shop.core.model.order.adapters;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import poussecafe.attribute.Attribute;
import poussecafe.attribute.AttributeBuilder;
import poussecafe.shop.core.model.order.OrderId;
import poussecafe.shop.core.model.order.Order.Root;

@SuppressWarnings("serial")
@Entity
public class OrderAttributes implements Root.Attributes, Serializable {

    @Override
    public Attribute<OrderId> identifier() {
        return AttributeBuilder.single(OrderId.class)
                .usingAutoAdapter(OrderIdData.class)
                .read(() -> id)
                .write(value -> id = value)
                .build();
    }

    @Id
    private OrderIdData id;

    @Override
    public Attribute<Integer> units() {
        return AttributeBuilder.single(Integer.class)
                .read(() -> units)
                .write(value -> units = value)
                .build();
    }

    private int units;
}
