package poussecafe.shop.model.order.adapters;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import poussecafe.attribute.Attribute;
import poussecafe.attribute.AttributeBuilder;
import poussecafe.shop.model.order.Order.OrderRoot;
import poussecafe.shop.model.order.OrderId;

@SuppressWarnings("serial")
@Entity
public class OrderData implements OrderRoot.Attributes, Serializable {

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
