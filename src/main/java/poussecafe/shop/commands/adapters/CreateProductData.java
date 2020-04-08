package poussecafe.shop.commands.adapters;

import java.io.Serializable;
import poussecafe.attribute.Attribute;
import poussecafe.attribute.AttributeBuilder;
import poussecafe.discovery.MessageImplementation;
import poussecafe.shop.commands.CreateProduct;
import poussecafe.shop.model.product.ProductId;

@MessageImplementation(message = CreateProduct.class)
@SuppressWarnings("serial")
public class CreateProductData implements Serializable, CreateProduct {

    @Override
    public Attribute<ProductId> productId() {
        return AttributeBuilder.stringId(ProductId.class)
                .read(() -> productId)
                .write(value -> productId = value)
                .build();
    }

    private String productId;
}
