package poussecafe.shop.model.events.adapters;

import java.io.Serializable;
import poussecafe.attribute.Attribute;
import poussecafe.attribute.AttributeBuilder;
import poussecafe.discovery.MessageImplementation;
import poussecafe.shop.model.events.OrderPlaced;
import poussecafe.shop.model.order.OrderDescription;
import poussecafe.shop.model.order.adapters.OrderDescriptionData;
import poussecafe.shop.model.product.ProductId;

@MessageImplementation(message = OrderPlaced.class)
@SuppressWarnings("serial")
public class OrderPlacedData implements Serializable, OrderPlaced {

    @Override
    public Attribute<ProductId> productId() {
        return AttributeBuilder.single(ProductId.class)
                .storedAs(String.class)
                .adaptOnRead(ProductId::new)
                .read(() -> productId)
                .adaptOnWrite(ProductId::stringValue)
                .write(value -> productId = value)
                .build();
    }

    private String productId;

    @Override
    public Attribute<OrderDescription> description() {
        return AttributeBuilder.single(OrderDescription.class)
                .usingAutoAdapter(OrderDescriptionData.class)
                .read(() -> description)
                .write(value -> description = value)
                .build();
    }

    private OrderDescriptionData description;
}
