package poussecafe.shop.core.model.order.adapters;

import java.io.Serializable;
import poussecafe.attribute.AutoAdapter;
import poussecafe.shop.core.model.customer.CustomerId;
import poussecafe.shop.core.model.order.OrderId;
import poussecafe.shop.core.model.product.ProductId;

@SuppressWarnings("serial")
public class OrderIdData implements Serializable, AutoAdapter<OrderId> {

    public static OrderIdData adapt(OrderId id) {
        OrderIdData data = new OrderIdData();
        data.productId = id.getProductId().stringValue();
        data.customerId = id.getCustomerId().stringValue();
        data.reference = id.getReference();
        return data;
    }

    private String productId;

    private String customerId;

    private String reference;

    @Override
    public OrderId adapt() {
        return new OrderId(new ProductId(productId), new CustomerId(customerId), reference);
    }
}
