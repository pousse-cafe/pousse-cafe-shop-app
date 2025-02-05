package poussecafe.shop.core.model.order.adapters;

import java.io.Serializable;
import poussecafe.attribute.AutoAdapter;
import poussecafe.shop.core.model.customer.CustomerId;
import poussecafe.shop.core.model.order.OrderDescription;

@SuppressWarnings("serial")
public class OrderDescriptionData implements Serializable, AutoAdapter<OrderDescription> {

    public static OrderDescriptionData adapt(OrderDescription description) {
        OrderDescriptionData data = new OrderDescriptionData();
        data.customerId = description.customerId().stringValue();
        data.reference = description.reference();
        data.units = description.units();
        return data;
    }

    private String customerId;

    private String reference;

    private int units;

    @Override
    public OrderDescription adapt() {
        return new OrderDescription.Builder()
                .customerId(new CustomerId(customerId))
                .reference(reference)
                .units(units)
                .build();
    }
}
