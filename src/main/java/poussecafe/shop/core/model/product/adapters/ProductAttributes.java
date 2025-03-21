package poussecafe.shop.core.model.product.adapters;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import poussecafe.attribute.Attribute;
import poussecafe.attribute.AttributeBuilder;
import poussecafe.shop.core.model.product.Product;
import poussecafe.shop.core.model.product.ProductId;

@SuppressWarnings("serial")
@Entity
public class ProductAttributes implements Product.Root.Attributes, Serializable {

    @Override
    public Attribute<ProductId> identifier() {
        return AttributeBuilder.stringId(ProductId.class)
                .read(() -> productId)
                .write(value -> productId = value)
                .build();
    }

    @Id
    private String productId;

    @Override
    public Attribute<Integer> totalUnits() {
        return AttributeBuilder.single(Integer.class)
                .read(() -> totalUnits)
                .write(value -> totalUnits = value)
                .build();
    }

    private int totalUnits;

    @Override
    public Attribute<Integer> availableUnits() {
        return AttributeBuilder.single(Integer.class)
                .read(() -> availableUnits)
                .write(value -> availableUnits = value)
                .build();
    }

    private int availableUnits;
}
